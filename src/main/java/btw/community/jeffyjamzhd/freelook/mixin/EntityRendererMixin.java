package btw.community.jeffyjamzhd.freelook.mixin;

import btw.community.jeffyjamzhd.freelook.event.CameraEvent;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(value = EntityRenderer.class, priority = 10000)
public abstract class EntityRendererMixin {
    // Update delta and zoom
    @Inject(method = "updateCameraAndRender(F)V", at = @At(value = "INVOKE", shift = At.Shift.AFTER,
            target = "Lnet/minecraft/src/Profiler;startSection(Ljava/lang/String;)V", ordinal = 1))
    private void updateZoom(CallbackInfo ci) {
        CameraEvent.updateDelta();
        CameraEvent.updateZoom();
    }

    @Inject(method = "setupCameraTransform(FI)V", at = @At(value = "TAIL"))
    private void updateLook(float par1, int par2, CallbackInfo ci) {
        CameraEvent.updateFreelook(par1);
    }

    @Inject(method = "updateCameraAndRender(F)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/src/EntityClientPlayerMP;setAngles(FF)V", ordinal = 1))
    private void lockLook(float par1, CallbackInfo ci) {
        if (CameraEvent.flState != 0) {
            // Get player
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;

            // Lock head rotation
            player.rotationYaw = CameraEvent.ogYaw;
            player.rotationPitch = CameraEvent.ogPitch;
        }
    }

    @Inject(method = "Lnet/minecraft/src/EntityRenderer;renderHand(FI)V", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V", shift = At.Shift.AFTER))
    private void rotateViewmodel(float par1, int par2, CallbackInfo ci) {
        CameraEvent.glRotateCam(1, par1);
    }

    @ModifyArgs(method = "updateCameraAndRender(F)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/src/EntityClientPlayerMP;setAngles(FF)V", ordinal = 0))
    private void suppressMouseSmoothCam(Args args) {
        // If freelooking, zero out args
        args.set(0, CameraEvent.flState != 0 ? 0.0f : (float) args.get(0) * (float) CameraEvent.getCurZoom());
        args.set(1, CameraEvent.flState != 0 ? 0.0f : (float) args.get(1) * (float) CameraEvent.getCurZoom());
    }

    @ModifyArgs(method = "updateCameraAndRender(F)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/src/EntityClientPlayerMP;setAngles(FF)V", ordinal = 1))
    private void suppressMouse(Args args) {
        // If freelooking, zero out args
        args.set(0, CameraEvent.flState != 0 ? 0.0f : (float) args.get(0) * (float) CameraEvent.getCurZoom());
        args.set(1, CameraEvent.flState != 0 ? 0.0f : (float) args.get(1) * (float) CameraEvent.getCurZoom());
    }

    @ModifyReturnValue(method = "getFOVModifier(FZ)F", at = @At("RETURN"))
    private float setZoomLevel(float fov) {
        // Set zoom on FOV
        return fov * (float) CameraEvent.getCurZoom();
    }
}
