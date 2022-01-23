package me.superskidder.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import me.superskidder.events.EventUpdate;
import me.superskidder.manager.module.Module;
import me.superskidder.manager.module.ModuleType;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {

    public Sprint(String name, String description, ModuleType type) {
        super(name, description, type);
        setKey(Keyboard.KEY_L);
    }

    @EventTarget
    public void onUpdate(EventUpdate event){
        if(mc.thePlayer.moveForward != 0|| mc.thePlayer.moveStrafing != 0){
            mc.thePlayer.setSprinting(true);
        }
    }
}
