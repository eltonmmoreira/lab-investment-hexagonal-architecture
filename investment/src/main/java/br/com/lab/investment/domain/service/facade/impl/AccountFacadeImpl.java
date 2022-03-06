package br.com.lab.investment.domain.service.facade.impl;

import br.com.lab.investment.application.dto.request.DebitAccountRequest;
import br.com.lab.investment.domain.service.facade.AccountFacade;
import br.com.lab.investment.domain.service.facade.valueObject.AccountBalanceVO;
import br.com.lab.investment.domain.service.facade.valueObject.DebitAccountVO;
import br.com.lab.investment.infrastructure.http.AccountClient;
import org.springframework.stereotype.Component;

@Component
public class AccountFacadeImpl implements AccountFacade {

    private final AccountClient accountClient;

    public AccountFacadeImpl(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {
        return accountClient.getAccountBalance(accountId);
    }

    @Override
    public boolean debitAccount(Long accountId, Double valueOfInvestment) {
        var debitAccountVO = accountClient.debit(accountId,
                new DebitAccountRequest(valueOfInvestment));

        return debitAccountVO.isDebited();
    }
}
