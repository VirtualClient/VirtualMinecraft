package gg.virtualclient.virtualminecraft

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem

//#if MC>=11605
//$$ import org.lwjgl.opengl.GL14
//#endif

object VirtualRenderSystem {

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


}