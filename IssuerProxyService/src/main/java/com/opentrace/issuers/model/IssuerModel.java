package com.opentrace.issuers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "issuer")
public class IssuerModel {

    @Id
    public String id;

    private String cardHolderName;
    private String cardNumber;
    private String allowedLimit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAllowedLimit() {
        return allowedLimit;
    }

    public void setAllowedLimit(String allowedLimit) {
        this.allowedLimit = allowedLimit;
    }

    @Override
    public String toString() {
        return "IssuerModel{" +
                "id='" + id + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber=" + cardNumber +
                ", allowedLimit=" + allowedLimit +
                '}';
    }
}
