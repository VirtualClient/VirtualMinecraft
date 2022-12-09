package gg.virtualclient.virtualminecraft.keyboard

import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW

object VirtualKeyboard {

    @JvmStatic
    fun setRepeatEvents(repeatEvents: Boolean) {
        //It seems like this was removed in 1.19.3?

        //#if MC<11903
        //$$ MinecraftClient.getInstance().keyboard.setRepeatEvents(repeatEvents)
        //#endif
    }

    fun isControlKeyDown(): Boolean {
        if(MinecraftClient.IS_SYSTEM_MAC) {
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
    fun isKeyComboCtrlA(key: Int): Boolean = key == Key.KEY_A.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlC(key: Int): Boolean = key == Key.KEY_C.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlV(key: Int): Boolean = key == Key.KEY_V.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlX(key: Int): Boolean = key == Key.KEY_X.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlY(key: Int): Boolean = key == Key.KEY_Y.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlZ(key: Int): Boolean = key == Key.KEY_Z.getKeyCode() && isControlKeyDown() && !isShiftKeyDown() && !isAltKeyDown()
    @JvmStatic
    fun isKeyComboCtrlShiftZ(key: Int): Boolean = key == Key.KEY_Z.getKeyCode() && isControlKeyDown() && isShiftKeyDown() && !isAltKeyDown()

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