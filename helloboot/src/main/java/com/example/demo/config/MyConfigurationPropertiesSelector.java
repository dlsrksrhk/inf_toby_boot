package com.example.demo.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class MyConfigurationPropertiesSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
        List<Object> values = attr.get("value");
        Class propertyClass = (Class) attr.getFirst("value");

        return new String[]{propertyClass.getName()};
    }
}
