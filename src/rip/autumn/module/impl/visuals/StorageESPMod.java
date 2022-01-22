/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import java.awt.Color;
import rip.autumn.annotations.Label;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.EnumOption;

@Label(value="Storage ESP")
@Category(value=ModuleCategory.VISUALS)
@Aliases(value={"storageesp", "chestesp"})
public final class StorageESPMod
extends Module {
    private final int chestColor = new Color(250, 138, 19).getRGB();
    public final EnumOption<Mode> mode = new EnumOption<Mode>("Mode", Mode.BOX);

    public static enum Mode {
        BOX;

    }
}

