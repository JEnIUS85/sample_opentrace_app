package com.opentrace.acquirer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acquirer")
public class AcquirerModel {

    @Id
    public String id;

    public String acquirerId;

    public AcquirerModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    @Override
    public String toString() {
        return "AcquirerModel{" +
                "id='" + id + '\'' +
                ", AcquirerId='" + acquirerId + '\'' +
                '}';
    }
}
