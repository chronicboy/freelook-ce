package btw.community.jeffyjamzhd.freelook.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class GuiButtonConfig extends GuiButton {
    public GuiButtonConfig(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, "");
    }

    public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
        minecraft.getTextureManager().bindTexture(new ResourceLocation("textures/gui/freelook.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        boolean mouseOver = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, mouseOver ? 20 : 0, this.width, this.height);
    }
}
