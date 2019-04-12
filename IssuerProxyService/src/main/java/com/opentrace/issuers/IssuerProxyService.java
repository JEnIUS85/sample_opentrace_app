package com.opentrace.issuers;

import io.jaegertracing.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IssuerProxyService {


    @Value("${TRACER_ENDPOINT}")
    private String TRACER_ENDPOINT;

    @Value("${SERVICE_NAME}")
    private String SERVICE_NAME;

    @Value("${TRACER_REPORTER_LOG_SPANS}")
    private boolean TRACER_REPORTER_LOG_SPANS;

    @Value("${SAMPLER_TYPE}")
    private String SAMPLER_TYPE;

    @Value("${SAMPLER_PARAM}")
    private int SAMPLER_PARAM;

    @Bean
    public io.opentracing.Tracer jaegerTracer(){
       // return new Configuration("IssuerProxyService").getTracer();

        Configuration.SamplerConfiguration samplerConfiguration = new Configuration.SamplerConfiguration()
                .withType(SAMPLER_TYPE)//ConstSampler.TYPE
                .withParam(SAMPLER_PARAM);//1

        Configuration.SenderConfiguration senderConfiguration = new Configuration.SenderConfiguration()
                .withEndpoint(TRACER_ENDPOINT);
                //.withEndpoint("http://localhost:14268/api/traces");

        Configuration.ReporterConfiguration reporterConfiguration = new Configuration.ReporterConfiguration()
                .withLogSpans(TRACER_REPORTER_LOG_SPANS)//true
                .withFlushInterval(1000)
                .withMaxQueueSize(1000)
                .withLogSpans(false)
                .withSender(senderConfiguration);

        return new Configuration(SERVICE_NAME)//"IssuerProxyService"
                .withSampler(samplerConfiguration)
                .withReporter(reporterConfiguration)
                .getTracer();
		
        //return Configuration.fromEnv().getTracer();
    }




    public static void main(String [] args){
        SpringApplication.run(IssuerProxyService.class,args);
    }
}
