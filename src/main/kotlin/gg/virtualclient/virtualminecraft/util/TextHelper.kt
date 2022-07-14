package gg.virtualclient.virtualminecraft.util

import net.minecraft.text.Text

//#if MC<=11900
//$$ import net.minecraft.text.LiteralText;
//$$ import net.minecraft.text.TranslatableText;
//#endif

object TextHelper {

    @JvmStatic
    fun translatable(str: String?): Text {
        //#if MC>=11900
        return Text.translatable(str)
        //#else
        //$$ return TranslatableText(str)
        //#endif
    }

    @JvmStatic
    fun translatable(str: String?, vararg objects: Any?): Text {
        //#if MC>=11900
        return Text.translatable(str, objects)
        //#else
        //$$ return TranslatableText(str, objects)
        //#endif
    }

    @JvmStatic
    fun text(str: String?): Text {
        //#if MC>=11900
        return Text.of(str)
        //#else
        //$$ return LiteralText(str)
        //#endif
    }

    @JvmStatic
    fun empty(): Text {
        //#if MC>=11900
        return Text.empty()
        //#else
        //$$ return LiteralText.EMPTY
        //#endif
    }
}
