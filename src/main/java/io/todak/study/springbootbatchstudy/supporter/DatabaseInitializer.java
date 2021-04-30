package io.todak.study.springbootbatchstudy.supporter;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    private final DatabaseInitService databaseInitService;

    public DatabaseInitializer(DatabaseInitService databaseInitService) {
        this.databaseInitService = databaseInitService;
    }

    @PostConstruct
    public void init() {
        databaseInitService.init();
    }

}
