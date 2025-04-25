package com.adnan.microservices.camel_microservice_a.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// @Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {

        //Processing can be done using .bean and .process method
        //Transformation can be done using .bean and .transform method


        from("timer:first-timer") //end-point this can be queue, producer api or anyhthing
                .log("${body}")
                // .transform().constant("My constant Message")
                .transform().constant("Time Right Now is: "+ LocalDateTime.now())
                .log("${body}")
                //  .bean("getCurrentTimeBean") //Directly calling bean Bad Practices use autowired because if class name change you have to change the name here as well
              //  .bean(getCurrentTimeBean,"getCurrentTime") // When you have only one method in the bean do not specify the method name
                .bean(getCurrentTimeBean,"getCurrentTime") // When you have only one method in the bean do specify the method name
                .log("${body}")
                .process(new SimpleLoggingProcessor()) // process method is used for processing
                .bean(simpleLoggingProcessingComponent)
                .log("${body}")
                    .to("log:first-timer"); //end point this can be another api, database or any transport protocol

    }
}

@Component
class GetCurrentTimeBean{
    public String getCurrentTime(){
        return "Time Now is" + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent{

    private Logger logger= LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void processing(String message){
                logger.info("SimpleLoggingProcessingComponent {}",message);
    }
}

class SimpleLoggingProcessor implements Processor{

    private Logger logger= LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessingComponent {}",exchange.getMessage().getBody());
    }
}
