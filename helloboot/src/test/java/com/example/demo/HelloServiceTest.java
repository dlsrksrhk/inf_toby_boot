package com.example.demo;

import com.example.demo.helloboot.HelloDecorator;
import com.example.demo.helloboot.SimpleHelloService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Test
@interface UnitTest {
}

class HelloServiceTest {

    @Test
    public void helloService() {
        SimpleHelloService helloService = new SimpleHelloService();
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