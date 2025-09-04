package com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa;

import com.ciptadana.intra_day_loan_balance.database.oracle.entity.NativeEntity;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection.PortfolioListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface PortfolioJpaRepository extends JpaRepository<NativeEntity, String> {

    @Query(value = "SELECT * FROM V_GET_PORTFOLIO_LIST_FO WHERE NCLIENT = :clientCode ORDER BY PORTFOLIO_USER", nativeQuery = true)
    List<PortfolioListProjection> getPortfolioList(@Param("clientCode") String clientCode);
}
