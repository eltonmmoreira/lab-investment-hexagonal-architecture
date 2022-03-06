package br.com.lab.investment.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestmentRequest {
    private Long productId;
    private Double value;
}
