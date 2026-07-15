package dev.vivek6219.dedupeengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@SpringBootApplication
public class DedupeEngineApplication {

    private String command;

    public static void main(String[] args) {
        SpringApplication.run(DedupeEngineApplication.class, args);
    }
}
