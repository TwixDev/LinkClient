package me.superskidder.guis.ingame.clickgui;

import me.superskidder.Link;
import me.superskidder.manager.module.Module;
import me.superskidder.manager.module.ModuleType;
import me.superskidder.utils.AnimationUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class ClickGui extends GuiScreen {
    public static float x, y;
    public static float width = 600, height = 400;
    AnimationUtils animation = new AnimationUtils();
    AnimationUtils animation2 = new AnimationUtils();
    AnimationUtils animation3 = new AnimationUtils();
    AnimationUtils animation4 = new AnimationUtils();

    ScaledResolution sr;
    private Module curModule;

    @Override
    public void initGui() {
        super.initGui();
        sr = new ScaledResolution(mc);
//        if (x == 0) {
        x = (sr.getScaledWidth() - width) / 2;
//        }
//        if (y == 0) {
        y = (sr.getScaledHeight() - height) / 2;
//        }

        width = 400;
        height = 230;


    }

    double start = -100;
    boolean dragging;
    double dragY, dragX;
    static ModuleType type = ModuleType.COMBAT;
    static float typeY = y;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        start = animation.animate(0, start, 0.12);
        GlStateManager.translate(0, start, 0);
        Gui.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 180).getRGB());
        Gui.drawRect(x, y, x + width * 0.2f, y + height, new Color(20, 20, 20, 220).getRGB());
        Gui.drawRect(x, y, x + width, y + 20, new Color(93, 93, 93, 255).getRGB());
        Gui.drawRect(x + width - 10, y + 8, x + width - 5, y + 13, new Color(255, 20, 20, 255).getRGB());

        mc.fontRendererObj.drawStringWithShadow(Link.Instance.CLIENT_NAME + " - ClickGui", x + 5, y + 5, -1);

        if (isHovered(x, y, x + width, y + 20, mouseX, mouseY) && dragging) {
            x = (float) (mouseX - dragX);
            y = (float) (mouseY - dragY);
        }
        if (!Mouse.isButtonDown(0)) {
            dragging = false;
        }

        //Categories List
        float getY = y + 30;
        for (ModuleType t : ModuleType.values()) {
            if (type == t) {
                break;
            }
            getY += 25;
        }
        typeY = animation2.animate(getY - 10, typeY, 0.2f);

        float start = y + 30;
        for (ModuleType t : ModuleType.values()) {
            if (type == t) {
                Gui.drawRect(x, typeY, x + width * 0.2f, typeY + 25, new Color(30, 125, 255).getRGB());
            }
            mc.fontRendererObj.drawStringWithShadow(t.name(), x + 5, start, -1);
            start += 25;
        }


        //ModList
        float modsY = y + 30;
        for (Module m : Link.Instance.moduleManager.mods) {
            if (m.getType() != type)
                continue;
            Gui.drawRect(x + width * 0.2f + 10, modsY, x + width - 10, modsY + 25, m.isEnabled() ? new Color(86, 86, 86, 255).getRGB() : new Color(68, 68, 68, 180).getRGB());
            mc.fontRendererObj.drawStringWithShadow(m.name, x + width * 0.2f + 20, modsY + 10, m.isEnabled() ? new Color(255, 255, 255, 255).getRGB() : new Color(200, 200, 200).getRGB());
            modsY += 30;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        dragging = isHovered(x, y, x + width, y + 20, mouseX, mouseY);
        if (dragging) {
            dragX = mouseX - x;
            dragY = mouseY - y;
        }

        //Categories List
        float start = y + 30;
        for (ModuleType t : ModuleType.values()) {
            if (isHovered(x, start - 10, x + width * 0.2f, start + 25, mouseX, mouseY))
                type = t;
            start += 25;
        }


        //ModList
        float modsY = y + 30;
        for (Module m : Link.Instance.moduleManager.mods) {
            if (m.getType() != type)
                continue;
            if (isHovered(x + width * 0.2f + 10, modsY, x + width - 10, modsY + 25, mouseX, mouseY)) {
                if (mouseButton == 0) {
                    m.toggle();
                } else {
                    curModule = m;
                }

            }
            modsY += 30;
        }
    }

    public boolean isHovered(float x, float y, float x1, float y1, float mouseX, float mouseY) {
        return mouseX > x && mouseX < x1 && mouseY > y && mouseY < y1;
    }
}

