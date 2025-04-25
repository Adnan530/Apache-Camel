package com.adnan.microservices.camel_microservice_b.routes;

import com.adnan.microservices.camel_microservice_b.bean.CurrencyExhange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActiveMqRecieverRouter extends RouteBuilder {


    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;


    @Override
    public void configure() throws Exception {
        //JSON Formatting for incoming message
        //Jackson library is used for formating
        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExhange.class)
                .bean(myCurrencyExchangeProcessor)
                .to("log:recieved-message-from-active-mq");
    }
}

@Component
class MyCurrencyExchangeProcessor{
    Logger logger= LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
    public void processMessage(CurrencyExhange currencyExhange){
            logger.info("Do some processing {}",currencyExhange.getConversionMultiple());
            currencyExhange.getConversionMultiple();
    }
}

@Component
class MyCurrencyExchangeTransformer{
    Logger logger= LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);
    public CurrencyExhange processMessage(CurrencyExhange currencyExhange){
        logger.info("Do some Transforming {}",currencyExhange.getConversionMultiple().multiply(BigDecimal.TEN));
        currencyExhange.setConversionMultiple(currencyExhange.getConversionMultiple().multiply(BigDecimal.TEN));
        return currencyExhange;
    }
}


