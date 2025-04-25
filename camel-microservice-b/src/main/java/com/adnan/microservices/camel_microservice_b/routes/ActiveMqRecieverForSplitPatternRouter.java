package com.adnan.microservices.camel_microservice_b.routes;

import com.adnan.microservices.camel_microservice_b.bean.CurrencyExhange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqRecieverForSplitPatternRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:split-queue")
                .to("log:recieved-message-from-active-mq-for-split-pattern");

    }
}
