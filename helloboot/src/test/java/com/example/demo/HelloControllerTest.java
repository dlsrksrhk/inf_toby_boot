package com.example.demo;

import com.example.demo.helloboot.HelloController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {

    @Test
    public void helloControllerTest() {
        HelloController helloController = new HelloController(name -> {
            return name;
        });

        String ret = helloController.hello("Test");
        assertThat(ret).isEqualTo("Test");
    }

    @DisplayName("test")
    @Test
    public void failHelloControllerTest() {
        HelloController helloController = new HelloController(name -> {
            return name;
        });

        assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}