package btw.community.jeffyjamzhd.freelook.mixin;

import btw.community.jeffyjamzhd.freelook.gui.GuiButtonConfig;
import btw.community.jeffyjamzhd.freelook.gui.GuiFreelookConfig;
import net.minecraft.src.GameSettings;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiOptions;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiOptions.class)
public abstract class GuiOptionsMixin extends GuiScreen {
    @Shadow @Final private GameSettings options;
    private static GuiButtonConfig configButton;

    // Shrink options button
    @ModifyConstant(method = "initGui()V", constant = @Constant(intValue = 150, ordinal = 1))
    private int shrinkWidth(int i) {
        return i - 24;
    }

    // Add settings button
    @Inject(method = "initGui()V", at = @At("TAIL"))
    private void addButton(CallbackInfo ci) {
        configButton = new GuiButtonConfig(301, this.width / 2 + 132, this.height / 6 + 96 - 6, 20, 20, "");
        this.buttonList.add(configButton);
    }

    // Listen for settings button input
    @Inject(method = "actionPerformed", at = @At("TAIL"))
    protected void actionPerformed(GuiButton gButton, CallbackInfo ci) {
        if (gButton.enabled) {
            if (gButton.id == 301) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiFreelookConfig(this, this.options));
            }
        }
    }

}
