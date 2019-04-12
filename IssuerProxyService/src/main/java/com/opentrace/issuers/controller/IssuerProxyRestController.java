package com.opentrace.issuers.controller;

import com.opentrace.issuers.driver.MongoClientDriver;
import com.opentrace.issuers.dto.AcquirerCardPayRequest;
import com.opentrace.issuers.model.IssuerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IssuerProxyRestController {

    @Autowired
    MongoClientDriver mongoClientDriver;

    @RequestMapping(value = "/IssuerAuthorizePayment",method = RequestMethod.POST)
    public ResponseEntity<String> AuthorizeCardPayFromIssuer(@Valid @RequestBody AcquirerCardPayRequest request, BindingResult bindingResult)  {

        Query query = null;
        IssuerModel issuerModel = null;
        MongoTemplate mongoTemplate =mongoClientDriver.getMongoTemplate();

        try {
            //with in DB if the credit card exists
            query = new Query().addCriteria(Criteria.where("cardNumber").is(request.getCardNumber().toString()));
            issuerModel = mongoTemplate.findOne(query, IssuerModel.class);
            if (!(issuerModel != null && (issuerModel.getCardNumber() != null))) {
                return new ResponseEntity<>("Not Approved -- Card Not Found", HttpStatus.NOT_FOUND);
            }


            //Check in DB card holder name and verify
            query = new Query().addCriteria(Criteria.where("cardHolderName").is(request.getCardHolderName()));
            issuerModel = mongoTemplate.findOne(query, IssuerModel.class);

            //System.out.println(" Card Holder Name : " + issuerModel.getCardHolderName());


            if (!(issuerModel != null && (issuerModel.getCardHolderName() != null && issuerModel.getCardHolderName().equalsIgnoreCase(request.getCardHolderName())))) {
                return new ResponseEntity<>("Not Approved -- Holder Name Miss-match", HttpStatus.NOT_FOUND);
            }

            //check the authorized amount limit..
            query = new Query().addCriteria(Criteria.where("cardNumber").is(request.getCardNumber().toString()));
            issuerModel = mongoTemplate.findOne(query, IssuerModel.class);

            //System.out.println("Allowed Limit : " + issuerModel.toString());

            if (!(issuerModel != null && (issuerModel.getCardHolderName() != null && Double.parseDouble(issuerModel.getAllowedLimit()) >= request.getAmountPayable()))) {
                return new ResponseEntity<>("Not Approved -- Request Amount more than Allowed Limit", HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<String>("Approved", HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Not - Approved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
