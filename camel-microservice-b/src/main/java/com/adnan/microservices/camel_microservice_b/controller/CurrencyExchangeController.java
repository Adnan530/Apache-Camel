package com.adnan.microservices.camel_microservice_b.controller;

import com.adnan.microservices.camel_microservice_b.bean.CurrencyExhange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExhange getCurrencyExchangeRate(
            @PathVariable String from,
            @PathVariable String to
    ){
        return new CurrencyExhange(1000L,from,to, BigDecimal.TEN);
    }
}
