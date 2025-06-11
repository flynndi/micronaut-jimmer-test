package io.micronaut.test.config;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.inject.annotation.TypedAnnotationMapper;
import io.micronaut.inject.visitor.VisitorContext;

import java.util.List;

public class AbcTestMapper implements TypedAnnotationMapper<Abc> {

    @Override
    public Class<Abc> annotationType() {
        System.out.println("Abc.annotationType >>>>>>>>>>>>>>>>");
        return Abc.class;
    }

    @Override
    public List<AnnotationValue<?>> map(
            AnnotationValue<Abc> annotation, VisitorContext visitorContext) {
        return List.of(AnnotationValue.builder(Abc.class).build());
    }
}
