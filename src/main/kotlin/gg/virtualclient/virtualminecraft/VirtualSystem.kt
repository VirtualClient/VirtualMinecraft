package gg.virtualclient.virtualminecraft

import net.minecraft.util.Util

object VirtualSystem {

    @JvmStatic
    fun openURL(url: String) {
        Util.getOperatingSystem().open(url)
    }

}