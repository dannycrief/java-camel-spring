package org.skozurak.integration.service.java_camel.main;

import lombok.val;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.model.OnExceptionDefinition;

import java.util.function.Consumer;


public abstract class BaseWsServiceRouterBuilder extends RouteBuilder {

    protected CxfEndpoint configureCxfEndpoint(Consumer<CxfEndpoint> configure) {
        val cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setCamelContext(getCamelContext());
        configure.accept(cxfEndpoint);
        return cxfEndpoint;
    }

    protected void configureOnException() {
        configureOnException(onException -> {
            onException.process("defaultExceptionProcessor").removeHeader("*");
        });
    }

    protected void configureOnException(Consumer<OnExceptionDefinition> configurer) {
        val definition = onException(Exception.class).handled(true);
        configurer.accept(definition);
        definition.stop();
    }
}
