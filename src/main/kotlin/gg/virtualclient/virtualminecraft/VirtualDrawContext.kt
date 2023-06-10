package gg.virtualclient.virtualminecraft

//#if MC>=12000
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext;

fun newDrawContext(matrixStack: VirtualMatrixStack): DrawContext {
    val context = DrawContext(MinecraftClient.getInstance(), MinecraftClient.getInstance().bufferBuilders.entityVertexConsumers)
    context.applyMatrixStack(matrixStack)
    return context
}

fun DrawContext.applyMatrixStack(matrixStack: VirtualMatrixStack) {
    val mc = matrices.peek()
    val other = matrixStack.peek()

    mc.positionMatrix.set(other.model)
    mc.normalMatrix.set(other.normal)
}
//#endif