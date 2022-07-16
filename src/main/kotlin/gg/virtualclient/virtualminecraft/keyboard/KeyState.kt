package gg.virtualclient.virtualminecraft.keyboard

import org.lwjgl.glfw.GLFW
enum class KeyState {

    PRESSED,
    RELEASED,
    HOLD;

    companion object {
        @JvmStatic
        fun fromGLFW(action: Int): KeyState? {
            return when (action) {
                GLFW.GLFW_PRESS -> PRESSED
                GLFW.GLFW_RELEASE -> RELEASED
                GLFW.GLFW_REPEAT -> HOLD
                else -> null
            }
        }
    }

}