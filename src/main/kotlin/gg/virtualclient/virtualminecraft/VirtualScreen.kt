package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.adventure.asMinecraft
import net.kyori.adventure.text.Component
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack

//#if MC>=12000
import net.minecraft.client.gui.DrawContext
//#endif

abstract class VirtualScreen(title: Component) : Screen(title.asMinecraft()) {

    val height: Int
        get() {
            return super.height
        }

    val width: Int
        get() {
            return super.width
        }


    //#if MC>=12000
    /*
     * Minecraft 1.20 introduced draw contexts, which are currently not natively integrated in VirtualMinecraft.
     * In order to avoid constantly creating new draw contexts during a single render process, we store the render
     * context during a render here.
     */
    private var drawContexts = mutableListOf<DrawContext>()

    private inline fun <R> withDrawContext(matrixStack: VirtualMatrixStack, block: (DrawContext) -> R) {
        val client = this.client!!
        val context = drawContexts.lastOrNull() ?: DrawContext(client, client.bufferBuilders.entityVertexConsumers)

        context.matrices.push()
        context.applyMatrixStack(matrixStack)

        block(context)
        context.matrices.pop()
    }
    //#endif



    //#if MC>=12000
    final override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        this.drawContexts.add(context)
        try {
            render(VirtualMatrixStack(context.matrices), mouseX, mouseY, delta)
        } finally {
            this.drawContexts.remove(context)
        }
    }
    //#endif


    open fun render(matrices: VirtualMatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        //#if MC>=12000
        withDrawContext(matrices) {
            super.render(it, mouseX, mouseY, delta)
        }
        //#elseif MC>=11604
        //$$ super.render(matrices.toMC(), mouseX, mouseY, delta)
        //#else
        //$$ matrices.runWithGlobalState {
        //$$ super.render(mouseX, mouseY, delta)
        //$$ }
        //#endif
    }

    final override fun init() {
        onScreenInit()
    }

    open fun onScreenInit() {
        super.init()
    }

    final override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return onKeyPressed(keyCode, scanCode, modifiers)
    }

    open fun onKeyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    final override fun keyReleased(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return onKeyReleased(keyCode, scanCode, modifiers)
    }

    open fun onKeyReleased(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyReleased(keyCode, scanCode, modifiers)
    }

    final override fun charTyped(chr: Char, modifiers: Int): Boolean {
        return onCharTyped(chr, modifiers)
    }

    open fun onCharTyped(chr: Char, modifiers: Int): Boolean {
        return super.charTyped(chr, modifiers)
    }

    final override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return onMouseClicked(mouseX, mouseY, button)
    }

    open fun onMouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return super.mouseClicked(mouseX, mouseY, button)
    }

    final override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return onMouseReleased(mouseX, mouseY, button)
    }

    open fun onMouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return super.mouseReleased(mouseX, mouseY, button)
    }

    final override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        return onMouseDragged(mouseX, mouseY, button, deltaX, deltaY)
    }

    open fun onMouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)
    }

    final override fun mouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean {
        return onMouseScrolled(mouseX, mouseY, amount)
    }

    open fun onMouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean {
        return super.mouseScrolled(mouseX, mouseY, amount)
    }

    final override fun tick() {
        onTick()
    }

    open fun onTick() {
        super.tick()
    }

    final override fun close() {
        onScreenClosed()
    }

    open fun onScreenClosed() {
        super.close()
    }


    //#if MC<=11903
    //$$ final override fun renderBackground(matrices: MatrixStack, vOffset: Int) {
    //$$    renderBackground(VirtualMatrixStack(matrices))
    //$$ }
    //#endif

    //#if MC<=11904
    //$$ final override fun renderBackground(matrices: MatrixStack) {
    //$$    renderBackground(VirtualMatrixStack(matrices))
    //$$ }
    //#endif

    //#if MC>=12000
    override fun renderBackground(context: DrawContext) {
        this.drawContexts.add(context)
        try {
            renderBackground(VirtualMatrixStack(context.matrices))
        } finally {
            this.drawContexts.remove(context)
        }
    }
    //#endif


    open fun renderBackground(matrices: VirtualMatrixStack) {
        //#if MC>=12000
        withDrawContext(matrices) {
            super.renderBackground(it)
        }
        //#else
        //$$ super.renderBackground(matrices.toMC())
        //#endif
    }
}