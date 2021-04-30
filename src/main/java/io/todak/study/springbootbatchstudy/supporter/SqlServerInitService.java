package io.todak.study.springbootbatchstudy.supporter;

import io.todak.study.springbootbatchstudy.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("sqlserver")
@Slf4j
@Component
@RequiredArgsConstructor
public class SqlServerInitService implements DatabaseInitService {

    private final DummyDataInserter dummyDataInserter;
    private final PayRepository payRepository;

    @Override
    public void init() {
        if (payRepository.count() == 0) {
            dummyDataInserter.insert(100);
            log.info(">>>>>>>>>>>> Dummy Data Insert Start~!!!");
        } else {
            log.warn(">>>>>>>>>>>> Dummy Data already exists.....");
        }
    }
}
