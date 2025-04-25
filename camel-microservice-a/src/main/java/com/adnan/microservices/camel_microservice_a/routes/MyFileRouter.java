package com.adnan.microservices.camel_microservice_a.routes;

import com.adnan.microservices.camel_microservice_a.bean.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    //Playing With File
    @Override
    public void configure() throws Exception {
        /*
            In this method we are moving files from one folder to another folder
         */
        from("file:files/input")
                /*
                 * We can create a unique route id for any route for logging and debugging purpose
                 */
                .routeId("Files-Input-Route")
                /*
                 * Transforming into String
                 */
                .transform().body(String.class)
                /*
                 * Choice is used for conditions same as IF condition in Java
                 */
                .choice()
                /*
                 * In when method we are passing a separte bean where business logic is there
                 */
                .when(method(deciderBean)) //
                /*
                 * Content Based Routing - Based on the content we do routing - by using choice() method
                 */
                .when(simple("${file:ext} ends with 'xml'")) //ends with operator of simple language, == please read the documentation
                .log("XML FILE")
                .when(simple("${body} contains 'USD'"))
                .log("Not an XML File But Contains USD")
                .otherwise()
                .log("Not an XML File")
                .end()
                //end choice
                .log("${body}")
                .log("${messageHistory} ${headers.CamelFileAbsolute}")
                .log("${messageHistory} ${file:absolute.path}")
                /*
                 * Calling Reusable route that I have created for logging purpose
                 */
                .to("direct:log-file-values")
                    .to("file:files/output");
    }
}
