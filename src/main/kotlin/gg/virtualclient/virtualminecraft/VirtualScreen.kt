package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.adventure.asMinecraft
import net.kyori.adventure.text.Component
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack

abstract class VirtualScreen(title: Component) : Screen(title.asMinecraft()) {

    final override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        render(VirtualMatrixStack(matrices), mouseX, mouseY, delta)
    }

    open fun render(matrices: VirtualMatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        //#if MC>=11604
        super.render(matrices.toMC(), mouseX, mouseY, delta)
        //#else
        //$$ matrices.runWithGlobalState {
        //$$ super.render(mouseX, mouseY, delta)
        //$$ }
        //#endif
    }


}