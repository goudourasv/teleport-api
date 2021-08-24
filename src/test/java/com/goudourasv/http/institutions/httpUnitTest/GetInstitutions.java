package com.goudourasv.http.institutions.httpUnitTest;


import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.InstitutionsResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.ServerErrorException;
import java.util.List;

import static com.goudourasv.utils.TestData.createInstitutions;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class GetInstitutions {

    @InjectMock
    private InstitutionsService institutionsService;

    @Test
    public void ShouldReturn200WithoutAnyParam() {
        List<Institution> expectedInstitutions = createInstitutions();
        Mockito.when(institutionsService.getFilteredInstitutions(null, null)).thenReturn(expectedInstitutions);

        Response response = given().contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Institution> institutionsResponse = response.getBody().jsonPath().getList(".", Institution.class);

        Mockito.verify(institutionsService).getFilteredInstitutions(null, null);
        assertThat(institutionsResponse).hasSameSizeAs(expectedInstitutions).hasSameElementsAs(expectedInstitutions);
    }

    @Test
    public void ShouldReturn200WithCountryParam() {
        List<Institution> expectedInstitutions = createInstitutions();
        String country = "Greece";

        Mockito.when(institutionsService.getFilteredInstitutions(country, null)).thenReturn(expectedInstitutions);

        Response response = given().contentType(ContentType.JSON)
                .queryParam("country", country)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Institution> institutionsResponse = response.getBody().jsonPath().getList(".", Institution.class);

        Mockito.verify(institutionsService).getFilteredInstitutions(country, null);
        assertThat(institutionsResponse).hasSameSizeAs(expectedInstitutions).hasSameElementsAs(expectedInstitutions);
    }

    @Test
    public void ShouldReturn200WithCountryAndCityParams() {
        List<Institution> expectedInstitutions = createInstitutions();
        String country = "Greece";
        String city = "Thessaloniki";

        Mockito.when(institutionsService.getFilteredInstitutions(country, city)).thenReturn(expectedInstitutions);

        Response response = given().contentType(ContentType.JSON)
                .queryParam("country", country)
                .queryParam("city", city)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Institution> institutionsResponse = response.getBody().jsonPath().getList(".", Institution.class);

        Mockito.verify(institutionsService).getFilteredInstitutions(country, city);
        assertThat(institutionsResponse).hasSameSizeAs(expectedInstitutions).hasSameElementsAs(expectedInstitutions);
    }

    @Test
    public void ShouldReturn500ServerErrorException() {

        Exception exception = new ServerErrorException("Database error", 504);
        Mockito.when(institutionsService.getFilteredInstitutions(null, null)).thenThrow(exception);

        given().contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(504)
                .assertThat()
                .body("message", equalTo("Something went wrong"));

        Mockito.verify(institutionsService).getFilteredInstitutions(null, null);
    }
}
