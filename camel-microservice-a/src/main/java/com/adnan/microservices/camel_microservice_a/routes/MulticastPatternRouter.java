package com.adnan.microservices.camel_microservice_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MulticastPatternRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        /*
         * Pipeline is the bydefault integration pattern in camel
         */

        /*
         * Multicast pattern is used when you want to send to multiple endpoint in to metho
         */
        from("timer:multicast?period=10000")
                .multicast()
                .to("log:something1","log:something2","log:something3");
    }
}
