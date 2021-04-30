package io.todak.study.springbootbatchstudy.job;

import io.todak.study.springbootbatchstudy.config.TestBatchConfig;
import io.todak.study.springbootbatchstudy.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBatchTest
@SpringBootTest(classes = {StepScopeProblemJobConfigurer.class, TestBatchConfig.class})
@ActiveProfiles("h2")
@TestPropertySource(properties = {"job.name=" + StepScopeProblemJobConfigurer.JOB_NAME})
class StepScopeProblemJobConfigurerTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    PersonRepository personRepository;


    @DisplayName("StepScope 문제")
    @Test
    public void check_step_scope() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("firstName", "Sungsu").toJobParameters();
        assertThrows(NoSuchElementException.class, () -> personRepository.findByFirstName("SUNGSU").get());
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        assertEquals(personRepository.findByFirstName("SUNGSU").get().getFirstName(), "SUNGSU");
    }


}