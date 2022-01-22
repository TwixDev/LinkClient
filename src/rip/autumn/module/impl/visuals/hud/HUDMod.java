/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals.hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.zane.basicbus.api.annotations.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import rip.autumn.annotations.Label;
import rip.autumn.core.Autumn;
import rip.autumn.events.render.RenderGuiEvent;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.impl.visuals.hud.Component;
import rip.autumn.module.impl.visuals.hud.impl.InfoComponent;
import rip.autumn.module.impl.visuals.hud.impl.ModList;
import rip.autumn.module.impl.visuals.hud.impl.TabComponent;
import rip.autumn.module.impl.visuals.hud.impl.Watermark;
import rip.autumn.module.option.impl.BoolOption;
import rip.autumn.module.option.impl.ColorOption;
import rip.autumn.module.option.impl.DoubleOption;
import rip.autumn.module.option.impl.EnumOption;

@Label(value="HUD")
@Category(value=ModuleCategory.VISUALS)
public final class HUDMod
extends Module {
    public static String clientName = Autumn.INSTANCE.getName() + " " + Autumn.INSTANCE.getVersion();
    private static final Minecraft mc = Minecraft.getMinecraft();
    public final ColorOption color = new ColorOption("Color", new Color(255, 0, 0));
    public final EnumOption<ArrayListPosition> arrayListPosition = new EnumOption<ArrayListPosition>("Mod List Position", ArrayListPosition.TOP);
    public final BoolOption tabGui = new BoolOption("Tab Gui", true);
    public final BoolOption watermark = new BoolOption("Watermark", true);
    public final BoolOption defaultFont = new BoolOption("Default Font", false);
    public final EnumOption<InfoDisplayMode> infoDisplayMode = new EnumOption<InfoDisplayMode>("Info Display Mode", InfoDisplayMode.LEFT);
    public final EnumOption<ArrayListColor> modListColorMode = new EnumOption<ArrayListColor>("Mod List Color Mode", ArrayListColor.PULSING);
    public final BoolOption modListSideBar = new BoolOption("Mod List Side Bar", false);
    public final BoolOption modListOutline = new BoolOption("Mod List Outline", true);
    public final BoolOption modListBackground = new BoolOption("Mod List Background", true);
    public final DoubleOption modListBackgroundAlpha = new DoubleOption("Mod List Background Alpha", 0.2, 0.0, 1.0, 0.05);
    private final List<Component> components = new ArrayList<Component>();

    public HUDMod() {
        this.setEnabled(true);
        this.setHidden(true);
        this.components.add(new ModList(this));
        this.components.add(new Watermark(this));
        this.components.add(new InfoComponent(this));
        this.components.add(new TabComponent(this));
        this.addOptions(this.color, this.tabGui, this.defaultFont, this.infoDisplayMode, this.arrayListPosition, this.modListColorMode, this.modListSideBar, this.modListOutline, this.modListBackground, this.modListBackgroundAlpha, this.watermark);
    }

    @Listener(value=RenderGuiEvent.class)
    public final void onRenderGui(RenderGuiEvent event) {
        if (HUDMod.mc.gameSettings.showDebugInfo) {
            return;
        }
        ScaledResolution sr = event.getScaledResolution();
        int componentsSize = this.components.size();
        for (int i = 0; i < componentsSize; ++i) {
            Component component = this.components.get(i);
            component.draw(sr);
        }
    }

    public static enum InfoDisplayMode {
        LEFT,
        RIGHT,
        OFF;

    }

    public static enum ArrayListColor {
        STATIC,
        PULSING,
        RAINBOW;

    }

    public static enum ArrayListPosition {
        BOTTOM,
        TOP;

    }
}

