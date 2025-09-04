package com.ciptadana.intra_day_loan_balance.database.oracle.repository;

import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.CashPositionJpaRepository;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection.CashPositionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashPositionRepository {

    private final CashPositionJpaRepository cashPositionJpaRepository;

    public void executeApiClientBuyingPower(String clientCode, String res) {
        cashPositionJpaRepository.executeApiClientBuyingPower(clientCode, res);
    }

    public CashPositionProjection getCashPosition() {
        return cashPositionJpaRepository.getCashPosition();
    }
}