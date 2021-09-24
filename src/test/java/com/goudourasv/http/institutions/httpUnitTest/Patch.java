package com.goudourasv.http.institutions.httpUnitTest;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.InstitutionsResource;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.goudourasv.utils.TestData.createInstitution;
import static com.goudourasv.utils.TestData.createInstitutionUpdate;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class Patch {

    @InjectMock
    private InstitutionsService institutionsService;

    @Test
    public void shouldReturn200WhenValidInstitutionUpdate() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        InstitutionUpdate institutionUpdate = createInstitutionUpdate();
        Institution expectedInstitution = createInstitution();

        Mockito.when(institutionsService.partiallyUpdateInstitution(institutionId, institutionUpdate)).thenReturn(expectedInstitution);
        Response response = given().contentType(ContentType.JSON)
                .body(institutionUpdate)
                .when().patch("/{id}", institutionId)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .response();

        Institution institutionResult = response.as(Institution.class);

        Mockito.verify(institutionsService).partiallyUpdateInstitution(institutionId, institutionUpdate);
        assertThat(institutionResult).isEqualTo(expectedInstitution);
    }
}
