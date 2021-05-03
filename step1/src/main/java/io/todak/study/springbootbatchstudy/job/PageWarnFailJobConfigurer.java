package io.todak.study.springbootbatchstudy.job;

import io.todak.study.springbootbatchstudy.entities.PageWarn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class PageWarnFailJobConfigurer {

    public static final String JOB_NAME = "pageWarnFailJob";

    private final EntityManagerFactory entityManagerFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    private final int chunkSize = 10;

    @Bean
    public Job pageWarnFailJob() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> pageWarnFailJob run~!!!!!!!!!!");
        return jobBuilderFactory.get(JOB_NAME)
                .start(pageWarnFailStep())
                .build();
    }

    @Bean
    @JobScope
    public Step pageWarnFailStep() {
        return stepBuilderFactory.get("pageWarnFailStep")
                .<PageWarn, PageWarn>chunk(chunkSize)
                .reader(pageWarnFailReader())
                .processor(pageWarnFailProcessor())
                .writer(pageWarnFailWriter())
                .build();
    }


    @Bean
    @StepScope
    public JpaPagingItemReader<PageWarn> pageWarnFailReader() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> pageWarnFailReader run~!!!!!!!!!!");
        return new JpaPagingItemReaderBuilder<PageWarn>()
                .name("pageWarnFailReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT p FROM PageWarn p")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<PageWarn, PageWarn> pageWarnFailProcessor() {
        return item -> {
            item.success();
            System.out.println("item : " + item);
            return item;
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<PageWarn> pageWarnFailWriter() {
        JpaItemWriter<PageWarn> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }


}
