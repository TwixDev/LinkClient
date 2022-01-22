/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package rip.autumn.module.impl.movement;

import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S45PacketTitle;
import org.lwjgl.input.Mouse;
import rip.autumn.annotations.Label;
import rip.autumn.events.packet.ReceivePacketEvent;
import rip.autumn.events.packet.SendPacketEvent;
import rip.autumn.events.player.MotionUpdateEvent;
import rip.autumn.events.player.MoveEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.EnumOption;
import rip.autumn.utils.Logger;
import rip.autumn.utils.MovementUtils;
import rip.autumn.utils.PlayerUtils;
import rip.autumn.utils.Stopwatch;
import rip.autumn.utils.pathfinding.CustomVec3;
import rip.autumn.utils.pathfinding.PathfindingUtils;

@Label(value="Teleport")
@Category(value=ModuleCategory.MOVEMENT)
public final class TeleportMod
extends Module {
    private final EnumOption<Mode> mode = new EnumOption<Mode>("Mode", Mode.MINEPLEX);
    private final Stopwatch timer = new Stopwatch();
    private CustomVec3 target;
    private int stage;

    @Override
    public void onEnabled() {
        this.stage = 0;
    }

    @Listener(value=SendPacketEvent.class)
    public final void onSendPacket(SendPacketEvent event) {
        if (this.stage == 1 && !this.timer.elapsed(6000L)) {
            event.setCancelled();
        }
    }

    @Listener(value=ReceivePacketEvent.class)
    public final void onReceivePacket(ReceivePacketEvent event) {
        if (this.stage == 1 && !this.timer.elapsed(6000L) && (event.getPacket() instanceof S02PacketChat || event.getPacket() instanceof S45PacketTitle)) {
            event.setCancelled();
        }
    }

    @Listener(value=MotionUpdateEvent.class)
    public final void onMotionUpdate(MotionUpdateEvent event) {
        int x = TeleportMod.mc.objectMouseOver.getBlockPos().getX();
        int y = TeleportMod.mc.objectMouseOver.getBlockPos().getY() + 1;
        int z = TeleportMod.mc.objectMouseOver.getBlockPos().getZ();
        switch (this.stage) {
            case 0: {
                if (!Mouse.isButtonDown((int)1) || TeleportMod.mc.thePlayer.isSneaking() || !TeleportMod.mc.inGameHasFocus) break;
                this.timer.reset();
                this.target = new CustomVec3(x, y, z);
                this.killPlayer();
                this.stage = 1;
                break;
            }
            case 1: {
                if (!this.timer.elapsed(6000L)) break;
                mc.getNetHandler().addToSendQueueSilent(new C0CPacketInput(0.0f, 0.0f, true, true));
                for (CustomVec3 vec3 : PathfindingUtils.computePath(new CustomVec3(TeleportMod.mc.thePlayer.posX, TeleportMod.mc.thePlayer.posY, TeleportMod.mc.thePlayer.posZ), this.target)) {
                    mc.getNetHandler().addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(vec3.getX(), vec3.getY(), vec3.getZ(), true));
                }
                Logger.log("Teleported");
                TeleportMod.mc.thePlayer.setPosition(this.target.getX(), this.target.getY(), this.target.getZ());
                this.stage = 0;
            }
        }
        if (TeleportMod.mc.thePlayer.hurtTime == 9) {
            this.timer.reset();
        }
    }

    @Listener(value=MoveEvent.class)
    public final void onMove(MoveEvent event) {
        if (this.stage == 1 & !this.timer.elapsed(6000L)) {
            MovementUtils.setSpeed(event, 0.0);
        }
    }

    private void killPlayer() {
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        for (int i = 0; i < 20; ++i) {
            double offset = 0.0601f;
            int j = 0;
            while ((double)j < (double)PlayerUtils.getMaxFallDist() / (double)0.0601f + 1.0) {
                netHandler.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(TeleportMod.mc.thePlayer.posX, TeleportMod.mc.thePlayer.posY + (double)0.0601f, TeleportMod.mc.thePlayer.posZ, false));
                netHandler.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(TeleportMod.mc.thePlayer.posX, TeleportMod.mc.thePlayer.posY + (double)5.0E-4f, TeleportMod.mc.thePlayer.posZ, false));
                ++j;
            }
        }
        netHandler.addToSendQueueSilent(new C03PacketPlayer(true));
    }

    private static enum Mode {
        MINEPLEX;

    }
}

