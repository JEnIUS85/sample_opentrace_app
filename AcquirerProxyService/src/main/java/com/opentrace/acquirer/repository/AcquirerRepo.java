package com.opentrace.acquirer.repository;

import com.opentrace.acquirer.model.AcquirerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcquirerRepo extends MongoRepository<AcquirerModel,String>{
    public AcquirerModel findByAcquirerId(String acquirerId);
}


