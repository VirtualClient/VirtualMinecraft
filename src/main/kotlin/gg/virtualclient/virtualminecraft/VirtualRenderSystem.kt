package gg.virtualclient.virtualminecraft

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.texture.AbstractTexture
import net.minecraft.client.texture.ResourceTexture
import net.minecraft.util.Identifier
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13

//#if MC>=11605
import org.lwjgl.opengl.GL14
//#endif

object VirtualRenderSystem {

    @JvmStatic
    fun configureTexture(glTextureId: Int, block: Runnable) {
        val prevTextureBinding = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D)
        GlStateManager._bindTexture(glTextureId)
        block.run()
        GlStateManager._bindTexture(prevTextureBinding)
    }

    @JvmStatic
    fun bindTexture(index: Int, glTextureId: Int) {
        //#if MC>=11701
        RenderSystem.setShaderTexture(index, glTextureId);
        //#else
        //$$ configureTextureUnit(index) { GlStateManager.bindTexture(glTextureId) }
        //#endif
    }

    @JvmStatic
    fun bindTexture(index: Int, identifier: Identifier) {
        val texture: AbstractTexture = MinecraftClient.getInstance().textureManager.getTexture(identifier) ?: registerTexture(identifier)
        bindTexture(index, texture.glId)
    }

    private fun registerTexture(identifier: Identifier): AbstractTexture {
        val texture = ResourceTexture(identifier)
        MinecraftClient.getInstance().textureManager.registerTexture(identifier, texture)
        return texture
    }

    @JvmStatic
    fun configureTextureUnit(index: Int, block: Runnable) {
        val prevActiveTexture: Int = getActiveTexture()
        setActiveTexture(GL13.GL_TEXTURE0 + index)
        block.run()
        setActiveTexture(prevActiveTexture)
    }

    @JvmStatic
    fun getActiveTexture(): Int {
        return GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE)
    }

    @JvmStatic
    fun color(red: Float, green: Float, blue: Float, alpha: Float) {
        //#if MC>=11701
        RenderSystem.setShaderColor(red, green, blue, alpha)
        //#else
        //$$ RenderSystem.color4f(red, green, blue, alpha)
        //#endif
    }

    @JvmStatic
    fun setActiveTexture(glId: Int) {
        //#if MC>=11701
        GlStateManager._activeTexture(glId);
        //#elseif MC>=11400
        //$$ GlStateManager.activeTexture(glId);
        //#else
        //$$ GlStateManager.setActiveTexture(glId)
        //#endif
    }

    @JvmStatic
    fun enableAlpha() {
        //#if MC<11700
        //#if MC>=11502
        //$$ RenderSystem.enableAlphaTest();
        //#else
        //$$ GlStateManager.enableAlpha();
        //#endif
        //#endif
    }

    @JvmStatic
    fun disableAlpha() {
        //#if MC<11700
        //#if MC>=11502
        //$$ RenderSystem.disableAlphaTest();
        //#else
        //$$ GlStateManager.disableAlpha();
        //#endif
        //#endif
    }

    @JvmStatic
    fun enableDepth() {
        //#if MC>=11605
        RenderSystem.enableDepthTest();
        //#else
        //$$ GlStateManager.enableDepth()
        //#endif
    }


    @JvmStatic
    fun depthFunc(mode: Int) {
        GlStateManager._depthFunc(mode)
    }

    @JvmStatic
    fun depthMask(flag: Boolean) {
        GlStateManager._depthMask(flag)
    }

    @JvmStatic
    fun disableDepth() {
        //#if MC>=11502
        RenderSystem.disableDepthTest();
        //#else
        //$$ GlStateManager.disableDepth()
        //#endif
    }

    @JvmStatic
    fun shadeModel(mode: Int) {
        //#if MC<11700
        //$$ GlStateManager.shadeModel(mode)
        //#endif
    }

    @JvmStatic
    fun deleteTexture(glTextureId: Int) {
        GlStateManager._deleteTexture(glTextureId)
    }

    @JvmStatic
    fun enableBlend() {
        RenderSystem.enableBlend()
    }

    @JvmStatic
    fun disableBlend() {
        RenderSystem.disableBlend()
    }

    @JvmStatic
    fun tryBlendFuncSeparate(srcFactor: Int, dstFactor: Int, srcFactorAlpha: Int, dstFactorAlpha: Int) {
        //#if MC>=11500
        RenderSystem.blendFuncSeparate(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
        //#else
        //$$ GlStateManager.tryBlendFuncSeparate(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha)
        //#endif
    }

    @JvmStatic
    fun blendEquation(equation: Int) {
        //#if MC>=11500
        RenderSystem.blendEquation(equation);
        //#else
        //$$ GL14.glBlendEquation(equation)
        //#endif
    }

    @JvmStatic
    fun glCreateProgram(): Int {
        //#if MC>=11502
        return GlStateManager.glCreateProgram();
        //#else
        //$$ return OpenGlHelper.glCreateProgram()
        //#endif
    }

    @JvmStatic
    fun glCreateShader(type: Int): Int {
        //#if MC>=11502
        return GlStateManager.glCreateShader(type);
        //#else
        //$$ return OpenGlHelper.glCreateShader(type)
        //#endif
    }

    @JvmStatic
    fun isOpenGl21Supported(): Boolean {
        //#if MC>=11502
        return true;
        //#else
        //$$ return OpenGlHelper.openGL21
        //#endif
    }

    @JvmStatic
    fun glCompileShader(shaderIn: Int) {
        //#if MC>=11502
        GlStateManager.glCompileShader(shaderIn);
        //#else
        //$$ OpenGlHelper.glCompileShader(shaderIn)
        //#endif
    }

    fun glUseProgram(program: Int) {
        //#if MC>=11502
        GlStateManager._glUseProgram(program)
        //#else
        //$$ OpenGlHelper.glUseProgram(program);
        //#endif
    }

    fun glGetShaderi(shaderIn: Int, pname: Int): Int {
        //#if MC>=11502
        return GlStateManager.glGetShaderi(shaderIn, pname)
        //#else
        //$$ return OpenGlHelper.glGetShaderi(shaderIn, pname);
        //#endif
    }

    fun glGetShaderInfoLog(shader: Int, maxLen: Int): String? {
        //#if MC>=11502
        return GlStateManager.glGetShaderInfoLog(shader, maxLen)
        //#else
        //$$ return OpenGlHelper.glGetShaderInfoLog(shader, maxLen);
        //#endif
    }

    fun glAttachShader(program: Int, shaderIn: Int) {
        //#if MC>=11502
        GlStateManager.glAttachShader(program, shaderIn)
        //#else
        //$$ OpenGlHelper.glAttachShader(program, shaderIn);
        //#endif
    }

    fun glLinkProgram(program: Int) {
        //#if MC>=11502
        GlStateManager.glLinkProgram(program)
        //#else
        //$$ OpenGlHelper.glLinkProgram(program);
        //#endif
    }

    fun glGetProgrami(program: Int, pname: Int): Int {
        //#if MC>=11502
        return GlStateManager.glGetProgrami(program, pname)
        //#else
        //$$ return OpenGlHelper.glGetProgrami(program, pname);
        //#endif
    }

    fun glGetProgramInfoLog(program: Int, maxLen: Int): String? {
        //#if MC>=11502
        return GlStateManager.glGetProgramInfoLog(program, maxLen)
        //#else
        //$$ return OpenGlHelper.glGetProgramInfoLog(program, maxLen);
        //#endif
    }


    fun enableTexture2D() {
        //#if MC>=11502
        RenderSystem.enableTexture()
        //#else
        //$$ GlStateManager.enableTexture2D()
        //#endif
    }

    fun disableTexture2D() {
        //#if MC>=11502
        RenderSystem.disableTexture();
        //#else
        //$$ GlStateManager.disableTexture2D()
        //#endif
    }
}