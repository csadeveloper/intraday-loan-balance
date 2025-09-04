package com.ciptadana.intra_day_loan_balance.controller;

import com.ciptadana.intra_day_loan_balance.database.oracle.repository.dto.PortfolioDTO;
import com.ciptadana.intra_day_loan_balance.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio-list")
@RequiredArgsConstructor
public class PortfolioListController {

    private final PortfolioService portfolioService;

    @GetMapping("/{clientCode}")
    public ResponseEntity<List<PortfolioDTO>> getPortfolioList(@PathVariable String clientCode) {
        List<PortfolioDTO> data = portfolioService.getPortfolioList(clientCode);
        return ResponseEntity.ok(data);
    }
}