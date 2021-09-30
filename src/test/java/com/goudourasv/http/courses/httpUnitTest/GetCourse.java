package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.http.courses.CoursesResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class GetCourse {
    @InjectMock
    private CoursesService coursesService;


    @Test
    public void shouldReturn200WithExpectedCourseId() {

        UUID courseId = UUID.fromString("85fb9103-bacb-4933-b67d-d0bcf438ca29");
        Course expectedCourse = new Course(courseId, null,null,null,null,null,null,null,null,false);

        Mockito.when(coursesService.getSpecificCourse(courseId)).thenReturn(expectedCourse);

        Course courseResponse = when().get("/{id}", courseId)
                .then()
                .statusCode(200)
                .extract()
                .as(Course.class);

        Mockito.verify(coursesService).getSpecificCourse(courseId);
        assertThat(courseResponse).isEqualTo(expectedCourse);
    }

    @Test
    public void shouldReturn404WhenNotFoundCourseId() {
        UUID courseId = UUID.fromString("85fb9103-bacb-4933-b67d-d0bcf438ca20");

        Mockito.when(coursesService.getSpecificCourse(courseId)).thenReturn(null);

        when().get("/{id}", courseId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("HTTP 404 Not Found"));

        Mockito.verify(coursesService).getSpecificCourse(courseId);
    }
}
