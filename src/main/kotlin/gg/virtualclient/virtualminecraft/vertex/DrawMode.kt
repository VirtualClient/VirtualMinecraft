package gg.virtualclient.virtualminecraft.vertex

import org.lwjgl.opengl.GL11

//#if MC>=11701
import net.minecraft.client.render.VertexFormat
//#endif


enum class DrawMode constructor(
    //#if MC>=11701
    val mcMode: VertexFormat.DrawMode,
    //#endif
    val glMode: Int
) {

    LINES(GL11.GL_LINES),
    LINE_STRIP(GL11.GL_LINE_STRIP),
    TRIANGLES(GL11.GL_TRIANGLES),
    TRIANGLE_STRIP(GL11.GL_TRIANGLE_STRIP),
    TRIANGLE_FAN(GL11.GL_TRIANGLE_FAN),
    QUADS(GL11.GL_QUADS);

    //#if MC>=11701
    constructor(glMode: Int): this(glToMcDrawMode(glMode), glMode)
    //#endif

}

//#if MC>=11701
fun glToMcDrawMode(glMode: Int): VertexFormat.DrawMode {
    return when (glMode) {
        GL11.GL_LINES -> VertexFormat.DrawMode.LINES
        GL11.GL_LINE_STRIP -> VertexFormat.DrawMode.LINE_STRIP
        GL11.GL_TRIANGLES -> VertexFormat.DrawMode.TRIANGLES
        GL11.GL_TRIANGLE_STRIP -> VertexFormat.DrawMode.TRIANGLE_STRIP
        GL11.GL_TRIANGLE_FAN -> VertexFormat.DrawMode.TRIANGLE_FAN
        GL11.GL_QUADS -> VertexFormat.DrawMode.QUADS
        else -> throw IllegalArgumentException("Unsupported draw mode $glMode")
    }
}
//#endif

fun fromGl(glMode: Int): DrawMode? {
    return when (glMode) {
        GL11.GL_LINES -> DrawMode.LINES
        GL11.GL_LINE_STRIP -> DrawMode.LINE_STRIP
        GL11.GL_TRIANGLES -> DrawMode.TRIANGLES
        GL11.GL_TRIANGLE_STRIP -> DrawMode.TRIANGLE_STRIP
        GL11.GL_TRIANGLE_FAN -> DrawMode.TRIANGLE_FAN
        GL11.GL_QUADS -> DrawMode.QUADS
        else -> throw IllegalArgumentException("Unsupported draw mode $glMode")
    }
}