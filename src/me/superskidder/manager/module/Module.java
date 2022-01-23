package me.superskidder.manager.module;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;

public class Module {
    public String name;
    public String description;
    public int key;
    public ModuleType type;
    public Minecraft mc = Minecraft.getMinecraft();
    boolean enabled;
    private String suffix;

    public Module(String name, String description, ModuleType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onEnabled() {
        onEnable();
        EventManager.register(this);
    }

    public void onDisabled() {
        onDisable();
        EventManager.unregister(this);
    }

    public ModuleType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            onEnabled();
        } else {
            onDisabled();
        }
    }

    public String getSuffix() {
        return suffix == null ? "" : suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = " " + suffix;
    }
}
