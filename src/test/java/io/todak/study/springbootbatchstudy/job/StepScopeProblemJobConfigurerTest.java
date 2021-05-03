package io.todak.study.springbootbatchstudy.job;

import io.todak.study.springbootbatchstudy.config.TestBatchConfigurer;
import io.todak.study.springbootbatchstudy.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = {TestBatchConfigurer.class, StepScopeProblemJobConfigurer.class})
@SpringBatchTest
@RunWith(SpringRunner.class)
//@SpringBootTest(
//        classes = {StepScopeProblemJobConfigurer.class, TestBatchConfigurer.class}
//        properties = {"job.name=" + StepScopeProblemJobConfigurer.JOB_NAME}
//)

//@TestPropertySource(properties = {"job.name=" + StepScopeProblemJobConfigurer.JOB_NAME})
public class StepScopeProblemJobConfigurerTest {

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