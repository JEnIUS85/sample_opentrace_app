package com.sample.opentrace;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleOpenTraceApplication {

    @Bean
    public io.opentracing.Tracer jaegerTracer(){
        return new Configuration("helloService").getTracer();
    //, new Configuration.SamplerConfiguration(),
        //                new Configuration.ReporterConfiguration()

        //return Configuration.fromEnv().getTracer();
    }
    public static void main(String [] args){
        SpringApplication.run(SampleOpenTraceApplication.class,args);
    }
}
