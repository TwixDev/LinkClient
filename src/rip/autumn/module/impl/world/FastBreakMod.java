/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.world;

import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;
import rip.autumn.annotations.Label;
import rip.autumn.events.player.BlockDamagedEvent;
import rip.autumn.events.player.MotionUpdateEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;

@Label(value="Fast Break")
@Category(value=ModuleCategory.WORLD)
@Aliases(value={"fastbreak"})
public final class FastBreakMod
extends Module {
    @Listener(value=MotionUpdateEvent.class)
    public final void onUpdate(MotionUpdateEvent event) {
        if (event.isPre()) {
            FastBreakMod.mc.playerController.blockHitDelay = 0;
        }
    }

    @Listener(value=BlockDamagedEvent.class)
    public void onBlockDamaged(BlockDamagedEvent event) {
        PlayerControllerMP playerController = FastBreakMod.mc.playerController;
        BlockPos pos = event.getBlockPos();
        FastBreakMod.mc.thePlayer.swingItem();
        playerController.curBlockDamageMP += this.getBlock(pos.getX(), pos.getY(), pos.getZ()).getPlayerRelativeBlockHardness(FastBreakMod.mc.thePlayer, FastBreakMod.mc.theWorld, pos) * 0.186f;
    }

    public Block getBlock(double posX, double posY, double posZ) {
        BlockPos pos = new BlockPos((int)posX, (int)posY, (int)posZ);
        return FastBreakMod.mc.theWorld.getChunkFromBlockCoords(pos).getBlock(pos);
    }
}

