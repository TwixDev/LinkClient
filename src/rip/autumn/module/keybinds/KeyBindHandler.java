/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package rip.autumn.module.keybinds;

import com.google.common.collect.ImmutableList;
import me.zane.basicbus.api.annotations.Listener;
import rip.autumn.events.game.KeyPressEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleManager;

public final class KeyBindHandler {
    private final ModuleManager moduleManager;

    public KeyBindHandler(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Listener(value=KeyPressEvent.class)
    public final void onKeyPress(KeyPressEvent event) {
        ImmutableList<Module> modules = this.moduleManager.getModules();
        int keysSize = modules.size();
        for (int i = 0; i < keysSize; ++i) {
            Module module = (Module)modules.get(i);
            if (event.getKeyCode() != module.getKeyBind().getKeyCode()) continue;
            module.toggle();
        }
    }
}

