package io.micronaut.test.config;

import io.micronaut.inject.ast.ClassElement;
import io.micronaut.inject.ast.MethodElement;
import io.micronaut.inject.visitor.TypeElementVisitor;
import io.micronaut.inject.visitor.VisitorContext;

import java.util.*;

public class AbcTypeElementVisitorTest implements TypeElementVisitor<Abc, Object> {

    public AbcTypeElementVisitorTest() {}

    @Override
    public Set<String> getSupportedAnnotationNames() {
        return Set.of(Abc.class.getName());
    }

    @Override
    public void visitClass(ClassElement element, VisitorContext context) {
        System.out.println(">>> visitClass: " + element.getName());
        if (element.hasDeclaredAnnotation(Abc.class)) {
            System.out.println(">>> Matched @Abc: " + element.getName());
        }
    }

    @Override
    public void visitMethod(MethodElement element, VisitorContext context) {
        System.out.println(">>> visitMethod: " + element.getName());
    }

    @Override
    public void start(VisitorContext visitorContext) {
        System.out.println("=========================start======================");
    }

    @Override
    public void finish(VisitorContext visitorContext) {
        System.out.println("=========================finish======================");
    }

    @Override
    public VisitorKind getVisitorKind() {
        return VisitorKind.ISOLATING;
    }
}
