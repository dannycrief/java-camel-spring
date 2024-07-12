package org.skozurak.integration.service.person;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.skozurak.integration.service.person.model.PersonSearchRequest;
import org.skozurak.integration.service.person.model.PersonSearchResponse;
import org.springframework.http.MediaType;

@Component
public class PersonSearchRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        // Handling exceptions
        onException(Exception.class)
                .handled(true)
                .log("Exception caught in route: ${exception.message}")
                .setBody().constant("Error processing request");

        // Define the REST endpoint
        rest("/person/search")
                .post()
                .type(PersonSearchRequest.class)
                .outType(PersonSearchResponse.class)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .routeId("personSearchRoute")
                .log("Received request to search for a person by email")
//                .setBody(constant("SELECT first_name, last_name, age, email, created_at, updated_at FROM person WHERE email = 'step.kozbvb@gmail.com'"))
                .process("personSearchRequestProcessor")
                .to("jdbc:dataSource") // Using JDBC component with direct query
                .log("SQL Query executed, processing result")
                .process("personSearchResponseProcessor")
                .log("Response ready to be sent to the client");

    }
}