/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.world;

import rip.autumn.annotations.Label;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.DoubleOption;

@Label(value="Time Changer")
@Category(value=ModuleCategory.WORLD)
@Aliases(value={"timechanger", "worldtime"})
public final class TimeChangerMod
extends Module {
    public final DoubleOption time = new DoubleOption("Time", 16000.0, 1.0, 24000.0, 100.0);

    public TimeChangerMod() {
        this.addOptions(this.time);
    }
}

