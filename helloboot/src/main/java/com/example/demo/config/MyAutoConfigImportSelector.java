package com.example.demo.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        ImportCandidates configClasseCandidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        //return StreamSupport.stream(configClasses.spliterator(),false).toArray(value -> new String[value]); 이렇게도 쓸 수 있다.

        List<String> candidates = new ArrayList<>();
        configClasseCandidates.forEach(candidates::add);

        return candidates.toArray(new String[0]);
    }
}