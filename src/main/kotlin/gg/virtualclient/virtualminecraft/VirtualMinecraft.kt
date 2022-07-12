package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualevents.bus.EventBusImpl
import net.minecraft.client.MinecraftClient

object VirtualMinecraft {

    @JvmStatic
    val eventBus = EventBusImpl()

    val isRunningOnMac: Boolean
        get() {
            return MinecraftClient.IS_SYSTEM_MAC
        }

}