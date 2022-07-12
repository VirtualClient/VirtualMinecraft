package gg.virtualclient.virtualminecraft.keyboard

import gg.virtualclient.virtualevents.event.Cancellable
import gg.virtualclient.virtualevents.event.Event

data class KeyEvent constructor(val key: Key, val scanCode: Int, val state: KeyState) : Event, Cancellable {
    override var isCancelled: Boolean = false
}