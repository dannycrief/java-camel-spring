package org.skozurak.integration.service.java_camel.main.common.exception;

import org.skozurak.integration.service.java_camel.main.common.model.BaseResponse;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DefaultExceptionProcessor implements Processor {
    private static final int HTTP_RESPONSE_ERROR = 500;


    @Override
    public void process(Exchange exchange) throws Exception {
        val exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.error("Exception", exception);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HTTP_RESPONSE_ERROR);
        exchange.getIn().setBody(getErrorResponse(exception));
    }

    private BaseResponse getErrorResponse(Exception exception) {
        val builder = BaseResponse.builder().status("ERROR");
        if (exception != null) {
            builder.errorDesc(exception.getMessage());
        } else {
            builder.errorDesc("Service unknown error");
        }
        return builder.build();
    }
}
