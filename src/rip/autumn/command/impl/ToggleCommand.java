/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.command.impl;

import rip.autumn.command.AbstractCommand;
import rip.autumn.core.Autumn;
import rip.autumn.module.Module;
import rip.autumn.utils.Logger;

public final class ToggleCommand
extends AbstractCommand {
    public ToggleCommand() {
        super("Toggle", "Toggle modules on and off.", "toggle <module>", "toggle", "t");
    }

    @Override
    public void execute(String ... arguments) {
        if (arguments.length == 2) {
            String moduleName = arguments[1];
            Object module = Autumn.MANAGER_REGISTRY.moduleManager.getModuleOrNull(moduleName);
            if (module != null) {
                ((Module)module).toggle();
            } else {
                Logger.log("'" + moduleName + "' is not a module.");
            }
        } else {
            this.usage();
        }
    }
}

