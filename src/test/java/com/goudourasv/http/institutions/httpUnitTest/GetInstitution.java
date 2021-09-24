package com.goudourasv.http.institutions.httpUnitTest;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.InstitutionsResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class GetInstitution {
    @InjectMock
    private InstitutionsService institutionsService;

    @Test
    public void shouldReturn200WithExpectedId() {
        Institution expectedInstitution = createInstitution();

        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(institutionsService.getSpecificInstitution(institutionId)).thenReturn(expectedInstitution);

        Institution institutionResponse = given().contentType(ContentType.JSON)
                .when().get("/{id}", institutionId)
                .then()
                .statusCode(200)
                .extract()
                .as(Institution.class);

        Mockito.verify(institutionsService).getSpecificInstitution(institutionId);
        assertThat(institutionResponse).isEqualTo(expectedInstitution);
    }

    @Test
    public void shouldReturn404WithInvalidInstitutionId() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(institutionsService.getSpecificInstitution(institutionId)).thenReturn(null);

        when().get("/{id}", institutionId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Http 404 NotFound"));

        Mockito.verify(institutionsService).getSpecificInstitution(institutionId);


    }
}
