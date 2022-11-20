package net.chebdotnet.weather;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

import java.time.Duration;

public class MongoDbContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:latest")
            .withStartupTimeout(Duration.ofSeconds(600));

    static {
        MONGO_DB_CONTAINER.start();
    }

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "spring.data.mongodb.uri = " + MONGO_DB_CONTAINER.getReplicaSetUrl()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
