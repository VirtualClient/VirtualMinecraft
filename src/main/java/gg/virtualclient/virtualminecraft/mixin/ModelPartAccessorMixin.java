package gg.virtualclient.virtualminecraft.mixin;

import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Mixin;

//#if MC<=11605
//$$ import it.unimi.dsi.fastutil.objects.ObjectList;
//$$ import net.minecraft.client.model.ModelPart;
//$$ import org.spongepowered.asm.mixin.gen.Accessor;
//#endif

@Mixin(ModelPart.class)
public interface ModelPartAccessorMixin {

    //#if MC<=11605
    //$$ @Accessor
    //$$ ObjectList<ModelPart.Cuboid> getCuboids();
    //#endif
}
