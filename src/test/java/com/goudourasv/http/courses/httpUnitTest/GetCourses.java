package com.goudourasv.http.courses.httpUnitTest;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.courses.CoursesResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class GetCourses {

    @InjectMock
    private CoursesService coursesService;

    @Test
    public void shouldReturn200WithoutAnyQueryParam() {
        List<Course> expectedCourses = createCourses();
        Set<String> tags = new HashSet<>();

        Mockito.when(coursesService.getFilteredCourses(null, tags, null)).thenReturn(expectedCourses);

        Response response = given().contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Course> coursesResponse = response.getBody().jsonPath().getList(".", Course.class);

        Mockito.verify(coursesService).getFilteredCourses(null, tags, null);
        assertThat(coursesResponse).hasSameSizeAs(expectedCourses).hasSameElementsAs(expectedCourses);
    }

    @Test
    public void shouldReturn200WithExpectedInstitutionId() {
        List<Course> expectedCourses = createCoursesWithSameInstitutionId();

        Set<String> tags = new HashSet<>();
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");

        Mockito.when(coursesService.getFilteredCourses(institutionId, tags, null)).thenReturn(expectedCourses);

        Response response = given().contentType(ContentType.JSON)
                .param("institution", "e21be850-20f7-4943-bd37-c226cbdc8c83")
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Course> coursesResponse = response.getBody().jsonPath().getList(".", Course.class);

        Mockito.verify(coursesService).getFilteredCourses(institutionId, tags, null);
        assertThat(coursesResponse).hasSameSizeAs(expectedCourses).hasSameElementsAs(expectedCourses);
    }

    @Test
    public void shouldReturn200WithExpectedInstructorId() {
        List<Course> expectedCourses = createCoursesWithSameInstructorId();

        Set<String> tags = new HashSet<>();
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");

        Mockito.when(coursesService.getFilteredCourses(null, tags, instructorId)).thenReturn(expectedCourses);

        Response response = given().contentType(ContentType.JSON)
                .param("instructor", "7ce6be58-4eb1-4ff1-b470-a34c2fc54687")
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Course> coursesResponse = response.getBody().jsonPath().getList(".", Course.class);

        Mockito.verify(coursesService).getFilteredCourses(null, tags, instructorId);
        assertThat(coursesResponse).hasSameSizeAs(expectedCourses).hasSameElementsAs(expectedCourses);
    }

    @Test
    public void shouldReturn200WithExpectedTags() {
        List<Course> expectedCourses = createCoursesWithSameTags();

        Set<String> tags = new HashSet<>();
        Tag engineering = new Tag("Engineering");
        String engineeringTag = engineering.getName();
        tags.add(engineeringTag);

        //Check Set<String> or Set<Tag>?
        Mockito.when(coursesService.getFilteredCourses(null, tags, null)).thenReturn(expectedCourses);

        Response response = given().contentType(ContentType.JSON)
                .param("tags", "Engineering")
                .when().get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Course> coursesResponse = response.getBody().jsonPath().getList(".", Course.class);

        Mockito.verify(coursesService).getFilteredCourses(null, tags, null);
        assertThat(coursesResponse).hasSameSizeAs(expectedCourses).hasSameElementsAs(expectedCourses);
    }

    //TODO: NullPointerExceptionsMapper
    @Test
    public void shouldThrowExceptionWithInvalidFilterParam() {
    }

}
