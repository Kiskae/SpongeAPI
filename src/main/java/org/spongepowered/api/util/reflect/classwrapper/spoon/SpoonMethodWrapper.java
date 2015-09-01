package org.spongepowered.api.util.reflect.classwrapper.spoon;

import com.beust.jcommander.internal.Lists;
import org.spongepowered.api.util.reflect.classwrapper.ClassWrapper;
import org.spongepowered.api.util.reflect.classwrapper.MethodWrapper;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;

import java.util.List;

public class SpoonMethodWrapper implements MethodWrapper<CtType<?>, CtMethod<?>> {

    private CtMethod<?> method;

    public SpoonMethodWrapper(CtMethod<?> method) {
        this.method = method;
    }

    @Override
    public String getName() {
        return this.method.getSimpleName();
    }

    @Override
    public boolean isPublic() {
        return this.method.getModifiers().contains(ModifierKind.PUBLIC);
    }

    @Override
    public ClassWrapper<CtType<?>, CtMethod<?>> getReturnType() {
        return new SpoonClassWrapper(this.method.getType().getDeclaration());
    }

    @Override
    public List<ClassWrapper<CtType<?>, CtMethod<?>>> getParameterTypes() {
        List<ClassWrapper<CtType<?>, CtMethod<?>>> parameters = Lists.newArrayList();

        for (CtParameter<?> klass: this.method.getParameters()) {
            parameters.add(new SpoonClassWrapper(klass.getType().getDeclaration()));
        }

        return parameters;
    }

    @Override
    public CtMethod<?> getActualMethod() {
        return this.method;
    }
}
