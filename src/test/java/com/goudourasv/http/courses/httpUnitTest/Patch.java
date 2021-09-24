package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.http.courses.CoursesResource;
import com.goudourasv.http.courses.dto.CourseUpdate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.goudourasv.utils.TestData.createCourse;
import static com.goudourasv.utils.TestData.createCourseUpdate;
import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class Patch {

    @InjectMock
    private CoursesService coursesService;

    @Test
    public void shouldReturn200WhenValidCourseUpdate() {
        CourseUpdate courseUpdate = createCourseUpdate();
        Course expectedCourse = createCourse();
        UUID courseId = UUID.fromString("30d8f597-537a-4486-bfb5-845a2bae4c45");

        Mockito.when(coursesService.partiallyUpdateCourse(courseId, courseUpdate)).thenReturn(expectedCourse);

        Response response = with().body(courseUpdate)
                .contentType(ContentType.JSON)
                .when().patch("/{id}", courseId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Course courseResult = response.as(Course.class);

        Mockito.verify(coursesService).partiallyUpdateCourse(courseId, courseUpdate);
        assertThat(courseResult).isEqualTo(expectedCourse);
    }
}
