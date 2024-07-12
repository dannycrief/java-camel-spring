package org.skozurak.integration.service.person.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonSearchResponse {

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
