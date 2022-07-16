package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.adventure.asMinecraft
import net.kyori.adventure.text.Component
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack

abstract class VirtualScreen(title: Component) : Screen(title.asMinecraft()) {

    val height: Int
        get() {
            return super.height
        }

    val width: Int
        get() {
            return super.width
        }

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

    override fun init() {
        super.init()
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun keyReleased(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyReleased(keyCode, scanCode, modifiers)
    }

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        return super.charTyped(chr, modifiers)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean {
        return super.mouseScrolled(mouseX, mouseY, amount)
    }

    override fun tick() {
        super.tick()
    }

    override fun close() {
        super.close()
    }

    final override fun renderBackground(matrices: MatrixStack?, vOffset: Int) {
        renderBackground(VirtualMatrixStack(), vOffset)
    }

    open fun renderBackground(matrices: VirtualMatrixStack, vOffset: Int = 0) {
        super.renderBackground(matrices.toMC(), vOffset)
    }
}