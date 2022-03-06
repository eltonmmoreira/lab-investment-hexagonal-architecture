package br.com.lab.investment.domain.service;

import br.com.lab.investment.domain.model.Investment;

public interface InvestmentService {

    Investment invest(Long productId, Long accountId, Double valueInvestment);
    
}
