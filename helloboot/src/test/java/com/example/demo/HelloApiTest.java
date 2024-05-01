package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

class HelloApiTest {

    @Test
    public void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        //200 ok res
        //hearder content-type이 text/plain으로 시작
        //body Hello Spring
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                .startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    public void failHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name=", String.class);

        //internal 500 error res
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}