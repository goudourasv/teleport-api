package com.goudourasv.http.utils;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class PostgresResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    static PostgreSQLContainer<?> db =
            new PostgreSQLContainer<>("postgres:12.5")
                    .withDatabaseName("teleport-mock")
                    .withUsername("postgres")
                    .withPassword("secret")
                    .withExposedPorts(5432);

    @Override
    public Map<String, String> start() {
        db.start();
        return Collections.singletonMap(
                "quarkus.datasource.jdbc.url", db.getJdbcUrl()
        );
    }

    @Override
    public void stop() {
        db.stop();
    }
}