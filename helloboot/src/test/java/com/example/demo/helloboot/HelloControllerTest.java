package com.example.demo.helloboot;

import com.example.demo.helloboot.HelloController;
import com.example.demo.helloboot.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {
    private static final HelloService helloService = new HelloService() {
        @Override
        public String sayHello(String name) {
            return name;
        }

        @Override
        public int countOf(String name) {
            return 0;
        }
    };

    @Test
    public void helloControllerTest() {
        HelloController helloController = new HelloController(helloService);

        String ret = helloController.hello("Test");
        assertThat(ret).isEqualTo("Test");
    }

    @DisplayName("test")
    @Test
    public void failHelloControllerTest() {
        HelloController helloController = new HelloController(helloService);

        assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}