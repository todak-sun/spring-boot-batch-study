package io.todak.study.springbootbatchstudy.supporter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("h2")
@Component
@RequiredArgsConstructor
public class H2InitService implements DatabaseInitService {

    private final DummyDataInserter dummyDataInserter;

    @Override
    public void init() {
        dummyDataInserter.insert(100);
    }
}
