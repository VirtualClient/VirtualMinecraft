package gg.virtualclient.virtualminecraft.util

import org.joml.Quaternionf

//#if MC<11903
//$$ import net.minecraft.util.math.Vec3f
//#endif

fun newQuaternion(x: Float, y: Float, z: Float, angle: Float, degrees: Boolean): Quaternionf {
    //#if MC>=11903
    val angleRadians = if (degrees) Math.toRadians(angle.toDouble()).toFloat() else angle
    return Quaternionf().rotateAxis(angleRadians, x, y, z)
    //#else
    //$$ return Quaternion(Vec3f(x, y, z), angle, degrees)
    //#endif
}