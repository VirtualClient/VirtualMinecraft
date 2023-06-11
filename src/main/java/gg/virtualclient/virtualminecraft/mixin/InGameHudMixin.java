package gg.virtualclient.virtualminecraft.mixin;


import gg.virtualclient.virtualminecraft.VirtualMatrixStack;
import gg.virtualclient.virtualminecraft.VirtualMinecraft;
import gg.virtualclient.virtualminecraft.event.GameOverlayRenderEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    //#if MC>=12000
    @Inject(method = "render", at = @At("HEAD"))
    public void renderPre(DrawContext context, float tickDelta, CallbackInfo ci) {
        VirtualMinecraft.getEventBus().callEvent(new GameOverlayRenderEvent(new VirtualMatrixStack(context.getMatrices()),
                MinecraftClient.getInstance().options.debugEnabled, tickDelta, GameOverlayRenderEvent.State.PRE));
    }

    @Inject(method = "render", at = @At("RETURN"))
    public void renderPost(DrawContext context, float tickDelta, CallbackInfo ci) {
        VirtualMinecraft.getEventBus().callEvent(new GameOverlayRenderEvent(new VirtualMatrixStack(context.getMatrices()),
                MinecraftClient.getInstance().options.debugEnabled, tickDelta, GameOverlayRenderEvent.State.POST));
    }
    //#else
    //$$ @Inject(method = "render", at = @At("HEAD"))
    //$$ public void renderPre(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
    //$$    VirtualMinecraft.getEventBus().callEvent(new GameOverlayRenderEvent(new VirtualMatrixStack(matrixStack),
    //$$            MinecraftClient.getInstance().options.debugEnabled, tickDelta, GameOverlayRenderEvent.State.PRE));
    //$$ }

    //$$ @Inject(method = "render", at = @At("RETURN"))
    //$$ public void renderPost(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
    //$$    VirtualMinecraft.getEventBus().callEvent(new GameOverlayRenderEvent(new VirtualMatrixStack(matrixStack),
    //$$                MinecraftClient.getInstance().options.debugEnabled, tickDelta, GameOverlayRenderEvent.State.POST));
    //$$ }
    //#endif



}