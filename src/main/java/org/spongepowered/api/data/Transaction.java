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
package org.spongepowered.api.data;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.spongepowered.api.data.DataQuery.of;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nullable;

public final class Transaction<T extends DataSerializable> implements DataSerializable {

    public static final DataQuery TYPE_CLASS = of("TypeClass");
    public static final DataQuery ORIGINAL = of("Original");
    public static final DataQuery DEFAULT_REPLACEMENT = of("DefaultReplacement");
    public static final DataQuery CUSTOM_REPLACEMENT = of("CustomReplacement");
    public static final DataQuery VALID = of("IsValid");

    private final T original;
    private final T defaultReplacement;
    private boolean valid = true;
    @Nullable private T custom;

    public Transaction(T original, T defaultReplacement) {
        this.original = checkNotNull(original);
        this.defaultReplacement = checkNotNull(defaultReplacement);
    }

    /**
     * Gets the original snapshot.
     *
     * @return The original snapshot
     */
    public T getOriginal() {
        return this.original;
    }

    /**
     * Gets the default replacement snapshot.
     *
     * @return The default replacement
     */
    public T getDefaultReplacement() {
        return this.defaultReplacement;
    }

    /**
     * Gets the custom snapshot if one was set.
     *
     * @return The custom snapshot, if available
     */
    public Optional<T> getCustom() {
        return Optional.ofNullable(this.custom);
    }

    /**
     * Sets the custom snapshot. If setting <code>null</code>, this will
     * reset to use the {@link #getDefaultReplacement()} snapshot.
     *
     * @param custom The custom snapshot
     */
    public void setCustom(@Nullable T custom) {
        this.custom = custom;
    }

    /**
     * Gets the proposed final snapshot, if the {@link #getCustom()} returns
     * {@link Optional#isPresent()}, the custom is returned, otherwise,
     * {@link #getDefaultReplacement()} is returned.
     *
     * @return The proposed final snapshot
     */
    public T getFinal() {
        return this.custom == null ? this.defaultReplacement : this.custom;
    }

    /**
     * Gets whether this transaction is marked as valid.
     *
     * @return The valid state of this transaction
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Sets whether this transaction is valid or not.
     *
     * @param valid The valid state of this transaction
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.original, this.defaultReplacement, this.valid, this.custom);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        return Objects.equals(this.original, other.original)
               && Objects.equals(this.defaultReplacement, other.defaultReplacement)
               && Objects.equals(this.valid, other.valid)
               && Objects.equals(this.custom, other.custom);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
            .add("original", this.original)
            .add("defaultReplacement", this.defaultReplacement)
            .add("valid", this.valid)
            .add("custom", this.custom)
            .toString();
    }

    @Override
    public DataContainer toContainer() {
        final DataContainer container = new MemoryDataContainer()
            .set(TYPE_CLASS, this.original.getClass().getName())
            .set(ORIGINAL, this.original)
            .set(DEFAULT_REPLACEMENT, this.defaultReplacement)
            .set(VALID, this.valid);
        if (this.custom != null) {
            container.set(CUSTOM_REPLACEMENT, this.custom);
        }
        return container;
    }
}
