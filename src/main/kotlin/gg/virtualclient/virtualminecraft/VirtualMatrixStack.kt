package gg.virtualclient.virtualminecraft

import java.util.*

import org.joml.Matrix4f
import org.joml.Matrix3f
import org.joml.Quaternionf
import org.joml.Vector3f

//#if MC>=11700
import com.mojang.blaze3d.systems.RenderSystem
//#endif

//#if MC>=11605
import net.minecraft.client.util.math.MatrixStack
//#endif

//#if MC>=11605
import net.minecraft.util.math.MathHelper
//#endif

//#if MC<11700
//$$ import net.minecraft.client.util.GlAllocationUtils
//$$ import java.nio.Buffer
//$$ import java.nio.FloatBuffer
//$$ import org.lwjgl.opengl.GL11
//#endif


/**
 * A stack of matrices which can be manipulated via common transformations, just like MC's MatrixStack.
 *
 * For MC versions 1.16 and above, methods exist to convert from (via the constructor) and to (via Entry.toMCStack) the
 * vanilla stack type if required.
 * For MC versions below 1.17, the *GlobalState methods can be used to transfer the state of this matrix stack into the
 * global GL state. For 1.17, they transfer state into Mojang's global MatrixStack in RenderSystem.
 *
 * @author UniversalCraft contributors
 */
class VirtualMatrixStack private constructor(
    private val stack: Deque<Entry>
) {

    constructor() : this(ArrayDeque<Entry>().apply {
        add(Entry(
            Matrix4f().apply { identity() },
            Matrix3f().apply { identity() }
        ))
    })

    //#if MC>=11600
    constructor(mc: MatrixStack) : this(mc.peek())
    constructor(mc: MatrixStack.Entry) : this(ArrayDeque<Entry>().apply {
        add(Entry(
            //#if MC>=11802
            mc.positionMatrix, mc.normalMatrix
            //#else
            //$$ mc.model, mc.normal
            //#endif
        ))
    })
    fun toMC() = peek().toMCStack()
    //#endif

    fun translate(x: Double, y: Double, z: Double) = translate(x.toFloat(), y.toFloat(), z.toFloat())

    fun translate(x: Float, y: Float, z: Float) {
        if (x == 0f && y == 0f && z == 0f) return
        stack.last.run {
            //#if MC>=11903
            model.translate(x, y, z)
            //#elseif MC>=11400
            //$$ model.multiply(Matrix4f.translate(x, y, z))
            //#else
            //$$ Matrix4f.translate(Vector3f(x, y, z), model, model)
            //#endif
        }
    }

    fun scale(x: Double, y: Double, z: Double) = scale(x.toFloat(), y.toFloat(), z.toFloat())

    fun scale(x: Float, y: Float, z: Float) {
        if (x == 1f && y == 1f && z == 1f) return
        return stack.last.run {
            //#if MC>=11903
            model.scale(x, y, z)
            //#elseif MC>=11400
            //$$ model.multiply(Matrix4f.scale(x, y, z))
            //#else
            //$$ Matrix4f.scale(Vector3f(x, y, z), model, model)
            //#endif
            if (x == y && y == z) {
                if (x < 0f) {
                    //#if MC>=11903
                    normal.scale(-1f)
                    //#elseif MC>=11400
                    //$$ normal.multiply(-1f)
                    //#else
                    //$$ Matrix3f.negate(normal, normal)
                    //#endif
                }
            } else {
                val ix = 1f / x
                val iy = 1f / y
                val iz = 1f / z
                //#if MC>=11400
                val rt = MathHelper.fastInverseCbrt(ix * iy * iz)
                //#else
                //$$ val rt = Math.cbrt((ix * iy * iz).toDouble()).toFloat()
                //#endif

                //#if MC>=11903
                normal.scale(rt * ix, rt * iy, rt * iz)
                //#elseif MC>=11400
                //$$ normal.multiply(Matrix3f.scale(rt * ix, rt * iy, rt * iz))
                //#else
                //$$ val scale = Matrix3f()
                //$$ scale.m00 = rt * ix
                //$$ scale.m11 = rt * iy
                //$$ scale.m22 = rt * iz
                //$$ Matrix3f.mul(normal, scale, normal)
                //#endif
            }
        }
    }

    @JvmOverloads
    fun rotate(angle: Float, x: Float, y: Float, z: Float, degrees: Boolean = true) {
        if (angle == 0f) return
        stack.last.run {

            //#if MC>=11903
            val angleRadians = if (degrees) Math.toRadians(angle.toDouble()).toFloat() else angle
            multiply(Quaternionf().rotateAxis(angleRadians, x, y, z));
            //#elseif MC>=11400
            //$$ multiply(Quaternion(Vec3f(x, y, z), angle, degrees));
            //#else
            //$$ val angleRadians = if (degrees) Math.toRadians(angle.toDouble()).toFloat() else angle
            //$$ val axis = Vector3f(x, y, z)
            //$$ Matrix4f.rotate(angleRadians, axis, model, model)
            //$$
            //$$ // There appears to be no method to rotate a Matrix3f, so we'll have to do it manually
            //$$ fun makeRotationMatrix(angle: Float, axis: Vector3f) = Matrix3f().apply {
            //$$     val c = cos(angle)
            //$$     val s = sin(angle)
            //$$     val oneMinusC = 1 - c
            //$$     val xx = axis.x * axis.x
            //$$     val xy = axis.x * axis.y
            //$$     val xz = axis.x * axis.z
            //$$     val yy = axis.y * axis.y
            //$$     val yz = axis.y * axis.z
            //$$     val zz = axis.z * axis.z
            //$$     val xs = axis.x * s
            //$$     val ys = axis.y * s
            //$$     val zs = axis.z * s
            //$$
            //$$     m00 = xx * oneMinusC + c
            //$$     m01 = xy * oneMinusC + zs
            //$$     m02 = xz * oneMinusC - ys
            //$$     m10 = xy * oneMinusC - zs
            //$$     m11 = yy * oneMinusC + c
            //$$     m12 = yz * oneMinusC + xs
            //$$     m20 = xz * oneMinusC + ys
            //$$     m21 = yz * oneMinusC - xs
            //$$     m22 = zz * oneMinusC + c
            //$$ }
            //$$ Matrix3f.mul(normal, makeRotationMatrix(angleRadians, axis), normal)
            //#endif
        }
    }

    fun multiply(quaternion: Quaternionf): Unit = stack.last.run {
        //#if MC>=11903
        model.rotate(quaternion)
        normal.rotate(quaternion)
        //#elseif MC>=11400
        //$$ model.multiply(quaternion)
        //$$ normal.multiply(quaternion)
        //#else
        //$$ TODO("lwjgl quaternion multiply") // there seems to be no existing methods to do this
        //#endif
    }

    fun fork() = VirtualMatrixStack(ArrayDeque<Entry>().apply {
        add(stack.last.deepCopy())
    })

    fun push(): Unit = stack.addLast(stack.last.deepCopy())

    fun pop() {
        stack.removeLast()
    }

    fun peek(): Entry = stack.last

    fun isEmpty(): Boolean = stack.size == 1

    fun applyToGlobalState() {
        //#if MC>=11700
        // FIXME preprocessor bug: should remap the intermediary name to yarn no problem
        RenderSystem.getModelViewStack().multiplyPositionMatrix(stack.last.model)
        //#else
        //$$ stack.last.model.writeRowFirst(MATRIX_BUFFER)
        //$$ // Explicit cast to Buffer required so we do not use the JDK9+ override in FloatBuffer
        //$$ (MATRIX_BUFFER as Buffer).rewind()
        //#if MC>=11500
        //$$ GL11.glMultMatrixf(MATRIX_BUFFER)
        //#else
        //$$ GL11.glMultMatrix(MATRIX_BUFFER)
        //#endif
        //#endif
    }

    fun replaceGlobalState() {
        //#if MC>=11700
        RenderSystem.getModelViewStack().loadIdentity()
        //#else
        //$$ GL11.glLoadIdentity()
        //#endif
        applyToGlobalState()
    }

    fun runWithGlobalState(block: Runnable) = runWithGlobalState { block.run() }

    fun <R> runWithGlobalState(block: () -> R): R  = withGlobalStackPushed {
        applyToGlobalState()
        block()
    }

    fun runReplacingGlobalState(block: Runnable) = runReplacingGlobalState { block.run() }

    fun <R> runReplacingGlobalState(block: () -> R): R = withGlobalStackPushed {
        replaceGlobalState()
        block()
    }

    private inline fun <R> withGlobalStackPushed(block: () -> R) : R {
        //#if MC>=11700
        val stack = RenderSystem.getModelViewStack()
        stack.push()
        RenderSystem.applyModelViewMatrix()
        //#else
        //$$ GL11.glPushMatrix()
        //#endif
        return block().also {
            //#if MC>=11700
            stack.pop()
            RenderSystem.applyModelViewMatrix()
            //#else
            //$$ GL11.glPopMatrix()
            //#endif
        }
    }

    data class Entry(val model: Matrix4f, val normal: Matrix3f) {
        //#if MC>=11600
        fun toMCStack() = MatrixStack().also {
            //#if MC>=11802
            it.peek().positionMatrix.mul(model)
            it.peek().normalMatrix.mul(normal)
            //#else
            //$$ it.peek().model.multiply(model)
            //$$ it.peek().normal.multiply(normal)
            //#endif
        }
        //#endif

        fun deepCopy() =
            //#if MC>=11903
            Entry(Matrix4f(model), Matrix3f(normal))
            //#elseif MC>=11400
            //$$ Entry(model.copy(), normal.copy())
            //#else
            //$$ Entry(Matrix4f.load(model, null), Matrix3f.load(normal, null))
            //#endif
    }

    companion object {
        //#if MC<11700
        //$$ private val MATRIX_BUFFER: FloatBuffer = GlAllocationUtils.allocateFloatBuffer(16)
        //#endif
    }
}