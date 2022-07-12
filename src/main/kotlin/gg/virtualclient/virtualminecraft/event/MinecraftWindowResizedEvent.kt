package gg.virtualclient.virtualminecraft.event

import gg.virtualclient.virtualevents.event.Event

data class MinecraftWindowResizedEvent(val scaledWidth: Int, val scaledHeight: Int,
                                       val frameBufferWidth: Int, val frameBufferHeight: Int) : Event