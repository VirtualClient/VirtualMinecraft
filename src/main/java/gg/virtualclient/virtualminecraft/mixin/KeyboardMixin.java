package gg.virtualclient.virtualminecraft.mixin;

import gg.virtualclient.virtualminecraft.DebugKey;
import gg.virtualclient.virtualminecraft.MinecraftDebugger;
import gg.virtualclient.virtualminecraft.VirtualMinecraft;
import gg.virtualclient.virtualminecraft.adventure.AdventureSupportKt;
import gg.virtualclient.virtualminecraft.keyboard.Key;
import gg.virtualclient.virtualminecraft.keyboard.KeyEvent;
import gg.virtualclient.virtualminecraft.keyboard.KeyState;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow
    private long debugCrashStartTime;

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void handleKey(long window, int key, int scancode, int action, int j, CallbackInfo info) {
        KeyState keyState = KeyState.fromGLFW(action);
        if(keyState == null)
            return;
        KeyEvent keyEvent = new KeyEvent(Key.ofKeyCode(key), scancode, keyState);
        VirtualMinecraft.getEventBus().callEvent(keyEvent);
        if(keyEvent.isCancelled()) {
            info.cancel();
        }
        VirtualMinecraft.getEventBus().callEvent(keyEvent);
        if(keyEvent.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method = "processF3", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;getChatHud()Lnet/minecraft/client/gui/hud/ChatHud;"))
    public void onF3(int key, CallbackInfoReturnable<Boolean> cir) {
        ChatHud chatHud = MinecraftClient.getInstance().inGameHud.getChatHud();
        for (DebugKey debugKey : MinecraftDebugger.getCustomDebugKeyList()) {
            chatHud.addMessage(AdventureSupportKt.asMinecraft(debugKey.getDescription()));
        }
    }

    @Inject(method = "processF3", at = @At(value = "HEAD"), cancellable = true)
    public void onF3Start(int key, CallbackInfoReturnable<Boolean> cir) {
        if (this.debugCrashStartTime > 0L &&
            this.debugCrashStartTime < Util.getMeasuringTimeMs() - 100L) {
            return;
        }
        for (DebugKey debugKey : MinecraftDebugger.getCustomDebugKeyList()) {
            if(key == debugKey.getKeyCode()) {
                cir.setReturnValue(debugKey.getCallback().getAsBoolean());
                break;
            }
        }
    }

}
