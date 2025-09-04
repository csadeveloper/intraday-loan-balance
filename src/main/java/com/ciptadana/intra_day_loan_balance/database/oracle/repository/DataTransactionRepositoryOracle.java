package com.ciptadana.intra_day_loan_balance.database.oracle.repository;

import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection.GetDataMutation;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.DataTransactionJpaRepositoryOracle;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection.GetDataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataTransactionRepositoryOracle {

    private final DataTransactionJpaRepositoryOracle dataTransactionJpaRepositoryOracle;

    public List<String> getDataExistOnOracle(String trxDate){

        return dataTransactionJpaRepositoryOracle.getDataExistOnOracle(trxDate);
    }

    public GetDataClient getClientCode(String accountNo){

        GetDataClient dataClient = dataTransactionJpaRepositoryOracle.getClientCodeFromRdiBalance(accountNo);
        if(dataClient == null)
            dataClient = dataTransactionJpaRepositoryOracle.getClientCodeFromClientProperty(accountNo);

        return dataClient;
    }

    public boolean insertRdiBalanceLog(String clientcode, GetDataMutation data){

        boolean result = true;
        try {

            long amount = data.getValueTransaction();
            if(data.getMutationType().equals("D"))
                amount = amount * -1;

            dataTransactionJpaRepositoryOracle.insertRdiBalanceIntraDayLog(
                    data.getVdate(),
                    data.getVtime(),
                    clientcode,
                    data.getAccountId(),
                    amount,
                    data.getExternalRef(),
                    data.getRemarks()
            );

        }catch (Exception e){
            log.error(e.getMessage());
            result = false;
        }

        return result;
    }
}
