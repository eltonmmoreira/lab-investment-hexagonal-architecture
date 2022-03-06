package br.com.lab.investment.api.handler;

import br.com.lab.investment.application.dto.response.ErrorMessageResponse;
import br.com.lab.investment.domain.exception.InvestmentAccountIsNotDebitException;
import br.com.lab.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.investment.domain.exception.InvestmentProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final String DEFAULT_MESSAGE_ERROR = "Não foi possível processar sua requisição";

    @ExceptionHandler(InvestmentProductNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorProductNotFound(InvestmentProductNotFoundException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(InvestmentAccountWithoutBalanceException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceForProductPrivateException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountWithoutBalanceForProductPrivateException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountIsNotDebitException.class)
    public ResponseEntity<ErrorMessageResponse> errorIsNotDebited(InvestmentAccountIsNotDebitException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> generalError(RuntimeException exception) {
        return getErrorMessageResponse(exception.getMessage(), DEFAULT_MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message, String description, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .body(new ErrorMessageResponse(
                        httpStatus.value(),
                        LocalDateTime.now(),
                        message,
                        description
                        )
                );
    }

}
