package io.todak.study.springbatch.exam;

import io.todak.study.springbatch.part3.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class Example01Configuration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job example01job() throws Exception {
        return jobBuilderFactory.get("example01job")
                .incrementer(new RunIdIncrementer())
                .start(example01step(null))
                .build();
    }

    @Bean
    @JobScope
    public Step example01step(@Value("#{jobParameters[allow_duplicate]}") Boolean allowDuplicate) throws Exception {
        return stepBuilderFactory.get("example01step")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .processor(new DuplicateValidationProcessor<>(Person::getName, allowDuplicate))
                .writer(itemWriter())
                .build();
    }

    private ItemWriter<Person> itemWriter() throws Exception {
        CompositeItemWriter<Person> itemWriter = new CompositeItemWriterBuilder<Person>()
                .delegates(itemSaveWriter())
                .delegates(itemLogWriter())
                .build();

        itemWriter.afterPropertiesSet();
        return itemWriter;
    }

    private ItemWriter<Person> itemSaveWriter() throws Exception {
        JpaItemWriter<Person> itemWriter = new JpaItemWriterBuilder<Person>()
                .usePersist(true)
                .entityManagerFactory(entityManagerFactory)
                .build();
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }

    private ItemWriter<Person> itemLogWriter() {
        return items -> {
            log.info("saved item size : {}", items.size());
        };
    }

//    @Bean
//    @StepScope
//    public ItemProcessor<Person, Person> itemProcessor(@Value("#{jobParameters[allow_duplicate]}") Boolean allow_duplicate) {
//        return new DuplicateValidationProcessor<>(Person::getName, allow_duplicate);
//    }

    private ItemReader<Person> itemReader() throws Exception {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "address", "age", "name");

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            String address = fieldSet.readString("address");
            String name = fieldSet.readString("name");
            String age = fieldSet.readString("age");
            return new Person(name, age, address);
        });
        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("csv/example01.csv"))
                .lineMapper(lineMapper)
                .build();
        itemReader.afterPropertiesSet(); //필드값이 제대로 설정되었는지 검증
        return itemReader;
    }


}
