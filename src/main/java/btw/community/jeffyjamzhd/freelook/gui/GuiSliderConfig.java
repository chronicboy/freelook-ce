package btw.community.jeffyjamzhd.freelook.gui;

import btw.community.jeffyjamzhd.freelook.FreeLookAddon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class GuiSliderConfig extends GuiButton {
    /*
        @todo
        - Add visual markers for notch points
     */
    public float sliderValue = 1.0F;
    public boolean dragging = false;
    private String id;
    private int notches;

    public GuiSliderConfig(int i, int j, int k, String id, String string, float value, int notches) {
        super(i, j, k, 150, 20, string);
        this.id = id;
        this.sliderValue = value;
        this.notches = notches;
    }

    protected int getHoverState(boolean bl) { return 0; }

    protected void mouseDragged(Minecraft minecraft, int i, int j) {
        if (this.drawButton) {
            if (this.dragging) {
                int slideFac = 4 + 8 * (notches / 16);
                this.sliderValue = (float) (i - (this.xPosition + 4)) / (float) (this.width - 8);
                this.sliderValue = this.notches > 0
                        ? roundToNotch(this.sliderValue)
                        : this.sliderValue;
                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }

                FreeLookAddon fl = FreeLookAddon.getInstance();
                fl.setSliderConfig(this.id, this.sliderValue);
                this.displayString = fl.getSliderDisplay(this.id);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        if (super.mousePressed(minecraft, i, j)) {
            this.sliderValue = (float)(i - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            FreeLookAddon fl = FreeLookAddon.getInstance();
            fl.setSliderConfig(this.id, this.sliderValue);
            this.displayString = fl.getSliderDisplay(this.id);
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    public void mouseReleased(int i, int j) {
        this.dragging = false;
    }

    // I never said I was a good programmer :p
    private float roundToNotch(float value) {
        float fac = 1.0f / notches;
        float facMargin = fac / 2;
        for (int i = 0; i < notches; i++) {
            if (value < (fac * i) + facMargin) return fac * i;
            else if (value < fac * (i + 1)) return fac * (i + 1);
        }
        return 1.0f;
    }
}
