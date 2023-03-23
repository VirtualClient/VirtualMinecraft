package gg.virtualclient.virtualminecraft.model

import net.minecraft.client.model.ModelPart
import net.minecraft.util.math.Direction
import java.util.*

//#if MC<=11605
//$$ import gg.virtualclient.virtualminecraft.mixin.ModelPartAccessorMixin;
//#endif

class ModelPartBuilder {

    private val children: MutableMap<String, ModelPart> = HashMap()
    private val cuboids: MutableList<ModelPart.Cuboid> = ArrayList()

    private var textureWidth = 0f
    private var textureHeight = 0f

    private var mirror = false
    private var textureOffsetU = 0
    private var textureOffsetV = 0

    private var yaw = 0f

    private var pivotZ = 0f
    private var pivotY = 0f
    private var pivotX = 0f
    private var pitch = 0f
    private var roll = 0f


    fun withTextureSize(
        textureWidth: Float,
        textureHeight: Float,
    ) = apply {
        this.textureWidth = textureWidth
        this.textureHeight = textureHeight
    }

    fun withTextureUV(
        textureOffsetU: Int,
        textureOffsetV: Int,
    ) = apply {
        this.textureOffsetU = textureOffsetU
        this.textureOffsetV = textureOffsetV
    }

    fun withMirror(mirror: Boolean) = apply {
        this.mirror = mirror
    }

    fun addBox(
        name: String?, x: Float, y: Float, z: Float, sizeX: Int, sizeY: Int, sizeZ: Int,
        extra: Float, textureOffsetU: Int, textureOffsetV: Int,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU,
                textureOffsetV,
                x,
                y,
                z,
                sizeX.toFloat(),
                sizeY.toFloat(),
                sizeZ.toFloat(),
                extra,
                extra,
                extra,
                mirror,
                textureWidth,
                textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }

    fun addBox(
        x: Float,
        y: Float,
        z: Float,
        sizeX: Float,
        sizeY: Float,
        sizeZ: Float,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU, textureOffsetV, x, y, z, sizeX, sizeY, sizeZ,
                0.0f, 0.0f, 0.0f, mirror, textureWidth, textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }

    fun addBox(
        x: Float,
        y: Float,
        z: Float,
        sizeX: Float,
        sizeY: Float,
        sizeZ: Float,
        mirror: Boolean,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU, textureOffsetV, x, y, z, sizeX, sizeY, sizeZ,
                0.0f, 0.0f, 0.0f, mirror, textureWidth, textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }

    fun addBox(
        x: Float,
        y: Float,
        z: Float,
        sizeX: Float,
        sizeY: Float,
        sizeZ: Float,
        extra: Float,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU, textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, extra,
                extra, extra, mirror, textureWidth, textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }

    fun addBox(
        x: Float,
        y: Float,
        z: Float,
        sizeX: Float,
        sizeY: Float,
        sizeZ: Float,
        extraX: Float,
        extraY: Float,
        extraZ: Float,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU, textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, extraX,
                extraY, extraZ, mirror, textureWidth, textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }

    fun addBox(
        x: Float,
        y: Float,
        z: Float,
        sizeX: Float,
        sizeY: Float,
        sizeZ: Float,
        extra: Float,
        mirror: Boolean,
    ) = apply {
        this.addBox(
            ModelPart.Cuboid(
                textureOffsetU, textureOffsetV, x, y, z, sizeX, sizeY, sizeZ,
                extra, extra, extra, mirror, textureWidth, textureHeight
                //#if MC>=11904
                //$$ ,ALL_DIRECTIONS
                //#endif
            )
        )
    }


    fun addBox(cuboid: ModelPart.Cuboid) = apply {
        cuboids.add(cuboid)
    }

    fun withChild(name: String, part: ModelPart) = apply {
        children[name] = part
    }


    fun withYaw(yaw: Float) = apply {
        this.yaw = yaw
    }

    fun withPitch(pitch: Float) = apply {
        this.pitch = pitch
    }

    fun withRoll(roll: Float) = apply {
        this.roll = roll
    }

    fun withPivot(x: Float, y: Float, z: Float) = apply {
        pivotX = x
        pivotY = y
        pivotZ = z
    }

    fun build(): ModelPart {
        //#if MC>=11701
        val modelPart = ModelPart(cuboids, children)
        //#else
        //$$ ModelPart modelPart = new ModelPart((int) textureWidth, (int) textureHeight, textureOffsetU, textureOffsetV);
        //$$ this.children.forEach((s, modelPart1) -> modelPart.addChild(modelPart1));
        //$$ for (ModelPart.Cuboid cuboid : this.cuboids) {
        //$$     ((ModelPartAccessorMixin) modelPart).getCuboids().add(cuboid);
        //$$ }
        //#endif
        modelPart.yaw = yaw
        modelPart.pitch = pitch
        modelPart.setPivot(pivotX, pivotY, pivotZ)
        modelPart.roll = roll
        return modelPart
    }

    companion object {
        private val ALL_DIRECTIONS: Set<Direction> = EnumSet.allOf(Direction::class.java)
    }

}