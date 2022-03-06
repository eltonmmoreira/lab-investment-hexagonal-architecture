package br.com.lab.investment.application;

import br.com.lab.investment.application.dto.request.InvestmentRequest;
import br.com.lab.investment.application.dto.response.InvestmentResponse;

public interface InvestmentApplication {

    InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest);
}
