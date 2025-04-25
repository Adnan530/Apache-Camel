package com.adnan.microservices.camel_microservice_a.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SplitPatternRouter extends RouteBuilder {

    @Autowired
    private Splitter splitter;

    @Override
    public void configure() throws Exception {
        /*
         * Split pattern is used to break the message as single output msg
         * Each line in csv act like a separate message
         */

        //One way of Doing
        /*from("file:files/csv")
                .log("${body}")
                .unmarshal().csv()
                .split(body())
                .to("activemq:split-queue");
*/
        /*
            Another way of using and splitting the data using delimiter
         */
        // Incoming message,message1, message3
        from("file:files/csv")
                .log("${body}")
                .convertBodyTo(String.class) //Converting incoming message into String
                .split(body(),",") //second way of doing
                .split(method(splitter))
                .to("activemq:split-queue");
    }
}
//Third way of doing
/*
  Create a separate component in which you will take the body process it do whatever is business
  requirement transform it, send it to queue, perform db operation and return it
 */
@Component
class Splitter{
    public List<String> splitInput(String body){
        return List.of("Adnan","Ali","Jamali");
    }
}
