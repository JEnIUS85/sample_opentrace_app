package com.sample.opentrace.dto;

import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
@Configuration
public class AcquirerCardPayRequest {

    @NotNull
    //@Size (max=16,message="Card Number should be 10 digit long")
    private Long CardNumber;

    @NotNull
    //@Size (max=40,message="Name should be at least 3 Characters long")
    private String CardHolderName;

    @NotNull
   // @Size (min=0,message="Amount Charged on Card should be a positive value")
    private double AmountPayable;

    @NotNull
    //@Size (min=5,message="Acquirer ID should be 5 or more character long passcode")
    private String AcquirerId;

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        CardHolderName = cardHolderName;
    }

    public double getAmountPayable() {
        return AmountPayable;
    }

    public void setAmountPayable(double amountPayable) {
        AmountPayable = amountPayable;
    }

    public String getAcquirerId() {
        return AcquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        AcquirerId = acquirerId;
    }
}
