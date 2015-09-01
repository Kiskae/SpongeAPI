/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
