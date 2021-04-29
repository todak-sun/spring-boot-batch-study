package io.todak.study.springbootbatchstudy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBootBatchStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchStudyApplication.class, args);
    }

}
