package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.http.courses.CoursesResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class Delete {

    @InjectMock
    private CoursesService coursesService;

    @Test
    public void shouldReturn204WithValidId() {
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        Mockito.when(coursesService.deleteSpecificCourse(courseId)).thenReturn(true);

        when().delete("{id}", courseId)
                .then()
                .statusCode(204);

        Mockito.verify(coursesService).deleteSpecificCourse(courseId);
    }

    @Test
    public void shouldReturn404WithInvalidId() {
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        Mockito.when(coursesService.deleteSpecificCourse(courseId)).thenReturn(false);

        when().delete("{id}", courseId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Course with id: " + courseId + "doesn't exist"));

        Mockito.verify(coursesService).deleteSpecificCourse(courseId);

    }

    @Test
    public void shouldReturn500WhenRuntimeException() {
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        Exception exception = new RuntimeException();

        Mockito.when(coursesService.deleteSpecificCourse(courseId)).thenThrow(exception);

        when().delete("{id}", courseId)
                .then()
                .statusCode(500)
                .assertThat()
                .body("message", equalTo("Something went wrong"));


        Mockito.verify(coursesService).deleteSpecificCourse(courseId);


    }
}
