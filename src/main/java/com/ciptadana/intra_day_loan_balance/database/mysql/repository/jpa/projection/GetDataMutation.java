package com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection;

public interface GetDataMutation {

    String getBank();
    String getVdate();
    String getVtime();
    String getExternalRef();
    Long getSeqNo();
    String getMutationType();
    String getAccountId();
    Long getBeginBalance();
    Long getValueTransaction();
    Long getEndBalance();
    String getRemarks();


}


