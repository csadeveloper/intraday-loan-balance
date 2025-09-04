package com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection;

import java.math.BigDecimal;

public interface PortfolioListProjection {
    String getNclient();
    String getNshare();
    BigDecimal getPortfolio_user();
    BigDecimal getStock_outstanding();
    BigDecimal getMatch_buy();
    BigDecimal getMatch_sell();
    String getTrans_module();
}
