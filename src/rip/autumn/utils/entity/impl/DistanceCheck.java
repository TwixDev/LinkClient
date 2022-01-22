/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.utils.entity.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import rip.autumn.module.option.impl.DoubleOption;
import rip.autumn.utils.entity.ICheck;

public final class DistanceCheck
implements ICheck {
    private final DoubleOption distance;

    public DistanceCheck(DoubleOption distance) {
        this.distance = distance;
    }

    @Override
    public boolean validate(Entity entity) {
        return (double)Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) <= (Double)this.distance.getValue();
    }
}

