package gg.virtualclient.virtualminecraft.keyboard

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.fabricmc.fabric.api.util.TriState


fun interface KeyCallback {

    fun onKey(key: Key, scanCode: Int, state: KeyState, cancellationState: TriState): TriState

    companion object {
        @JvmField val EVENT: Event<KeyCallback> = EventFactory.createArrayBacked(KeyCallback::class.java) { callbacks ->
            KeyCallback { key, scanCode, state, cancellationState ->
                var canceledState = cancellationState

                for (callback in callbacks) {
                    val result = callback.onKey(key, scanCode, state, canceledState)

                    if (result != TriState.DEFAULT) {
                        canceledState = result
                    }
                }

                canceledState
            }
        }

    }

}