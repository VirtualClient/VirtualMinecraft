package gg.virtualclient.virtualminecraft.mixin;

import gg.virtualclient.virtualminecraft.VirtualMinecraft;
import gg.virtualclient.virtualminecraft.event.ClientTickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    public void preTick(CallbackInfo callbackInfo) {
        VirtualMinecraft.getEventBus().callEvent(new ClientTickEvent(ClientTickEvent.State.PRE));
    }

    @Inject(at = @At("RETURN"), method = "tick")
    public void postTick(CallbackInfo callbackInfo) {
        VirtualMinecraft.getEventBus().callEvent(new ClientTickEvent(ClientTickEvent.State.POST));
    }

}
