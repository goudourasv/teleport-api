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
public class Put {

    @InjectMock
    private CoursesService coursesService;

    @Test
    public void shouldReturn200WithValidCourseCreate() {
        CourseCreate courseCreate = createCourseCreate();
        Course expectedCourse = createCourse();
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        Mockito.when(coursesService.replaceCourse(courseCreate, courseId)).thenReturn(expectedCourse);

        Response response = with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().put("/{id}", courseId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Course courseResult = response.as(Course.class);

        Mockito.verify(coursesService).replaceCourse(courseCreate, courseId);
        assertThat(courseResult).isEqualTo(expectedCourse);
    }

    @Test
    public void should400WithInvalidCourseCreate() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        List<String> tags = new ArrayList<>();

        CourseCreate courseCreate = new CourseCreate(null, institutionId, tags, instructorId, null, null, null);
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().put("/{id}", courseId)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("updateCourse.courseCreate.title must not be blank"));

    }
}
