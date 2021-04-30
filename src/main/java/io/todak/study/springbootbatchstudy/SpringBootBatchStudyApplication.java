package io.todak.study.springbootbatchstudy;

import io.todak.study.springbootbatchstudy.entities.Pay;
import io.todak.study.springbootbatchstudy.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBootBatchStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBatchStudyApplication.class, args);
    }


}
