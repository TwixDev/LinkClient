/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import rip.autumn.annotations.Label;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.DoubleOption;
import rip.autumn.module.option.impl.EnumOption;

@Label(value="Animations")
@Category(value=ModuleCategory.VISUALS)
public final class AnimationsMod
extends Module {
    public final EnumOption<Mode> mode = new EnumOption<Mode>("Mode", Mode.EXHIBITION);
    public static final DoubleOption swingSpeed = new DoubleOption("Swing Speed", 6.0, 2.0, 12.0, 0.5);
    public final DoubleOption x = new DoubleOption("X", 0.0, -1.0, 1.0, 0.05);
    public final DoubleOption y = new DoubleOption("Y", 0.15, -1.0, 1.0, 0.05);
    public final DoubleOption z = new DoubleOption("Z", 0.0, -1.0, 1.0, 0.05);

    public AnimationsMod() {
        this.setMode(this.mode);
        this.addOptions(this.mode, swingSpeed, this.x, this.y, this.z);
        this.setEnabled(true);
        this.setHidden(true);
    }

    public static enum Mode {
        OLD,
        EXHIBITION,
        SLIDE;

    }
}

