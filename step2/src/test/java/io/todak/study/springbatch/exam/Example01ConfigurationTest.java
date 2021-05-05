package io.todak.study.springbatch.exam;

import io.todak.study.springbatch.config.TestConfiguration;
import io.todak.study.springbatch.part3.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

@ActiveProfiles("local")
@SpringBootTest
@ContextConfiguration(classes = {
        Example01Configuration.class,
        TestConfiguration.class,
        PersonRepository.class,
        Person.class
})
public class Example01ConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void tearDown() {
//        personRepository.deleteAll();
    }

    @Test
    public void personRepository_notnull() {
//        Assertions.assertThat(personRepository).isNotNull();
    }

    @Test
    public void test_allow_duplicate() throws Exception {
        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "false")
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();

        //then
        Assertions.assertThat(stepExecutions.stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum())
//                .isEqualTo(personRepository.count())
                .isNotEqualTo(100);
    }

}