package com.opentrace.acquirer.driver;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import io.opentracing.Tracer;
import io.opentracing.contrib.mongo.TracingMongoClient;
import io.opentracing.contrib.mongo.providers.MongoSpanNameProvider;
import io.opentracing.contrib.mongo.providers.PrefixSpanNameProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoClientDriver {

    @Autowired
    Tracer jaegerTracer;

    @Value("${spring.data.mongodb.host}")
    private String mongodb_host;

    @Value("${spring.data.mongodb.port}")
    private String mongodb_port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    private static MongoTemplate mongoTemplate=null;

    public MongoTemplate getMongoTemplate(){
        if(mongoTemplate ==null) {

            MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder().applicationName("IssuerProxyService-MongoDB").build();

            List<ServerAddress> seeds = new ArrayList<ServerAddress>();
            seeds.add(new ServerAddress(mongodb_host, Integer.parseInt(mongodb_port)));

            MongoSpanNameProvider spanNameProvide = new PrefixSpanNameProvider("MongoDB-Operation:");

            MongoClient mongoClient = new TracingMongoClient(jaegerTracer, seeds, mongoClientOptions, spanNameProvide);

            //System.out.println("Address - " + mongoClient.getAddress().toString()+ "  toString : " + mongoClient.toString());

            mongoTemplate = new MongoTemplate(mongoClient, database);

            return mongoTemplate;
        }
        else{return mongoTemplate;}

    }

}
