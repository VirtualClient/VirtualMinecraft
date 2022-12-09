package gg.virtualclient.virtualminecraft.event

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface MinecraftWindowResizedCallback {

    fun resized(scaledWidth: Int, scaledHeight: Int, frameBufferWidth: Int, frameBufferHeight: Int)

    companion object {

        @JvmField val EVENT: Event<MinecraftWindowResizedCallback> = EventFactory.createArrayBacked(MinecraftWindowResizedCallback::class.java) { callbacks ->
            MinecraftWindowResizedCallback { width, height, fbWidth, fbHeight -> callbacks.forEach { it.resized(width, height, fbWidth, fbHeight) } }
        }

    }

}