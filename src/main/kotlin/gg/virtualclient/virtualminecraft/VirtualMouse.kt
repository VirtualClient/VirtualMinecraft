package gg.virtualclient.virtualminecraft

import net.minecraft.client.MinecraftClient
import net.minecraft.util.Util

import kotlin.math.max

object VirtualMouse {

    @JvmStatic
    val rawX: Double
        get() {
            return MinecraftClient.getInstance().mouse.x
        }

    @JvmStatic
    val rawY: Double
        get() {
            return MinecraftClient.getInstance().mouse.y
        }

    @JvmStatic
    val scaledX: Double
        get() {
            return rawX * VirtualWindow.scaledWidth / max(1, VirtualWindow.windowWidth)
        }

    @JvmStatic
    val scaledY: Double
        get() {
            return rawY * VirtualWindow.scaledHeight / max(1, VirtualWindow.windowHeight)
        }

}