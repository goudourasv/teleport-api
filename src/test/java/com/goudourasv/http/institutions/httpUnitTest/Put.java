package com.goudourasv.http.institutions.httpUnitTest;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.InstitutionsResource;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.goudourasv.utils.TestData.createInstitution;
import static com.goudourasv.utils.TestData.createInstitutionCreate;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class Put {
    @InjectMock
    private InstitutionsService institutionsService;


    @Test
    public void shouldReturn200WithValidInstitutionCreate() {
        InstitutionCreate institutionCreate = createInstitutionCreate();
        Institution expectedInstitution = createInstitution();

        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(institutionsService.replaceInstitution(institutionId, institutionCreate)).thenReturn(expectedInstitution);

        Response response = given().contentType(ContentType.JSON)
                .body(institutionCreate)
                .when().put("/{id}", institutionId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Institution institutionResult = response.as(Institution.class);

        Mockito.verify(institutionsService).replaceInstitution(institutionId, institutionCreate);
        assertThat(institutionResult).isEqualTo(expectedInstitution);
    }

    @Test
    public void shouldReturn400WithInvalidInstitutionCreate() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        InstitutionCreate institutionCreate = new InstitutionCreate(null, "England", "Michigan", null);

        given().contentType(ContentType.JSON)
                .body(institutionCreate)
                .when()
                .put("/{id}", institutionId)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("updateInstitution.input.name must not be blank"));
    }
}
