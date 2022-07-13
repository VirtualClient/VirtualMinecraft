package gg.virtualclient.virtualminecraft.vertex

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.render.*
import java.util.function.Supplier


class VirtualBufferBuilder(private val handle: BufferBuilder) : VirtualVertexConsumer(handle) {

    fun end() = handle.end()
    fun clear() = handle.clear()
    fun reset() = handle.reset()

    fun putByte(index: Int, value: Byte) = handle.putByte(index, value)
    fun putShort(index: Int, value: Short) = handle.putShort(index, value)
    fun putFloat(index: Int, value: Float) = handle.putFloat(index, value)

    fun isBuilding(): Boolean = handle.isBuilding

    fun beginWithActiveShader(mode: DrawMode, format: VertexFormat): VirtualBufferBuilder {
        //#if MC>=11700
        handle.begin(mode.mcMode, format)
        //#else
        //$$ handle.begin(mode.glMode, format)
        //#endif
        return this
    }

    fun beginWithDefaultShader(mode: DrawMode, format: VertexFormat): VirtualBufferBuilder {
        //#if MC>=11700
        val shader = defaultShaders[format] ?: throw IllegalArgumentException("No default shader for $format found.")
        RenderSystem.setShader(shader)
        //#endif
        return beginWithActiveShader(mode, format)
    }

    companion object {
        //#if MC>=11700
        @JvmStatic
        private val defaultShaders: MutableMap<VertexFormat, Supplier<Shader?>> = mutableMapOf(
            Pair(VertexFormats.LINES, Supplier { GameRenderer.getRenderTypeLinesShader() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR_LIGHT, Supplier { GameRenderer.getParticleShader() }),
            Pair(VertexFormats.POSITION, Supplier { GameRenderer.getPositionShader() }),
            Pair(VertexFormats.POSITION_COLOR, Supplier { GameRenderer.getPositionColorShader() }),
            Pair(VertexFormats.POSITION_COLOR_LIGHT, Supplier { GameRenderer.getPositionColorLightmapShader() }),
            Pair(VertexFormats.POSITION_TEXTURE, Supplier { GameRenderer.getPositionTexShader() }),
            Pair(VertexFormats.POSITION_COLOR_TEXTURE, Supplier { GameRenderer.getPositionColorTexShader() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR, Supplier { GameRenderer.getPositionTexColorShader() }),
            Pair(VertexFormats.POSITION_COLOR_TEXTURE_LIGHT, Supplier { GameRenderer.getPositionColorTexLightmapShader() }),
            Pair(VertexFormats.POSITION_TEXTURE_LIGHT_COLOR, Supplier { GameRenderer.getPositionTexLightmapColorShader() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR_NORMAL, Supplier { GameRenderer.getPositionTexColorNormalShader() }),
        )
        //#endif

        @JvmStatic
        private var bufferBuilder: VirtualBufferBuilder? = null

        @JvmStatic
        fun drawTessellator() {
            Tessellator.getInstance().draw()
        }

        @JvmStatic
        fun getFromTessellator(): VirtualBufferBuilder {
            if(bufferBuilder == null || bufferBuilder!!.handle != Tessellator.getInstance().buffer) {
                bufferBuilder = VirtualBufferBuilder(Tessellator.getInstance().buffer)
            }
            return bufferBuilder!!
        }

    }

}