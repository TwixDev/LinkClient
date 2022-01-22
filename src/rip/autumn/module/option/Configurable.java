/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rip.autumn.module.option.Option;
import rip.autumn.module.option.impl.EnumOption;

public class Configurable {
    private final List<Option<?>> options = new ArrayList();
    private EnumOption<?> mode = null;

    public final void setMode(EnumOption<?> mode) {
        this.mode = mode;
    }

    public final void addOptions(Option<?> ... options) {
        this.options.addAll(Arrays.asList(options));
    }

    public EnumOption<?> getMode() {
        return this.mode;
    }

    public final List<Option<?>> getOptions() {
        return this.options;
    }

    public final Option<?> getOptionByLabel(String label) {
        for (Option<?> option : this.options) {
            if (!option.getLabel().equals(label)) continue;
            return option;
        }
        throw new NullPointerException();
    }
}

