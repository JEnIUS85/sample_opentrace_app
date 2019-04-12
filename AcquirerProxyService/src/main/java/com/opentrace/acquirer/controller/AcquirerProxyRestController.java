package com.opentrace.acquirer.controller;

import com.opentrace.acquirer.driver.MongoClientDriver;
import com.opentrace.acquirer.dto.AcquirerCardPayRequest;
import com.opentrace.acquirer.model.AcquirerModel;
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
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/** Author - Nitin K Sharma
 *  URl - /CardPayment
 *  Inputs - AcquirerID, CardNumber, CardHolderName, Amount
 *  Outupt - Approved or Not-Approved
 */
@RestController
public class AcquirerProxyRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MongoClientDriver mongoClientDriver;


    @RequestMapping(value = "/CardPayment",method = RequestMethod.POST)
    public ResponseEntity<String> AuthenticateAqurier(@Valid @RequestBody AcquirerCardPayRequest request, BindingResult bindingResult)  throws  Exception {

        if(bindingResult.hasErrors()){
            throw new Exception();
            //return new ResponseEntity<>("Error Occurred In Processing Card Payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {

            Query query = new Query();
            query.addCriteria(Criteria.where("acquirerId").is(request.getAcquirerId()));
            AcquirerModel acquirerModel =mongoClientDriver.getMongoTemplate().findOne(query,AcquirerModel.class);

            if(acquirerModel !=null && acquirerModel.getAcquirerId() !=null && acquirerModel.getAcquirerId() !=""){

                //call another service for CardPaymentAuthorization.
                ResponseEntity<String> res =restTemplate.postForEntity("http://localhost:9090/IssuerAuthorizePayment",request,String.class);

                return new ResponseEntity<>("Approved - Card Charged",HttpStatus.OK);

            }

            return new ResponseEntity<>("Not - Approved",HttpStatus.NOT_FOUND);


        }
        catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Not - Approved",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
