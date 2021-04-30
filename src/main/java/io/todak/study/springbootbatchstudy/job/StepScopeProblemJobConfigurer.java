package io.todak.study.springbootbatchstudy.job;

import io.todak.study.springbootbatchstudy.entities.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

import static io.todak.study.springbootbatchstudy.job.StepScopeProblemJobConfigurer.JOB_NAME;

@RequiredArgsConstructor
@Slf4j
@Configuration
@ConditionalOnProperty(name = "job.name", havingValue = JOB_NAME)
public class StepScopeProblemJobConfigurer {

    public static final String JOB_NAME = "stepScopeProblemJob";
    private static final String STEP_NAME = "stopScopeProblemStep";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;


    @Bean
    public Job stepScopeProblemJob() {
        log.info(">>>>>>>>>>>>>>>>>>> {} START!!!!", JOB_NAME);
        return jobBuilderFactory.get(JOB_NAME)
                .start(step())
                .build();
    }

    private Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Person, Person>chunk(1)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .build();

    }

    private ItemProcessor<Person, Person> processor() {
        return new PersonItemProcessor();
    }

    @Bean
    @StepScope
    public ItemReader<Person> reader(@Value("#{jobParameters[firstName]}") String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("firstName", firstName);

        JpaPagingItemReader<Person> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("select p From Person p where p.firstName=:firstName");
        reader.setParameterValues(paramMap);
        reader.setPageSize(10);
        return reader;
    }

    private ItemWriter<Person> writer() {
        JpaItemWriter<Person> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
