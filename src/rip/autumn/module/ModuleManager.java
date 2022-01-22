/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package rip.autumn.module;

import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import rip.autumn.annotations.Label;
import rip.autumn.core.Autumn;
import rip.autumn.file.FileManager;
import rip.autumn.module.Module;
import rip.autumn.module.ModuleCategory;
import rip.autumn.module.annotations.Aliases;
import rip.autumn.module.annotations.Bind;
import rip.autumn.module.annotations.Category;
import rip.autumn.module.impl.combat.AntiAimMod;
import rip.autumn.module.impl.combat.AntiBotMod;
import rip.autumn.module.impl.combat.AuraMod;
import rip.autumn.module.impl.combat.CriticalsMod;
import rip.autumn.module.impl.combat.RegenMod;
import rip.autumn.module.impl.combat.VelocityMod;
import rip.autumn.module.impl.exploit.AntiVanishMod;
import rip.autumn.module.impl.exploit.DisablerMod;
import rip.autumn.module.impl.exploit.FinalHealthMod;
import rip.autumn.module.impl.exploit.PanicMod;
import rip.autumn.module.impl.exploit.PingSpoofMod;
import rip.autumn.module.impl.exploit.ServerCrasherMod;
import rip.autumn.module.impl.movement.AntiFallMod;
import rip.autumn.module.impl.movement.FlightMod;
import rip.autumn.module.impl.movement.LongJumpMod;
import rip.autumn.module.impl.movement.NoSlowMod;
import rip.autumn.module.impl.movement.SpeedMod;
import rip.autumn.module.impl.movement.SprintMod;
import rip.autumn.module.impl.movement.TargetStrafeMod;
import rip.autumn.module.impl.movement.TeleportMod;
import rip.autumn.module.impl.player.AntiDesyncMod;
import rip.autumn.module.impl.player.AutoArmorMod;
import rip.autumn.module.impl.player.ChatBypassMod;
import rip.autumn.module.impl.player.FriendProtectMod;
import rip.autumn.module.impl.player.InventoryManagerMod;
import rip.autumn.module.impl.player.InventoryWalkMod;
import rip.autumn.module.impl.player.KillSayMod;
import rip.autumn.module.impl.player.PhaseMod;
import rip.autumn.module.impl.visuals.AnimationsMod;
import rip.autumn.module.impl.visuals.ChamsMod;
import rip.autumn.module.impl.visuals.ClickGUIMod;
import rip.autumn.module.impl.visuals.CrosshairMod;
import rip.autumn.module.impl.visuals.DamageColorMod;
import rip.autumn.module.impl.visuals.ESP2DMod;
import rip.autumn.module.impl.visuals.FullBrightMod;
import rip.autumn.module.impl.visuals.GlowMod;
import rip.autumn.module.impl.visuals.NoEffectsMod;
import rip.autumn.module.impl.visuals.SkeletonsMod;
import rip.autumn.module.impl.visuals.StorageESPMod;
import rip.autumn.module.impl.visuals.StreamerMod;
import rip.autumn.module.impl.visuals.TargetHUDMod;
import rip.autumn.module.impl.visuals.hud.HUDMod;
import rip.autumn.module.impl.world.AutoToolMod;
import rip.autumn.module.impl.world.ChestAura;
import rip.autumn.module.impl.world.DestroyerMod;
import rip.autumn.module.impl.world.FastBreakMod;
import rip.autumn.module.impl.world.NoFallMod;
import rip.autumn.module.impl.world.ScaffoldMod;
import rip.autumn.module.impl.world.TimeChangerMod;
import rip.autumn.module.keybinds.KeyBindHandler;

public final class ModuleManager {
    private final ImmutableList<Module> modules;
    private final File dataFile;

    public ModuleManager() {
        Autumn.EVENT_BUS_REGISTRY.eventBus.subscribe(new KeyBindHandler(this));
        this.dataFile = new File(FileManager.HOME_DIRECTORY, "ModuleData.json");
        this.modules = this.collectModules(SprintMod.class, FlightMod.class, ScaffoldMod.class, AuraMod.class, DamageColorMod.class, InventoryWalkMod.class, NoSlowMod.class, CriticalsMod.class, ChamsMod.class, VelocityMod.class, SpeedMod.class, ServerCrasherMod.class, RegenMod.class, StreamerMod.class, CrosshairMod.class, AntiAimMod.class, AutoArmorMod.class, AntiVanishMod.class, FastBreakMod.class, AutoToolMod.class, DisablerMod.class, SkeletonsMod.class, LongJumpMod.class, AntiFallMod.class, NoFallMod.class, AntiBotMod.class, PingSpoofMod.class, ESP2DMod.class, HUDMod.class, InventoryManagerMod.class, GlowMod.class, FriendProtectMod.class, ChestAura.class, AntiDesyncMod.class, AnimationsMod.class, ClickGUIMod.class, FullBrightMod.class, StorageESPMod.class, NoEffectsMod.class, KillSayMod.class, FinalHealthMod.class, TargetStrafeMod.class, ChatBypassMod.class, PhaseMod.class, PanicMod.class, DestroyerMod.class, TeleportMod.class, TimeChangerMod.class, TargetHUDMod.class);
        this.saveData();
        this.loadData();
    }

    public ImmutableList<Module> getModules() {
        return this.modules;
    }

    @SafeVarargs
    private final ImmutableList<Module> collectModules(Class<? extends Module> ... classes) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Class<? extends Module> clazz : classes) {
            if (!clazz.isAnnotationPresent(Label.class) || !clazz.isAnnotationPresent(Category.class)) continue;
            try {
                Module module = clazz.newInstance();
                builder.add((Object)module);
                if (clazz.isAnnotationPresent(Bind.class)) {
                    module.getKeyBind().setKey(clazz.getAnnotation(Bind.class).value());
                }
                if (!clazz.isAnnotationPresent(Aliases.class)) continue;
                module.setAliases(clazz.getAnnotation(Aliases.class).value());
            }
            catch (IllegalAccessException | InstantiationException reflectiveOperationException) {
                // empty catch block
            }
        }
        return builder.build();
    }

    public final <T extends Module> T getModuleOrNull(Class<T> clazz) {
        ImmutableList<Module> modules = this.modules;
        int modulesSize = modules.size();
        for (int i = 0; i < modulesSize; ++i) {
            Module module = (Module)modules.get(i);
            if (module.getClass() != clazz) continue;
            return (T)module;
        }
        return null;
    }

    public final <T extends Module> T getModuleOrNull(String moduleName) {
        ImmutableList<Module> modules = this.modules;
        int modulesSize = modules.size();
        for (int i = 0; i < modulesSize; ++i) {
            Module module = (Module)modules.get(i);
            String[] aliases = module.getAliases();
            int aliasesLength = aliases.length;
            for (int i1 = 0; i1 < aliasesLength; ++i1) {
                if (!aliases[i1].equalsIgnoreCase(moduleName)) continue;
                return (T)module;
            }
        }
        return null;
    }

    public final List<Module> getModulesForCategory(ModuleCategory category) {
        ArrayList<Module> localModules = new ArrayList<Module>();
        ImmutableList<Module> modules = this.modules;
        int modulesSize = modules.size();
        for (int i = 0; i < modulesSize; ++i) {
            Module module = (Module)modules.get(i);
            if (module.getCategory() != category) continue;
            localModules.add(module);
        }
        return localModules;
    }

    public final void saveData() {
        JsonObject js = new JsonObject();
        try {
            this.dataFile.createNewFile();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        for (Module module : this.modules) {
            JsonObject jsf = new JsonObject();
            jsf.addProperty("Key", (Number)module.getKeyBind().getKeyCode());
            js.add(module.getLabel(), (JsonElement)jsf);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.dataFile));
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)js));
            writer.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public final void loadData() {
        try (FileReader reader = new FileReader(this.dataFile.toPath().toFile());){
            JsonObject object = new JsonParser().parse((Reader)reader).getAsJsonObject();
            for (Module module : this.modules) {
                JsonObject moduleObject;
                if (!object.has(module.getLabel()) || !(moduleObject = object.get(module.getLabel()).getAsJsonObject()).has("Key")) continue;
                module.getKeyBind().setKeyCode(moduleObject.get("Key").getAsInt());
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}

