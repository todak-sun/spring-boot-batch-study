package io.todak.study.springbootbatchstudy.supporter;

import io.todak.study.springbootbatchstudy.entities.PageWarn;
import io.todak.study.springbootbatchstudy.entities.Pay;
import io.todak.study.springbootbatchstudy.repository.PageWarnRepository;
import io.todak.study.springbootbatchstudy.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class SqlServerDummyDataInserter implements DummyDataInserter {

    private final PayRepository payRepository;

    private final PageWarnRepository pageWarnRepository;

    @Override
    public void insert(int size) {
        long amount = 0L;
        String txName = "txName";
        for (int i = 0; i < size; i++) {
            Pay pay = new Pay(
                    amount + (i * 100L),
                    txName + i,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
            );
            payRepository.save(pay);
            pageWarnRepository.save(new PageWarn(amount + (i * 100L), false));
        }


    }


}
