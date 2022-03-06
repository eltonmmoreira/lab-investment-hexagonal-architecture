package br.com.lab.investment.domain.service.impl;

import br.com.lab.investment.domain.exception.InvestmentAccountIsNotDebitException;
import br.com.lab.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.investment.domain.model.Investment;
import br.com.lab.investment.domain.service.InvestmentService;
import br.com.lab.investment.domain.service.facade.AccountFacade;
import br.com.lab.investment.infrastructure.repository.InvestmentRepository;
import br.com.lab.investment.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    private final ProductRepository productRepository;

    private final AccountFacade accountFacade;

    @Value("${lab.investment.exceptions.product-dont-exists-message}")
    private String messageExceptionProductNotFound;

    @Value("${lab.investment.exceptions.product-dont-exists-description}")
    private String descriptionExceptionProductNotFound;

    @Value("${lab.investment.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-message}")
    private String messageExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-description}")
    private String descriptionExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-is-not-debited-message}")
    private String messageExceptionAccountIfNotDebited;

    @Value("${lab.investment.exceptions.account-is-not-debited-description}")
    private String descriptionExceptionAccountIfNotDebited;

    public InvestmentServiceImpl(InvestmentRepository investmentRepository, ProductRepository productRepository, AccountFacade accountFacade) {
        this.investmentRepository = investmentRepository;
        this.productRepository = productRepository;
        this.accountFacade = accountFacade;
    }

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {
        var product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new InvestmentProductNotFoundException(
                                messageExceptionProductNotFound,
                                descriptionExceptionProductNotFound
                        )
                );

        var investment = new Investment(productId, accountId, valueInvestment);
        var accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.getBalance())) {
            throw new InvestmentAccountWithoutBalanceException(
                    messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance
            );
        }

        if (!investment.verifyProductPrivateOrDefaultForInvestment(accountBalanceVO.getBalance(), product)) {
            throw new InvestmentAccountWithoutBalanceForProductPrivateException(
                    messageExceptionAccountWithoutBalanceForProductPrivate,
                    descriptionExceptionAccountWithoutBalanceForProductPrivate);
        }

        boolean isDebited = accountFacade.debitAccount(accountId, valueInvestment);
        if (!isDebited) {
            throw new InvestmentAccountIsNotDebitException(
                    messageExceptionAccountIfNotDebited,
                    descriptionExceptionAccountIfNotDebited);
        }

        return investmentRepository.save(investment);
    }
}
