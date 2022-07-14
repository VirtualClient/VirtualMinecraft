package gg.virtualclient.virtualminecraft

import gg.virtualclient.virtualminecraft.adventure.asMinecraft
import net.kyori.adventure.text.Component
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.util.math.random.Random

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

    fun getWidth(text: Component): Int {
        return textRenderer.getWidth(text.asMinecraft())
    }

    fun draw(matrices: VirtualMatrixStack, text: String, x: Float, y: Float, color: Int): Int {
        return textRenderer.draw(matrices.toMC(), text, x, y, color)
    }

    fun drawWithShadow(matrices: VirtualMatrixStack, text: String, x: Float, y: Float, color: Int): Int {
        return textRenderer.drawWithShadow(matrices.toMC(), text, x, y, color)
    }

    fun draw(matrices: VirtualMatrixStack, text: Component, x: Float, y: Float, color: Int): Int {
        return textRenderer.draw(matrices.toMC(), text.asMinecraft(), x, y, color)
    }

    fun drawWithShadow(matrices: VirtualMatrixStack, text: Component, x: Float, y: Float, color: Int): Int {
        return textRenderer.drawWithShadow(matrices.toMC(), text.asMinecraft(), x, y, color)
    }

    companion object {

        private lateinit var mcInstance: VirtualTextRenderer

        @JvmStatic
        fun getInstance(): VirtualTextRenderer {
            if(!::mcInstance.isInitialized) {
                val textRenderer = MinecraftClient.getInstance().textRenderer
                    ?: throw IllegalStateException("Minecraft Font-Renderer not initialized.")
                mcInstance = VirtualTextRenderer(textRenderer)
            }
            return mcInstance
        }

    }

}