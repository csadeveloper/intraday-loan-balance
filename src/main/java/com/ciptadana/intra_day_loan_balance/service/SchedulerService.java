package com.ciptadana.intra_day_loan_balance.service;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerService {

    private final IntraDayLoanBalanceService intraDayLoanBalanceService;

    @Scheduled(fixedDelay = 6000)
    public void schedule() {
        intraDayLoanBalanceService.getDataMutation();
    }
}

