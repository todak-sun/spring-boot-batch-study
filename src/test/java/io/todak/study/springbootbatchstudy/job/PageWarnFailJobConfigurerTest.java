package io.todak.study.springbootbatchstudy.job;

import io.todak.study.springbootbatchstudy.entities.PageWarn;
import io.todak.study.springbootbatchstudy.repository.PageWarnRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"job.name=" + PageWarnFailJobConfigurer.JOB_NAME})
public class PageWarnFailJobConfigurerTest {

    private JobLauncherTestUtils jobLauncherTestUtils;

    @BeforeEach
    public void setUp() {
        this.jobLauncherTestUtils = new JobLauncherTestUtils();
    }

    @Autowired
    private PageWarnRepository pageWarnRepository;


    @Test
    public void isNotNullPageWarnRepository() {
        assertNotNull(pageWarnRepository);
    }

    @Test
    public void update_after_same_condition() throws Exception {
        //given
        for (long i = 0; i < 50L; i++) {
            pageWarnRepository.save(new PageWarn(i, false));
        }

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //then
        Assertions.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
//        Assertions.assertEquals(50, pageWarnRepository.findAllSuccess().size());
    }

}