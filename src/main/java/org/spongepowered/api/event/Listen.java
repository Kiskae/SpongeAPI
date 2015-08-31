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
package org.spongepowered.api.event;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import org.spongepowered.api.service.event.EventManager;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to annotate a method as an {@link EventListener}.
 *
 * <p>The method being targeted must be public and must be in a class that is
 * also public.</p>
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Listen {

    /**
     * The order this handler should be called in relation to other handlers in
     * the {@link EventManager}.
     *
     * @return The order the handler should be called in
     */
    Order order() default Order.DEFAULT;

    /**
     * Whether this handler should ignore cancelled events. If enabled the
     * handler will not be called for any cancelled events.
     *
     * @return If the handler should ignore cancelled events
     */
    boolean ignoreCancelled() default true;

    /**
     * Whether this handler should be called before any other server mods, such
     * as Forge mods. All Sponge event handlers are called after mods, unless
     * they specify the {@link #beforeModifications()} flag to be true.
     *
     * @return If the handler should be fired before other server mods
     */
    boolean beforeModifications() default false;

}