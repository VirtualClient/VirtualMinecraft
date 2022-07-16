package gg.virtualclient.virtualminecraft.vertex

import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats

enum class CommonVertexFormats(val mcFormat: VertexFormat) {

    POSITION(VertexFormats.POSITION),
    POSITION_COLOR(VertexFormats.POSITION_COLOR),
    POSITION_TEXTURE(VertexFormats.POSITION_TEXTURE),
    POSITION_TEXTURE_COLOR(VertexFormats.POSITION_TEXTURE_COLOR),
    POSITION_COLOR_TEXTURE_LIGHT(VertexFormats.POSITION_COLOR_TEXTURE_LIGHT),
    POSITION_TEXTURE_LIGHT_COLOR(VertexFormats.POSITION_TEXTURE_LIGHT_COLOR),
    POSITION_TEXTURE_COLOR_LIGHT(VertexFormats.POSITION_TEXTURE_COLOR_LIGHT),
    POSITION_TEXTURE_COLOR_NORMAL(VertexFormats.POSITION_TEXTURE_COLOR_NORMAL);

}