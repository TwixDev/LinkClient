package me.superskidder.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import me.superskidder.Link;
import me.superskidder.events.EventRender2D;
import me.superskidder.manager.module.Module;
import me.superskidder.manager.module.ModuleManager;
import me.superskidder.manager.module.ModuleType;
import me.superskidder.utils.TimerHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HUD extends Module {
    ArrayList<Module> sorted = new ArrayList<>();
    TimerHelper timer = new TimerHelper();

    public HUD(String name, String description, ModuleType type) {
        super(name, description, type);
        setKey(Keyboard.KEY_H);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        sorted = Link.Instance.moduleManager.mods;
    }

    @EventTarget
    public void onRender(EventRender2D e) {
        ScaledResolution sr = new ScaledResolution(mc);
        mc.fontRendererObj.drawStringWithShadow(Link.Instance.CLIENT_NAME + " " + Link.Instance.CLIENT_VERSION, 10, 10, -1);
        if (timer.delay(500)) {
            sorted.sort((m1, m2) -> mc.fontRendererObj.getStringWidth(m2.getName() + m2.getSuffix()) - mc.fontRendererObj.getStringWidth(m1.getName() + m1.getSuffix()));
        }

        int y = 2;
        for (Module m : sorted) {
            if(!m.isEnabled())
                continue;
            mc.fontRendererObj.drawStringWithShadow(m.getName() + EnumChatFormatting.GRAY + m.getSuffix(), sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.getName() + EnumChatFormatting.GRAY + m.getSuffix()) - 2, y, -1);
            y += mc.fontRendererObj.FONT_HEIGHT+2;
        }

    }
}
