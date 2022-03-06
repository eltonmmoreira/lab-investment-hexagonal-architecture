package br.com.lab.investment.application.impl;

import br.com.lab.investment.application.InvestmentApplication;
import br.com.lab.investment.application.dto.request.InvestmentRequest;
import br.com.lab.investment.application.dto.response.InvestmentResponse;
import br.com.lab.investment.domain.service.InvestmentService;
import org.springframework.stereotype.Component;

import static br.com.lab.investment.application.adapter.InvestmentAdapter.toDtoInvestment;

@Component
public class InvestmentApplicationImpl implements InvestmentApplication {

    private final InvestmentService investmentService;

    public InvestmentApplicationImpl(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @Override
    public InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest) {
        return toDtoInvestment(investmentService.invest(
                investmentRequest.getProductId(),
                accountId,
                investmentRequest.getValue())
        );
    }
}
