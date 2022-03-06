package br.com.lab.investment.domain.service.facade.valueObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBalanceVO {
    private Long accountId;
    private Double balance;
}
