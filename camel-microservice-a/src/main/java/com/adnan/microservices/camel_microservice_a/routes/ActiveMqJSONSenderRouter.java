package com.adnan.microservices.camel_microservice_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqJSONSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

/*       //Simple Timer used as a receiver
        from("timer:active-mq-timer?period=10000")
                .transform().constant("My Message for Active MQ")
                .log("${body}")
                .to("activemq:my-activemq-queue"); //queue*/

        //Here we are reading from a file
        from("file:files/json")
                .log("${body}")
                .to("activemq:my-activemq-queue");

    }
}
