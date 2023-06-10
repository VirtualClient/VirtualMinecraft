package gg.virtualclient.virtualminecraft.util

import org.joml.Quaternionf
import org.joml.Vector3f

//#if MC<11903
//$$ import net.minecraft.util.math.Matrix4f
//#endif

object AxisVectors {
    //TODO: On older versions use the already existing constants

    @JvmField val POSITIVE_X = Vector3f(1f, 0f, 0f)
    @JvmField val POSITIVE_Y = Vector3f(0f, 1f, 0f)
    @JvmField val POSITIVE_Z = Vector3f(0f, 0f, 1f)

    @JvmField val NEGATIVE_X = Vector3f(-1f, 0f, 0f)
    @JvmField val NEGATIVE_Y = Vector3f(0f, -1f, 0f)
    @JvmField val NEGATIVE_Z = Vector3f(0f, 0f, -1f)
}

fun Vector3f.quaternionFromDegrees(degrees: Float): Quaternionf {
    return newQuaternion(this, degrees, true)
}

fun Vector3f.quaternionFromRadians(radians: Float): Quaternionf {
    return newQuaternion(this, radians, false)
}

//#if MC<11903
//$$ fun Matrix4f.scale(x: Float, y: Float, z: Float) {
//$$     multiply(Matrix4f.scale(x, y, z))
//$$ }
//#endif

fun newQuaternion(x: Float, y: Float, z: Float, angle: Float, degrees: Boolean): Quaternionf {
    //#if MC>=11903
    val angleRadians = if (degrees) Math.toRadians(angle.toDouble()).toFloat() else angle
    return Quaternionf().rotateAxis(angleRadians, x, y, z)
    //#else
    //$$ return Quaternion(Vec3f(x, y, z), angle, degrees)
    //#endif
}

fun newQuaternion(vec: Vector3f, angle: Float, degrees: Boolean): Quaternionf {
    return newQuaternion(vec.x, vec.y, vec.z, angle, degrees)
}

fun newQuaternion(angleX: Float, angleY: Float, angleZ: Float, degrees: Boolean): Quaternionf {
    //#if MC>=11903
    val angleRadiansX = if (degrees) Math.toRadians(angleX.toDouble()).toFloat() else angleX
    val angleRadiansY = if (degrees) Math.toRadians(angleY.toDouble()).toFloat() else angleY
    val angleRadiansZ = if (degrees) Math.toRadians(angleZ.toDouble()).toFloat() else angleZ

    return Quaternionf().rotationXYZ(angleRadiansX, angleRadiansY, angleRadiansZ)
    //#else
    //$$ return Quaternion(angleX, angleY, angleZ, degrees)
    //#endif
}