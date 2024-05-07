package com.example.demo.bootac;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAcApplication {

    @Bean
    public ApplicationRunner applicationRunner(ConditionEvaluationReport report) {
        return args -> {
            long count = report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch())
                    .filter(co -> !co.getKey().contains("Jmx")) //JMX쪽은 불필요하니 제거
                    .map(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c -> {
                            System.out.println("\t" + c.getOutcome());
                        });
                        System.out.println();
                        return co;
                    }).count();
            System.out.println(count);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAcApplication.class, args);
    }
}