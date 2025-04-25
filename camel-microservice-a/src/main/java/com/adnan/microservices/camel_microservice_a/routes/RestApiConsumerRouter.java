package com.adnan.microservices.camel_microservice_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /*
             Configuration for host and port for the api that is to be consumed
         */
        restConfiguration().host("localhost").port(8081);

        from("timer:rest-api-consumer?period=10000") // Scheduler working as a Producer
                .setHeader("from", () -> "USD")
                .setHeader("to", () -> "PKR")
                .log("${body}")
                .to("rest:get:/api/currency-exchange/from/{from}/to/{to}") //Api to be consumed
                .log("${body}");
    }
}
