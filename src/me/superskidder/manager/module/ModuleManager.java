package me.superskidder.manager.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import me.superskidder.events.EventKey;
import me.superskidder.modules.movement.Sprint;
import me.superskidder.modules.render.ClickGui;
import me.superskidder.modules.render.HUD;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> mods = new ArrayList<>();

    public ModuleManager() {
        EventManager.register(this);
        mods.add(new Sprint("Sprint","Automatic sprint",ModuleType.MOVEMENT));
        mods.add(new HUD("HUD","Display module & some information",ModuleType.RENDER));
        mods.add(new ClickGui("ClickGui","Display module & some values",ModuleType.RENDER));


    }

    public ArrayList<Module> getModules() {
        return mods;
    }

    public Module getModule(String name) {
        for (Module m : mods) {
            if (m.name.equals(name))
                return m;
        }
        return null;
    }

    @EventTarget
    public void onKey(EventKey e){
        for (Module module : mods) {
            if(module.getKey() == e.getKey())
                module.toggle();
        }
    }


}
