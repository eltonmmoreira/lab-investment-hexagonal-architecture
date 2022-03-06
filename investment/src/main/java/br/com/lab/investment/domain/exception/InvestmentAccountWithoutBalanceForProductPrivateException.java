package br.com.lab.investment.domain.exception;

public class InvestmentAccountWithoutBalanceForProductPrivateException extends RuntimeException {

    private String description;

    public String getDescription() {
        return this.description;
    }

    public InvestmentAccountWithoutBalanceForProductPrivateException(){
        super();
    }

    public InvestmentAccountWithoutBalanceForProductPrivateException(String message, String description) {
        super(message);

        this.description = description;
    }
}
