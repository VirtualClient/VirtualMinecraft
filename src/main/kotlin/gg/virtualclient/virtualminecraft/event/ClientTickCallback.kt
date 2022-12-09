package gg.virtualclient.virtualminecraft.event

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface ClientTickCallback {

    fun tick(state: State)

    enum class State {
        PRE, POST
    }

    companion object {

        @JvmField val EVENT: Event<ClientTickCallback> = EventFactory.createArrayBacked(ClientTickCallback::class.java) { callbacks ->
            ClientTickCallback { state -> callbacks.forEach { it.tick(state) } }
        }

    }

}
