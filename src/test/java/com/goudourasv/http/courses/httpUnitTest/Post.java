package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.http.courses.CoursesResource;
import com.goudourasv.http.courses.dto.CourseCreate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.ServerErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.goudourasv.utils.TestData.createCourse;
import static com.goudourasv.utils.TestData.createCourseCreate;
import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class Post {

    @InjectMock
    private CoursesService coursesService;


    @Test
    public void shouldReturn201WhenValidCourseCreate() {
        CourseCreate courseCreate = createCourseCreate();
        Course expectedCourse = createCourse();

        String path = "http://localhost:8081/courses/";
        String expectedLocation = path + expectedCourse.getId();

        Mockito.when(coursesService.createCourse(courseCreate)).thenReturn(expectedCourse);

        Response response = with().body(courseCreate)
                .contentType(ContentType.JSON)
                // RestAssured doesn't set the trailing slash properly
                .basePath("/courses/")
                .when().post()
                .then()
                .statusCode(201)
                .extract()
                .response();

        Course courseResult = response.as(Course.class);

        Mockito.verify(coursesService).createCourse(courseCreate);
        assertThat(response.getHeader("Location")).isEqualTo(expectedLocation);
        assertThat(courseResult).isEqualTo(expectedCourse);
    }

    @Test
    public void shouldReturn400WhenInvalidCourseCreate() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        List<String> tags = new ArrayList<>();

        CourseCreate courseCreate = new CourseCreate(null, institutionId, tags, instructorId, null, null, null);

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("createCourse.courseCreate.title must not be blank"));
    }

    @Test
    public void shouldReturn500WhenDataBaseError() {
        CourseCreate courseCreate = createCourseCreate();
        Exception exception = new ServerErrorException("Database error", 504);

        Mockito.when(coursesService.createCourse(courseCreate)).thenThrow(exception);

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(504)
                .assertThat()
                .body("message", equalTo("Something went wrong"));
        Mockito.verify(coursesService).createCourse(courseCreate);

    }

    @Test
    public void shouldReturn500WhenGenericError() {
        CourseCreate courseCreate = createCourseCreate();
        // TODO: Change to runtime exception
        Exception exception = new RuntimeException();

        Mockito.when(coursesService.createCourse(courseCreate)).thenThrow(exception);

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(500)
                .assertThat()
                .body("message", equalTo("Something went wrong"));

        Mockito.verify(coursesService).createCourse(courseCreate);

    }
}
