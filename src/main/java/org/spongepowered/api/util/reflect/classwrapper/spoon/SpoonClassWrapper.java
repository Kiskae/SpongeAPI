package org.spongepowered.api.util.reflect.classwrapper.spoon;

import com.beust.jcommander.internal.Lists;
import org.spongepowered.api.util.reflect.classwrapper.ClassWrapper;
import org.spongepowered.api.util.reflect.classwrapper.MethodWrapper;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;

import java.util.List;

public class SpoonClassWrapper implements ClassWrapper<CtType<?>, CtMethod<?>> {

    private CtType<?> clazz;

    public SpoonClassWrapper(CtType<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        return this.clazz.getQualifiedName();
    }

    @Override
    public List<MethodWrapper<CtType<?>, CtMethod<?>>> getMethods() {
        return null;
    }

    @Override
    public boolean isAssignableFrom(ClassWrapper<CtType<?>, CtMethod<?>> other) {
        return this.clazz.isAssignableFrom(other.getActualClass().getReference());
    }

    @Override
    public List<ClassWrapper<CtType<?>, CtMethod<?>>> getInterfaces() {
        List<ClassWrapper<CtType<?>, CtMethod<?>>> interfaces = Lists.newArrayList();

        for (CtTypeReference<?> klass: this.clazz.getSuperInterfaces()) {
            interfaces.add(new SpoonClassWrapper(klass.getDeclaration()));
        }

        return interfaces;
    }

    @Override
    public ClassWrapper<CtType<?>, CtMethod<?>> getSuperclass() {
        return new SpoonClassWrapper(this.clazz.getSuperclass().getDeclaration());
    }

    @Override
    public CtType<?> getActualClass() {
        return this.clazz;
    }
}
