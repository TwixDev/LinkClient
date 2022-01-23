package me.superskidder.modules.render;

import me.superskidder.manager.module.Module;
import me.superskidder.manager.module.ModuleType;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {

    public ClickGui(String name, String description, ModuleType type) {
        super(name, description, type);
        this.setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(new me.superskidder.guis.ingame.clickgui.ClickGui());
        this.toggle();
    }
}
