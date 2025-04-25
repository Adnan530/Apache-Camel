package com.adnan.microservices.camel_microservice_b.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:My-first-Timer")
                .transform().constant("Adnan Jamali")
                .to("log:My-first-Timer");
    }
}
