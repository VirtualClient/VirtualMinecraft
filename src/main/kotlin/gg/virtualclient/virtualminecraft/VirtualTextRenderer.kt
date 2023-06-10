package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.adventure.asMinecraft
import net.kyori.adventure.text.Component
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.text.Text
import net.minecraft.util.math.random.Random


//#if MC>=11904
private val TEXT_LAYER_TYPE = TextRenderer.TextLayerType.NORMAL;
//#elseif MC>=11602
//$$ private val TEXT_LAYER_TYPE = false;
//#endif

class VirtualTextRenderer(private val textRenderer: TextRenderer) {



    val fontHeight: Int
        get() {
            return textRenderer.fontHeight
        }

    val isRightToLeft: Boolean
        get() {
            return textRenderer.isRightToLeft
        }

    val random: Random
        get() {
            return textRenderer.random
        }

    fun getWidth(text: String): Int {
        return textRenderer.getWidth(text)
    }

    fun getWidth(text: Text): Int {
        return textRenderer.getWidth(text)
    }

    fun getWidth(text: Component): Int {
        return getWidth(text.asMinecraft())
    }

    fun draw(matrices: VirtualMatrixStack, text: String, x: Float, y: Float, color: Int): Int {
        val drawer = VertexConsumerProvider.immediate(Tessellator.getInstance().buffer)
        val i = textRenderer.draw(text, x, y, color, false, matrices.peek().model, drawer, TEXT_LAYER_TYPE, 0, 15728880)
        drawer.draw()
        return i
    }

    fun drawWithShadow(matrices: VirtualMatrixStack, text: String, x: Float, y: Float, color: Int): Int {
        val drawer = VertexConsumerProvider.immediate(Tessellator.getInstance().buffer)
        val i = textRenderer.draw(text, x, y, color, true, matrices.peek().model, drawer, TEXT_LAYER_TYPE, 0, 15728880)
        drawer.draw()
        return i
    }

    fun draw(matrices: VirtualMatrixStack, text: Text, x: Float, y: Float, color: Int): Int {
        val drawer = VertexConsumerProvider.immediate(Tessellator.getInstance().buffer)
        val i = textRenderer.draw(text, x, y, color, false, matrices.peek().model, drawer, TEXT_LAYER_TYPE, 0, 15728880)
        drawer.draw()
        return i
    }

    fun drawWithShadow(matrices: VirtualMatrixStack, text: Text, x: Float, y: Float, color: Int): Int {
        val drawer = VertexConsumerProvider.immediate(Tessellator.getInstance().buffer)
        val i = textRenderer.draw(text, x, y, color, true, matrices.peek().model, drawer, TEXT_LAYER_TYPE, 0, 15728880)
        drawer.draw()
        return i
    }

    fun draw(matrices: VirtualMatrixStack, text: Component, x: Float, y: Float, color: Int): Int {
        return draw(matrices, text.asMinecraft(), x, y, color)
    }

    fun drawWithShadow(matrices: VirtualMatrixStack, text: Component, x: Float, y: Float, color: Int): Int {
        return drawWithShadow(matrices, text.asMinecraft(), x, y, color)
    }

    companion object {

        private val mcInstance: VirtualTextRenderer by lazy {
            val textRenderer = MinecraftClient.getInstance().textRenderer
                ?: throw IllegalStateException("Minecraft Font-Renderer not initialized.")
            VirtualTextRenderer(textRenderer)
        }

        @JvmStatic
        fun getInstance(): VirtualTextRenderer {
            return mcInstance
        }

    }

}