package br.com.lab.investment.application.dto.request;

import lombok.Data;

@Data
public class DebitAccountRequest {
    private Double valueOfDebit;

    public DebitAccountRequest(){}

    public DebitAccountRequest(Double valueOfDebit) {
        this.valueOfDebit = valueOfDebit;
    }
}
