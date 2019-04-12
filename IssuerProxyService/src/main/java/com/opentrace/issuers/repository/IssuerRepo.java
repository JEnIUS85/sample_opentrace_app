package com.opentrace.issuers.repository;

import com.opentrace.issuers.model.IssuerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssuerRepo extends MongoRepository<IssuerModel,String>{
    public IssuerModel findByCardNumber(int cardNumber);

    public IssuerModel findByCardHolderName(String cardHolderName);

    public IssuerModel findByAllowedLimit(Integer allowedLimit);
}


