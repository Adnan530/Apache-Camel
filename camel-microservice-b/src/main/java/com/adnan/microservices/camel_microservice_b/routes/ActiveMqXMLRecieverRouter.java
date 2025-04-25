package com.adnan.microservices.camel_microservice_b.routes;

import com.adnan.microservices.camel_microservice_b.bean.CurrencyExhange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqXMLRecieverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //XML Formatting for incoming message
        //Jackson library is used for formating
        from("activemq:my-activemq-xml-queue")
                .unmarshal().jacksonXml(CurrencyExhange.class)
                .to("log:recieved-message-from-active-mq");

    }
}
