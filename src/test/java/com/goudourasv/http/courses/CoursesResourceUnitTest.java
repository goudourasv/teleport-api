package com.goudourasv.http.courses;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.courses.dto.CourseCreate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.ServerErrorException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class CoursesResourceUnitTest {
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


    @Test
    public void shouldReturn200WithExpectedCourseId() {

        UUID courseId = UUID.fromString("85fb9103-bacb-4933-b67d-d0bcf438ca29");
        Course expectedCourse = new Course(courseId, "Annoula");

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

    @Test
    public void shouldReturn201WhenValidCourseCreate() {
        CourseCreate courseCreate = createCourseCreate();
        Course expectedCourse = createCourse();

        String path = "http://localhost:8081/courses/";
        String expectedLocation = path + expectedCourse.getId();

        Mockito.when(coursesService.createCourse(any())).thenReturn(expectedCourse);

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

        Mockito.verify(coursesService).createCourse(any());
        assertThat(response.getHeader("Location")).isEqualTo(expectedLocation);
        assertThat(courseResult).isEqualTo(expectedCourse);
    }

    @Test
    public void shouldReturn400WhenInvalidCourseCreate() {
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        CourseCreate courseCreate = new CourseCreate(null, institutionId, null, instructorId, null, null, null);

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

        Mockito.when(coursesService.createCourse(any())).thenThrow(exception);

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(504)
                .assertThat()
                .body("message", equalTo("Something went wrong"));
        Mockito.verify(coursesService).createCourse(any());

    }

    @Test
    public void shouldReturn500WhenGenericError() {
        CourseCreate courseCreate = createCourseCreate();
        Exception exception = new NullPointerException();

        Mockito.when(coursesService.createCourse(any())).thenThrow(exception);

        with().body(courseCreate)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(500)
                .assertThat()
                .body("message", equalTo("Something went wrong"));
        Mockito.verify(coursesService).createCourse(any());

    }


}