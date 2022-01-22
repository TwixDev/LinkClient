/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.module.impl.visuals.hud.impl.tabgui.handling.handlers;

import rip.autumn.module.ModuleCategory;
import rip.autumn.module.impl.visuals.hud.impl.tabgui.TabHandler;
import rip.autumn.module.impl.visuals.hud.impl.tabgui.block.TabBlock;
import rip.autumn.module.impl.visuals.hud.impl.tabgui.handling.TabFactory;
import rip.autumn.module.impl.visuals.hud.impl.tabgui.tab.Tab;
import rip.autumn.module.impl.visuals.hud.impl.tabgui.tab.tabs.ModCategoryTab;

public class ModCategoryTabFactory
implements TabFactory<ModuleCategory> {
    @Override
    public Tab<ModuleCategory> parse(TabHandler handler, ModuleCategory stateObject, Tab<?> parent, TabBlock children, TabBlock container) {
        return new ModCategoryTab(handler, stateObject, parent, children, container);
    }

    @Override
    public Class<ModuleCategory> getHandledType() {
        return ModuleCategory.class;
    }
}

