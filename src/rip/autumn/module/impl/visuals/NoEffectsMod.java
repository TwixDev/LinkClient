/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import rip.autumn.annotations.Label;
import rip.autumn.events.game.TickEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;

@Label(value="No Effects")
@Category(value=ModuleCategory.VISUALS)
@Aliases(value={"noeffects", "speedygonzales", "noblind"})
public final class NoEffectsMod
extends Module {
    @Listener(value=TickEvent.class)
    public final void onTick() {
        EntityPlayerSP player = NoEffectsMod.mc.thePlayer;
        Potion blind = Potion.blindness;
        Potion confusion = Potion.confusion;
        if (player.isPotionActive(blind)) {
            player.removePotionEffect(blind.id);
        }
        if (player.isPotionActive(confusion)) {
            player.removePotionEffect(confusion.id);
        }
    }
}

