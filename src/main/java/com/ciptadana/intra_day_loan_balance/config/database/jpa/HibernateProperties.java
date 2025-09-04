package com.ciptadana.intra_day_loan_balance.config.database.jpa;

import lombok.Data;

@Data
public class HibernateProperties {

    private Boolean formatSql;
    private String ddlAuto;
    private int batchSize;
}
