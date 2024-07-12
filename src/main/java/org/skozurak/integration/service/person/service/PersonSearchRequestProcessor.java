package org.skozurak.integration.service.person.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.sql.SqlConstants;
import org.skozurak.integration.service.person.model.PersonSearchRequest;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component("personSearchRequestProcessor")
public class PersonSearchRequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // Retrieve the body of the incoming message as a PersonSearchRequest
        PersonSearchRequest request = exchange.getIn().getBody(PersonSearchRequest.class);
        String email = request.getEmail();  // Get the email from the request object

        // Prepare the SQL query. Note the use of named parameters which are safer and prevent SQL injection.
        String sql = "SELECT first_name, last_name, age, email, created_at, updated_at FROM person WHERE email = 'step.kozbvb@gmail.com'";

        // Prepare a map to hold parameters for the query
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);  // Put the email into the map with the key as the parameter name used in the SQL query

        // Set the prepared SQL query and its parameters on the exchange
        // The body of the message is set to the SQL query
        exchange.getIn().setBody(sql);

        // The parameters are added to the message header under the key SqlConstants.SQL_PARAMETERS
        // This header is then used by the Camel SQL component to replace placeholders in the query with actual values
        exchange.getIn().setHeader(SqlConstants.SQL_PARAMETERS, parameters);
    }
}