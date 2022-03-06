package br.com.lab.investment.domain.exception;

public class InvestmentAccountWithoutBalanceException extends RuntimeException {
    private String description;

    public String getDescription() {
        return this.description;
    }

    public InvestmentAccountWithoutBalanceException(){
        super();
    }

    public InvestmentAccountWithoutBalanceException(String message, String description) {
        super(message);

        this.description = description;
    }
}
