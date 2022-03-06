package br.com.lab.investment.api;

import br.com.lab.investment.application.InvestmentApplication;
import br.com.lab.investment.application.dto.request.InvestmentRequest;
import br.com.lab.investment.application.dto.response.InvestmentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class InvestmentController {

    private final InvestmentApplication investmentApplication;

    public InvestmentController(InvestmentApplication investmentApplication) {
        this.investmentApplication = investmentApplication;
    }

    @PostMapping("/{accountId}/investment")
    public ResponseEntity<InvestmentResponse> invest(@PathVariable long accountId, @RequestBody InvestmentRequest investmentRequest) {
        var investmentResponse = investmentApplication.invest(accountId, investmentRequest);
        return ResponseEntity.ok(investmentResponse);
    }
}
