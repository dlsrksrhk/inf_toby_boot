package com.example.demo.helloboot;

import com.example.demo.helloboot.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {
    private static final HelloRepository helloRepository = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return new Hello(name, 1);
        }

        @Override
        public void increaseCount(String name) {
        }
    };

    @Test
    public void helloService() {
        SimpleHelloService helloService = new SimpleHelloService(helloRepository);
        String ret = helloService.sayHello("Test");
        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    public void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }

            @Override
            public int countOf(String name) {
                return 0;
            }
        });

        String ret = decorator.sayHello("Test");
        assertThat(ret).isEqualTo("*Test*");
    }
}