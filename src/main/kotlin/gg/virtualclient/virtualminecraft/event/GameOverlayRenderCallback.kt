package gg.virtualclient.virtualminecraft.event

import gg.virtualclient.virtualminecraft.VirtualMatrixStack
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface GameOverlayRenderCallback {

    fun render(matrixStack: VirtualMatrixStack, isDebugMenu: Boolean,
               partialTicks: Float, state: State)

    enum class State {
        PRE, POST
    }

    companion object {

        @JvmField val EVENT: Event<GameOverlayRenderCallback> = EventFactory.createArrayBacked(GameOverlayRenderCallback::class.java) { callbacks ->
            GameOverlayRenderCallback { stack, debug, ticks, state -> callbacks.forEach { it.render(stack, debug, ticks, state) } }
        }

    }

}