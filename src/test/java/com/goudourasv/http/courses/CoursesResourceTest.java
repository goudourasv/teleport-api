package com.goudourasv.http.courses;

import com.goudourasv.http.utils.PostgresResourceLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

@QuarkusTest
@TestHTTPEndpoint(CoursesResource.class)
public class CoursesResourceTest {

    @Test
    public void shouldTest(){
        System.out.println("oti na nai");

    }
}
