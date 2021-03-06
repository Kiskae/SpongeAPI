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
package org.spongepowered.api.attribute;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

/**
 * Represents a function used by an {@link AttributeModifier} to modify the
 * value of an {@link Attribute}.
 */
@CatalogedBy(Operations.class)
public interface Operation extends CatalogType, Comparable<Operation> {

    /**
     * Gets the amount the {@link Attribute} should be incremented when this
     * modifier is applied to it.
     *
     * @param base The base value of the Attribute
     * @param modifier The modifier to modify the Attribute with
     * @param currentValue The current value of the Attribute
     * @return The amount the Attribute should be incremented when this modifier
     *         is applied to it
     */
    double getIncrementation(double base, double modifier, double currentValue);

    /**
     * Gets if, when applied, this operation should change the value of the
     * attribute immediately, or if it's incrementations should be summed up,
     * then added.
     *
     * @return {@code true} if, when applied, this operation should change the
     *         value of the attribute immediately, or {@code false} if it's
     *         incrementations should be summed up, then added
     */
    boolean changeValueImmediately();

}
