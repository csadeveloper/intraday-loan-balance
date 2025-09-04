package com.ciptadana.intra_day_loan_balance.service;

import com.ciptadana.intra_day_loan_balance.database.mysql.repository.DataTransactionRepositoryMySQL;
import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection.GetDataMutation;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.DataTransactionRepositoryOracle;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection.GetDataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntraDayLoanBalanceService {


    private final DataTransactionRepositoryMySQL dataTransactionRepositoryMySQL;
    private final DataTransactionRepositoryOracle dataTransactionRepositoryOracle;

    private int lastTime = 0;
    private boolean isFirstStart = true;
    private final HashMap<String, String> mapKey = new HashMap<>();

    public void getDataMutation(){

        try {

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String trxDate = today.format(formatter);
            trxDate = "20250822";
            System.out.println("Date Processed: " + trxDate);

            if(isFirstStart) {
                log.info("START GET DATA");
                List<String> dataExternalRef = dataTransactionRepositoryOracle.getDataExistOnOracle(trxDate);
                for (String key : dataExternalRef) {
                    mapKey.put(key, key);
                }
                isFirstStart = false;
            }

            List<GetDataMutation> transactionsList = dataTransactionRepositoryMySQL.listDataMutation(trxDate, lastTime);
            log.info("FINISHED GET DATA, TOTAL: {}", transactionsList.size());

            log.info("START PROCESS DATA");
            int countData = 1;
            Iterator<GetDataMutation> iterator = transactionsList.iterator();

            while (iterator.hasNext()) {
                GetDataMutation transaction = iterator.next();
                log.info("Processing AccountNo: {}", transaction.getAccountId());

                GetDataClient data = dataTransactionRepositoryOracle.getClientCode(transaction.getAccountId());

                if (data != null) {

                    log.info("Transaction: {}", transaction);

                    if(mapKey.get(transaction.getExternalRef()) != null) {
                        log.info("Transaction not process: {}", transaction.getExternalRef());
                        return;
                    }

                    boolean insertMutationData = dataTransactionRepositoryOracle.insertRdiBalanceLog(data.getClientCode(), transaction);
                    if(insertMutationData) {
                        mapKey.put(transaction.getExternalRef(), transaction.getExternalRef());
                        lastTime = Integer.parseInt(transaction.getVtime());
                        iterator.remove();
                        log.info("Successfully processed account: {} {}", transaction.getAccountId(), transaction.getExternalRef());
                    }else
                        log.error("Failed processed account: {} {}", transaction.getAccountId(), transaction.getExternalRef());

                } else {
                    log.warn("Skipping account: {} {} due to missing client code", transaction.getAccountId(), transaction.getExternalRef());
                }
                countData++;
            }

            log.info("FINISHED PROCESSING {} DATA ", countData);

        }catch (Exception e){
            log.error(e.getMessage());
        }

    }



}
