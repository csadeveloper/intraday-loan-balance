package com.ciptadana.intra_day_loan_balance.database.mysql.repository;


import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.DataTransactionJpaRepositoryMySQL;
import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection.GetDataMutation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataTransactionRepositoryMySQL {

    private final DataTransactionJpaRepositoryMySQL dataTransactionJpaRepositoryMySQL;

    public List<GetDataMutation> listDataMutation(String trxDate, int lastTime){

        List<GetDataMutation> data = new ArrayList<>();
        data = dataTransactionJpaRepositoryMySQL.getDataMutation(trxDate, lastTime);

        return data;
    }
}
