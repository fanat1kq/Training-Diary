package org.example.containers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgresTestContainer {
    public static final String IMAGE_VERSION = "postgres:14";
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(IMAGE_VERSION)
            .withDatabaseName("postgres")
            .withUsername("nikita")
            .withPassword("postgres");
    @BeforeAll
    static void start() {
        container.start();
    }

    @AfterAll
    static void stop() {
        container.stop();
    }
}