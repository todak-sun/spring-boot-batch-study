package io.todak.study.springbootbatchstudy.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PageWarnRepositoryTest {

    @Autowired
    PageWarnRepository pageWarnRepository;

    @Test
    public void isNotNullPageWarnRepository() {
        assertNotNull(pageWarnRepository);
    }

}