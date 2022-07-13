package gg.virtualclient.virtualminecraft

import net.minecraft.client.MinecraftClient

object VirtualWindow {

    @JvmStatic
    val windowWidth: Int
        get() {
            return MinecraftClient.getInstance().window.width
        }

    @JvmStatic
    val windowHeight: Int
        get() {
            return MinecraftClient.getInstance().window.height
        }

    @JvmStatic
    val framebufferWidth: Int
        get() {
            return MinecraftClient.getInstance().window.framebufferWidth
        }

    @JvmStatic
    val framebufferHeight: Int
        get() {
            return MinecraftClient.getInstance().window.framebufferHeight
        }

    @JvmStatic
    val scaledWidth: Int
        get() {
            return MinecraftClient.getInstance().window.scaledWidth
        }


    @JvmStatic
    val scaledHeight: Int
        get() {
            return MinecraftClient.getInstance().window.scaledHeight
        }

    @JvmStatic
    val scaleFactor: Double
        get() {
            return MinecraftClient.getInstance().window.scaleFactor
        }

}