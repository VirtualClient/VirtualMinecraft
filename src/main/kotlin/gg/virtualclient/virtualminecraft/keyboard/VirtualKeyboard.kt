package gg.virtualclient.virtualminecraft.keyboard

import gg.virtualclient.virtualminecraft.VirtualMinecraft
import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW

object VirtualKeyboard {

    @JvmStatic
    fun setRepeatEvents(repeatEvents: Boolean) {
        MinecraftClient.getInstance().keyboard.setRepeatEvents(repeatEvents)
    }

    fun isControlKeyDown(): Boolean {
        if(VirtualMinecraft.isRunningOnMac) {
            //MacOS has the super key as their control key
            return isKeyPressed(Key.KEY_LEFT_SUPER) || isKeyPressed(Key.KEY_RIGHT_SUPER)
        }
        return isKeyPressed(Key.KEY_LEFT_SHIFT) || isKeyPressed(Key.KEY_RIGHT_SHIFT)
    }

    fun isShiftKeyDown(): Boolean {
        return isKeyPressed(Key.KEY_LEFT_SHIFT) || isKeyPressed(Key.KEY_RIGHT_SHIFT)
    }

    fun isAltKeyDown(): Boolean {
        return isKeyPressed(Key.KEY_LEFT_ALT) || isKeyPressed(Key.KEY_RIGHT_ALT)
    }

    @JvmStatic
    fun isKeyPressed(key: Int): Boolean {
        if(Key.UNKNOWN.getKeyCode() == key)
            return false
        return GLFW.glfwGetKey(MinecraftClient.getInstance().window.handle, key) == GLFW.GLFW_PRESS
    }

    @JvmStatic
    fun isKeyPressed(key: Key): Boolean {
        return isKeyPressed(key.getKeyCode())
    }

}