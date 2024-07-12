package org.skozurak.integration.service.person.service;

import lombok.val;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.skozurak.integration.service.person.model.PersonSearchResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Component("personSearchResponseProcessor")
public class PersonSearchResponseProcessor implements Processor {

    private final ConversionService conversionService;

    public PersonSearchResponseProcessor(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        val message = exchange.getIn();
        val result = message.getBody(List.class);

        if (result != null && !result.isEmpty()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> row = (Map<String, Object>) result.get(0);

            PersonSearchResponse response = PersonSearchResponse.builder()
                    .firstName((String) row.get("first_name"))
                    .lastName((String) row.get("last_name"))
                    .age((Integer) row.get("age"))
                    .email((String) row.get("email"))
                    .createdAt((Timestamp) row.get("created_at"))
                    .updatedAt((Timestamp) row.get("updated_at"))
                    .build();

            message.setBody(response);
        } else {
            // Handle case where no result is found
            message.setBody(new PersonSearchResponse());
        }
    }
}