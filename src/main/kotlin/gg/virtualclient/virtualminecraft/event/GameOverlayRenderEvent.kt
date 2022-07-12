package gg.virtualclient.virtualminecraft.event

import gg.virtualclient.virtualevents.event.Event
import gg.virtualclient.virtualminecraft.VirtualMatrixStack

data class GameOverlayRenderEvent(val matrixStack: VirtualMatrixStack, val isDebugMenu: Boolean,
                             val partialTicks: Float, val state: State) : Event {

    enum class State {
        PRE, POST
    }

}