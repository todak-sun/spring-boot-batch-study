package io.todak.study.springbootbatchstudy.etc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        log.info("LocalDateTime.now().toString {}", now);
        log.info("After Format : {}", formattedNow);
        log.info("formatted String to LocalDateTime : {}",
                LocalDateTime.parse(formattedNow, formatter)
        );
    }

}
