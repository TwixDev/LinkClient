/*
 * Decompiled with CFR 0.150.
 */
package rip.autumn.core.registry.impl;

import me.zane.basicbus.api.bus.Bus;
import me.zane.basicbus.api.bus.impl.EventBusImpl;
import me.zane.basicbus.api.invocation.impl.ReflectionInvoker;
import rip.autumn.core.registry.Registry;
import rip.autumn.events.Event;

public final class EventBusRegistry
implements Registry {
    public final Bus<Event> eventBus = new EventBusImpl<Event>(new ReflectionInvoker());
}

