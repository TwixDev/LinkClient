/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rip.autumn.annotations.Label;
import rip.autumn.events.game.TickEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;

@Label(value="Full Bright")
@Category(value=ModuleCategory.VISUALS)
@Aliases(value={"fullbright", "brightness"})
public final class FullBrightMod
extends Module {
    @Listener(value=TickEvent.class)
    public final void onTick() {
        FullBrightMod.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 1000000, 2));
    }

    @Override
    public void onDisabled() {
        FullBrightMod.mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
    }
}

