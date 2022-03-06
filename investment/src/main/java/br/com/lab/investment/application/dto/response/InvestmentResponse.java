package br.com.lab.investment.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvestmentResponse {
    private Long id;
    private Double value;
    private LocalDateTime date;

    public InvestmentResponse(Long id, Double value, LocalDateTime date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public InvestmentResponse() {
    }
}
