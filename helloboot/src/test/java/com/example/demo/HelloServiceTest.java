package com.example.demo;

import com.example.demo.helloboot.Hello;
import com.example.demo.helloboot.HelloDecorator;
import com.example.demo.helloboot.HelloRepository;
import com.example.demo.helloboot.SimpleHelloService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {
    private static final HelloRepository helloRepository = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
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
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Test");
        assertThat(ret).isEqualTo("*Test*");
    }
}