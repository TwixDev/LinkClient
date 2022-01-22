/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import rip.autumn.annotations.Label;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.EnumOption;
import rip.autumn.utils.render.Palette;

@Label(value="Chams")
@Category(value=ModuleCategory.VISUALS)
public final class ChamsMod
extends Module {
    public final EnumOption<Mode> mode = new EnumOption<Mode>("Mode", Mode.COLOR);
    public final EnumOption<Palette> player = new EnumOption<Palette>("Player", Palette.PURPLE);
    public final EnumOption<Palette> playerBehindWalls = new EnumOption<Palette>("Player behind walls", Palette.PURPLE);
    public static final int HANDCOL = -1253464587;

    public ChamsMod() {
        this.addOptions(this.mode, this.player, this.playerBehindWalls);
    }

    public static enum Mode {
        COLOR,
        TEXTURED;

    }
}

