package gg.virtualclient.virtualminecraft.vertex

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.render.*
import java.util.function.Supplier

//#if MC>=11903
import net.minecraft.client.gl.ShaderProgram
//#endif

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

    fun beginWithActiveShader(mode: DrawMode, format: CommonVertexFormats): VirtualBufferBuilder {
        return beginWithActiveShader(mode, format.mcFormat)
    }

    fun beginWithDefaultShader(mode: DrawMode, format: CommonVertexFormats): VirtualBufferBuilder {
        return beginWithDefaultShader(mode, format.mcFormat)
    }

    companion object {
        //#if MC>=11700
        @JvmStatic
        private val defaultShaders: MutableMap<VertexFormat, Supplier<ShaderProgram?>> = mutableMapOf(
            Pair(VertexFormats.LINES, Supplier { GameRenderer.getRenderTypeLinesProgram() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR_LIGHT, Supplier { GameRenderer.getParticleProgram() }),
            Pair(VertexFormats.POSITION, Supplier { GameRenderer.getPositionProgram() }),
            Pair(VertexFormats.POSITION_COLOR, Supplier { GameRenderer.getPositionColorProgram() }),
            Pair(VertexFormats.POSITION_COLOR_LIGHT, Supplier { GameRenderer.getPositionColorLightmapProgram() }),
            Pair(VertexFormats.POSITION_TEXTURE, Supplier { GameRenderer.getPositionTexProgram() }),
            Pair(VertexFormats.POSITION_COLOR_TEXTURE, Supplier { GameRenderer.getPositionColorTexProgram() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR, Supplier { GameRenderer.getPositionTexColorProgram() }),
            Pair(VertexFormats.POSITION_COLOR_TEXTURE_LIGHT, Supplier { GameRenderer.getPositionColorTexLightmapProgram() }),
            Pair(VertexFormats.POSITION_TEXTURE_LIGHT_COLOR, Supplier { GameRenderer.getPositionTexLightmapColorProgram() }),
            Pair(VertexFormats.POSITION_TEXTURE_COLOR_NORMAL, Supplier { GameRenderer.getPositionTexColorNormalProgram() }),
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