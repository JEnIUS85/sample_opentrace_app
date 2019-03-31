package com.sample.opentrace.controller;

import com.sample.opentrace.dto.AcquirerCardPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.naming.Binding;
import javax.validation.Valid;

@RestController
public class ProxyServiceRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AcquirerCardPayRequest acquirerCardPayRequest;


    @RequestMapping(value = "/CardPayment",method = RequestMethod.POST)
    public ResponseEntity<String> makeCardMethod(@Valid @RequestBody AcquirerCardPayRequest request, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new Exception();
            //return new ResponseEntity<>("Error Occurred In Processing Card Payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            return new ResponseEntity<>("Hello",HttpStatus.OK);
        }
        finally {
          //  childSpan.finish();
        }
    }


}
