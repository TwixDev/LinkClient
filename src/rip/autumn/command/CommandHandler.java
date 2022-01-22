/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.command;

import me.zane.basicbus.api.annotations.Listener;
import rip.autumn.command.CommandManager;
import rip.autumn.events.game.SendMessageEvent;

public final class CommandHandler {
    private final CommandManager commandManager;

    public CommandHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Listener(value=SendMessageEvent.class)
    public final void onMessage(SendMessageEvent event) {
        String msg = event.getMessage();
        if (msg.length() > 0 && msg.startsWith(".")) {
            event.setCancelled(this.commandManager.execute(msg));
        }
    }
}

