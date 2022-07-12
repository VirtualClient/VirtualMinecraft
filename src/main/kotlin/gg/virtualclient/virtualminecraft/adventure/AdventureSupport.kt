package gg.virtualclient.virtualminecraft.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.text.Text

private val serializer =
    //#if MC>=11604
    GsonComponentSerializer.gson()
    //#else
    //$$ GsonComponentSerializer.colorDownsamplingGson()
    //#endif

fun Text.asAdventure(): Component {
    return serializer.serializer().fromJson(Text.Serializer.toJsonTree(this), Component::class.java)
}

fun Component.asMinecraft(): Text {
    //TODO: More performant implementation
    return Text.Serializer.fromJson(serializer.serializer().toJsonTree(this))!!
}