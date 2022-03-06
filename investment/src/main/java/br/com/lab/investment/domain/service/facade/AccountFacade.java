package br.com.lab.investment.domain.service.facade;

import br.com.lab.investment.domain.service.facade.valueObject.AccountBalanceVO;

public interface AccountFacade {

    AccountBalanceVO getAccountBalanceById(Long accountId);

    boolean debitAccount(Long accountId, Double valueOfInvestment);
}
