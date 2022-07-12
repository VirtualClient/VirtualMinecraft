package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.keyboard.Key
import net.kyori.adventure.text.Component
import java.util.function.BooleanSupplier

object MinecraftDebugger {

    @JvmStatic
    val customDebugKeyList: MutableList<DebugKey> = ArrayList()

    @JvmStatic
    fun registerDebugKeybinding(keyCode: Int, description: Component, callback: BooleanSupplier) {
        customDebugKeyList.add(DebugKey(keyCode, description, callback))
    }

    @JvmStatic
    fun registerDebugKeybinding(keyCode: Key, description: Component, callback: BooleanSupplier) {
        registerDebugKeybinding(keyCode.getKeyCode(), description, callback)
    }

}

data class DebugKey(val keyCode: Int, val description: Component, val callback: BooleanSupplier)