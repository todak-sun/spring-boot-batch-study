package io.todak.study.springbatch.part3;

import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Profile("data")
@RequiredArgsConstructor
@Component
public class DataInserter {

    private final PersonInserter personInserter;

    @PostConstruct
    public void insert() {
        personInserter.insert();
    }


    @RequiredArgsConstructor
    @Component
    public static class PersonInserter {

        private final EntityManager em;

        @Transactional
        public void insert() {
            for (int i = 0; i < 50; i++) {
                Person person = new Person("이름" + i, "나이" + i, "주소" + i);
                em.persist(person);
            }
            em.flush();
            em.clear();
        }
    }


}
