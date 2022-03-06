package br.com.lab.investment.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long accountId;

    private Double value;

    @CreationTimestamp
    private LocalDateTime date;

    private boolean privateInvestment;

    public Investment(){}

    public Investment(Long productId, Long accountId, Double value) {
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
    }

    public boolean sufficientBalanceForInvestment(Double accountBalance) {
        return this.value < accountBalance;
    }

    public boolean verifyProductPrivateOrDefaultForInvestment(Double accountBalance,
                                                              Product product) {
        privateInvestment = product.isPrivateInvestment();
        return !privateInvestment ||
                accountBalance >= product.getMinimumValueForInvestment();
    }

}
