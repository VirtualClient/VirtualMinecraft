package gg.virtualclient.virtualminecraft.event

import gg.virtualclient.virtualevents.event.Event

class ClientTickEvent(val state: State): Event {

    enum class State {
        PRE, POST
    }

}
