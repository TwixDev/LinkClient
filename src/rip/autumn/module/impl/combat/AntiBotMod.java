/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.combat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import rip.autumn.annotations.Label;
import rip.autumn.events.player.MotionUpdateEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.EnumOption;

@Label(value="Anti Bot")
@Category(value=ModuleCategory.COMBAT)
@Aliases(value={"antibot"})
public final class AntiBotMod
extends Module {
    private final EnumOption<Mode> mode = new EnumOption<Mode>("Mode", Mode.HYPIXEL);
    public final Set<EntityPlayer> bots = new HashSet<EntityPlayer>();

    public AntiBotMod() {
        this.addOptions(this.mode);
        this.setMode(this.mode);
    }

    @Listener(value=MotionUpdateEvent.class)
    public final void onMotionUpdate(MotionUpdateEvent event) {
        switch ((Mode)((Object)this.mode.getValue())) {
            case HYPIXEL: {
                if (!event.isPre()) break;
                List playerEntities = AntiBotMod.mc.theWorld.playerEntities;
                int playerEntitiesSize = playerEntities.size();
                for (int i = 0; i < playerEntitiesSize; ++i) {
                    EntityPlayer player = (EntityPlayer)playerEntities.get(i);
                    if ((!player.getName().startsWith("\u00a7") || !player.getName().contains("\u00a7c")) && (!this.isEntityBot(player) || player.getDisplayName().getFormattedText().contains("NPC"))) continue;
                    AntiBotMod.mc.theWorld.removeEntity(player);
                }
                break;
            }
            case MINEPLEX: {
                for (Entity e : AntiBotMod.mc.theWorld.getLoadedEntityList()) {
                    if (!(e instanceof EntityPlayer)) continue;
                    EntityPlayer bot = (EntityPlayer)e;
                    if (e.ticksExisted >= 2 || !(bot.getHealth() < 20.0f) || !(bot.getHealth() > 0.0f) || e == AntiBotMod.mc.thePlayer) continue;
                    AntiBotMod.mc.theWorld.removeEntity(e);
                }
                break;
            }
        }
    }

    private boolean isEntityBot(Entity entity) {
        double distance = entity.getDistanceSqToEntity(AntiBotMod.mc.thePlayer);
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        if (mc.getCurrentServerData() == null) {
            return false;
        }
        return AntiBotMod.mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel") && entity.getDisplayName().getFormattedText().startsWith("\u0e22\u0e07") || !this.isOnTab(entity) && AntiBotMod.mc.thePlayer.ticksExisted > 100;
    }

    private boolean isOnTab(Entity entity) {
        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            if (!info.getGameProfile().getName().equals(entity.getName())) continue;
            return true;
        }
        return false;
    }

    private static enum Mode {
        HYPIXEL,
        MINEPLEX;

    }
}

