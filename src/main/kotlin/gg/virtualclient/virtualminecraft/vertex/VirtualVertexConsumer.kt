package gg.virtualclient.virtualminecraft.vertex

import gg.virtualclient.virtualminecraft.VirtualMatrixStack
import net.minecraft.client.render.VertexConsumer
import java.awt.Color

open class VirtualVertexConsumer(private val handle: VertexConsumer) {


    fun texture(u: Float, v: Float): VirtualVertexConsumer {
        handle.texture(u, v)
        return this
    }

    fun overlay(u: Int, v: Int): VirtualVertexConsumer {
        handle.overlay(u, v)
        return this
    }

    fun overlay(uv: Int): VirtualVertexConsumer {
        handle.overlay(uv)
        return this
    }

    fun light(u: Int, v: Int): VirtualVertexConsumer {
        handle.light(u, v)
        return this
    }

    fun light(uv: Int): VirtualVertexConsumer {
        handle.light(uv)
        return this
    }

    fun normal(x: Float, y: Float, z: Float): VirtualVertexConsumer {
        handle.normal(x, y, z)
        return this
    }

    fun next() = handle.next()


    fun color(red: Int, green: Int, blue: Int, alpha: Int): VirtualVertexConsumer {
        handle.color(red, green, blue, alpha)
        return this
    }

    fun color(red: Float, green: Float, blue: Float, alpha: Float): VirtualVertexConsumer {
        handle.color(red, green, blue, alpha)
        return this
    }

    fun color(color: Color): VirtualVertexConsumer {
        handle.color(color.red, color.green, color.blue, color.alpha)
        return this
    }

    fun vertex(x: Double, y: Double, z: Double): VirtualVertexConsumer {
        handle.vertex(x, y, z)
        return this
    }

    fun vertex(stack: VirtualMatrixStack, x: Double, y: Double, z: Double): VirtualVertexConsumer {
        handle.vertex(
            stack.peek().model,
            x.toFloat(),
            y.toFloat(),
            z.toFloat()
        )
        return this
    }

    fun normal(stack: VirtualMatrixStack, x: Float, y: Float, z: Float): VirtualVertexConsumer {
        handle.normal(stack.peek().normal, x, y, z)
        return this
    }

    fun vertex(
        x: Float, y: Float, z: Float, red: Float, green: Float, blue: Float, alpha: Float,
        u: Float, v: Float, overlay: Int, light: Int, normalX: Float, normalY: Float,
        normalZ: Float
    ) {
        handle.vertex(x, y, z, red, green, blue, alpha, u, v, overlay, light, normalX, normalY, normalZ)
    }

}