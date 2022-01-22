/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals;

import me.zane.basicbus.api.annotations.Listener;
import rip.autumn.annotations.Label;
import rip.autumn.events.render.RenderNametagEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.option.impl.BoolOption;

@Label(value="Streamer")
@Category(value=ModuleCategory.VISUALS)
public final class StreamerMod
extends Module {
    public static final String ENEMY = "Enemy";
    public static final String YOU = "You";
    public static final String FRIEND = "Friend";
    public final BoolOption hideScoreboard = new BoolOption("Hide Scoreboard", true);
    public final BoolOption hideChat = new BoolOption("Hide Chat", true);

    public StreamerMod() {
        this.addOptions(this.hideScoreboard, this.hideChat);
    }

    public boolean shouldHideScoreboard() {
        return this.isEnabled() && this.hideScoreboard.getValue() != false;
    }

    public boolean shouldHideChat() {
        return this.isEnabled() && this.hideChat.getValue() != false;
    }

    @Listener(value=RenderNametagEvent.class)
    public void onRenderNameTag(RenderNametagEvent event) {
        event.setRenderedName(ENEMY);
    }
}

