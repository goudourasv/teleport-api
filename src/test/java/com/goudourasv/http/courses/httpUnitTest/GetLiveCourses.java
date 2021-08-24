package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.courses.LiveCourse;
import com.goudourasv.http.courses.CoursesResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class GetLiveCourses {

    @InjectMock
    private CoursesService coursesService;

    @Test
    public void shouldReturn200() {
        List<LiveCourse> expectedLiveCourses = new ArrayList<>();
        LiveCourse liveCourse1 = new LiveCourse("course1", null, null, null, null);
        LiveCourse liveCourse2 = new LiveCourse("course2", null, null, null, null);
        expectedLiveCourses.add(liveCourse1);
        expectedLiveCourses.add(liveCourse2);


        Mockito.when(coursesService.getLiveCourses()).thenReturn(expectedLiveCourses);

        Response response = given().contentType(ContentType.JSON)
                .when().get("/live")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<LiveCourse> liveCoursesResponse = response.getBody().jsonPath().getList(".", LiveCourse.class);

        Mockito.verify(coursesService).getLiveCourses();
        assertThat(liveCoursesResponse).hasSameSizeAs(expectedLiveCourses).hasSameElementsAs(expectedLiveCourses);
    }
}
