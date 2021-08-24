package com.goudourasv.http.institutions.httpUnitTest;

import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.InstitutionsResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(InstitutionsResource.class)
public class Delete {

    @InjectMock
    private InstitutionsService institutionsService;

    @Test
    public void shouldReturn204WithValidId() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(institutionsService.deleteSpecificCourse(institutionId)).thenReturn(true);

        when().delete("{id}", institutionId)
                .then()
                .statusCode(204);

        Mockito.verify(institutionsService).deleteSpecificCourse(institutionId);
    }

    @Test
    public void shouldReturn404WithInvalidId() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(institutionsService.deleteSpecificCourse(institutionId)).thenReturn(false);

        when().delete("{id}", institutionId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Institution with id: " + institutionId + "doesn't exist"));

        Mockito.verify(institutionsService).deleteSpecificCourse(institutionId);
    }

    @Test
    public void shouldReturn500WhenRuntimeException(){
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Exception exception = new RuntimeException();

        Mockito.when(institutionsService.deleteSpecificCourse(institutionId)).thenThrow(exception);

        when().delete("{id}",institutionId)
                .then()
                .statusCode(500)
                .assertThat()
                .body("message",equalTo("Something went wrong"));

        Mockito.verify(institutionsService).deleteSpecificCourse(institutionId);
    }

}
