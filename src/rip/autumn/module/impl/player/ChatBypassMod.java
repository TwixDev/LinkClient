/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.player;

import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.network.play.client.C01PacketChatMessage;
import rip.autumn.annotations.Label;
import rip.autumn.events.packet.SendPacketEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Category;

@Label(value="Chat Bypass")
@Category(value=ModuleCategory.PLAYER)
@Aliases(value={"chatbypass"})
public final class ChatBypassMod
extends Module {
    @Listener(value=SendPacketEvent.class)
    public final void onSendPacket(SendPacketEvent event) {
        if (event.getPacket() instanceof C01PacketChatMessage) {
            C01PacketChatMessage packetChatMessage = (C01PacketChatMessage)event.getPacket();
            if (packetChatMessage.getMessage().startsWith("/")) {
                return;
            }
            event.setCancelled();
            StringBuilder msg = new StringBuilder();
            for (char character : packetChatMessage.getMessage().toCharArray()) {
                msg.append(character + "\u061c");
            }
            mc.getNetHandler().addToSendQueueSilent(new C01PacketChatMessage(msg.toString().replaceFirst("%", "")));
        }
    }
}

