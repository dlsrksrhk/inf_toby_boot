package com.example.demo.helloboot.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationTest {

    @DisplayName("순수 자바 코드를 이용한 빈 생성 테스트")
    @Test
    public void pureConfigTest() {
        //생성자 각각 호출시 서로 다른 객체이다.
        assertThat(new Common()).isNotSameAs(new Common());

        Common common = new Common();
        //객체는 하나만 생성되고 동일한 객체이다.
        assertThat(common).isSameAs(common);

        //MyConfig 내부에서도 Common 생성자가 두번 호출 되었기에 다른 객체이다.
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();
        assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @DisplayName("구성클래스 프록시를 이용한 빈 생성 테스트")
    @Test
    public void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        //구성클래스에서 Common은 두번의 생성자로 호출되지만 실제로는 한번만 생성된다.
        assertThat(bean1.common).isSameAs(bean2.common);
    }


    @DisplayName("순수 자바 코드로 프록시 구성클래스 만들기 테스트")
    @Test
    public void pureConfigProxyTest() {
        MyConfig myConfig = new MyConfigProxy();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        //MyConfigProxy는 Common 생성자가 두번 호출 되었더라도 싱글톤을 지원하기에 같은 객체이다.
        assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        //원래는 스프링이 해주는 작업이다.
        @Override
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }

            return common;
        }
    }


    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }
}