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

import static com.goudourasv.utils.TestData.createInstitution;
import static com.goudourasv.utils.TestData.createInstitutionCreate;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class Post {
    @InjectMock
    private InstitutionsService institutionsService;

    @Test
    public void shouldReturn201CreatedWithValidInstitutionCreate() {
        InstitutionCreate institutionCreate = createInstitutionCreate();
        Institution expectedInstitution = createInstitution();

        String path = "http://localhost:8081/institutions/";
        String expectedLocation = path + expectedInstitution.getId();

        Mockito.when(institutionsService.createInstitution(institutionCreate)).thenReturn(expectedInstitution);

        Response response = given().contentType(ContentType.JSON)
                .body(institutionCreate)
                .basePath("/institutions/")
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response();

        Institution institutionResponse = response.as(Institution.class);

        Mockito.verify(institutionsService).createInstitution(institutionCreate);
        assertThat(institutionResponse).isEqualTo(expectedInstitution);
        assertThat(response.getHeader("Location")).isEqualTo(expectedLocation);

    }

    @Test
    public void shouldReturn400WithInvalidInstitutionCreate() {
        InstitutionCreate institutionCreate = new InstitutionCreate(null, "Greece", "Thessaloniki", null);

        given().body(institutionCreate).contentType(ContentType.JSON)
                .when().post()
                .then().statusCode(400)
                .assertThat()
                .body("message", equalTo("createInstitution.institutionCreate.name must not be blank"));
    }


    @Test
    public void shouldReturn500WhenGenericError() {
        InstitutionCreate institutionCreate = createInstitutionCreate();
        Exception exception = new NullPointerException();

        Mockito.when(institutionsService.createInstitution(institutionCreate)).thenThrow(exception);

        given().contentType(ContentType.JSON)
                .body(institutionCreate)
                .when().post()
                .then()
                .statusCode(500)
                .assertThat()
                .body("message", equalTo("Something went wrong"));

        Mockito.verify(institutionsService).createInstitution(institutionCreate);


    }
}
