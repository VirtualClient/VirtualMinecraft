package gg.virtualclient.virtualminecraft.mixin;

import gg.virtualclient.virtualminecraft.VirtualMinecraft;
import gg.virtualclient.virtualminecraft.event.ClientTickEvent;
import gg.virtualclient.virtualminecraft.event.MinecraftWindowResizedEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    @Final
    private Window window;

    @Inject(at = @At("HEAD"), method = "tick")
    public void preTick(CallbackInfo callbackInfo) {
        VirtualMinecraft.getEventBus().callEvent(new ClientTickEvent(ClientTickEvent.State.PRE));
    }

    @Inject(at = @At("RETURN"), method = "tick")
    public void postTick(CallbackInfo callbackInfo) {
        VirtualMinecraft.getEventBus().callEvent(new ClientTickEvent(ClientTickEvent.State.POST));
    }

    @Inject(at = @At("HEAD"), method = "onResolutionChanged")
    public void onResolutionChanged(CallbackInfo callbackInfo) {
        VirtualMinecraft.getEventBus().callEvent(new MinecraftWindowResizedEvent(window.getScaledWidth(),
                window.getScaledHeight(), window.getFramebufferWidth(), window.getFramebufferHeight()));
    }
}
