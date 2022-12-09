package gg.virtualclient.virtualminecraft.util

import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

fun newButton(x: Int, y: Int, width: Int, height: Int, text: Text, pressAction: ButtonWidget.PressAction): ButtonWidget {
    //#if MC>=11903
    return ButtonWidget.Builder(text, pressAction).position(x, y).size(width, height).build()
    //#else
    //$$ return ButtonWidget(x, y, width, height, text, pressAction)
    //#endif
}