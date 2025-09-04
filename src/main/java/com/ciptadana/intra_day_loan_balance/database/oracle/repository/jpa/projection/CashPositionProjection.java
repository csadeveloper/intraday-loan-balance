package com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection;

import java.math.BigDecimal;

public interface CashPositionProjection {
    String getClientid();
    BigDecimal getBuyingpower();
    BigDecimal getLoanbalance();
    BigDecimal getLoanratio();
    BigDecimal getMarketvalue();
    BigDecimal getModifiedmarketvalue();
    BigDecimal getModifiedloanratio();
    BigDecimal getTradinglimit();
    String getClienttypecode();
    String getRating();
    String getClient_rating();
    BigDecimal getRiskcontrol();
    BigDecimal getOrderpower();
    String getUsername();
    String getUsertype();
    String getIs_automatic_shortsell();
    String getPriv();
    BigDecimal getOutstandingbov();
    BigDecimal getTradebuy();
    BigDecimal getTradesell();
    BigDecimal getCash_value();
    BigDecimal getAcc_payable();
}
