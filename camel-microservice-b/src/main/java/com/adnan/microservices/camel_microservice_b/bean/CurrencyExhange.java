package com.adnan.microservices.camel_microservice_b.bean;

import java.math.BigDecimal;

public class CurrencyExhange {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

    public void setTo(String to) {
        this.to = to;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public CurrencyExhange(){

    }

    public CurrencyExhange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public String getTo() {
        return to;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CurrencyExhange{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }
}
