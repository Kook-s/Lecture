package io.dev.lecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Lecture {

    public static void main(String[] args) {
        SpringApplication.run(Lecture.class, args);
    }

}
