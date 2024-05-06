package com.example.demo.helloboot;

import com.example.demo.helloboot.HelloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("홀리나이트")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("홀리나이트")).isEqualTo(0);

        helloRepository.increaseCount("홀리나이트");
        assertThat(helloRepository.countOf("홀리나이트")).isEqualTo(1);

        helloRepository.increaseCount("홀리나이트");
        assertThat(helloRepository.countOf("홀리나이트")).isEqualTo(2);
    }

}