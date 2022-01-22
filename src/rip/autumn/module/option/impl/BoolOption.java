/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.option.impl;

import java.util.function.Supplier;
import rip.autumn.module.option.Option;

public final class BoolOption
extends Option<Boolean> {
    public BoolOption(String label, Boolean defaultValue, Supplier<Boolean> supplier) {
        super(label, defaultValue, supplier);
    }

    public BoolOption(String label, Boolean defaultValue) {
        super(label, defaultValue, () -> true);
    }

    @Override
    public Boolean getValue() {
        return (Boolean)super.getValue() != false && this.check();
    }
}

