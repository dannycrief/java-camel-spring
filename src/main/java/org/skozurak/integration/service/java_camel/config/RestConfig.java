package org.skozurak.integration.service.java_camel.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends RouteBuilder {
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .clientRequestValidation(true)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "skozurak REST API")
                .apiProperty("api.version", "1.0.0");
    }
}
