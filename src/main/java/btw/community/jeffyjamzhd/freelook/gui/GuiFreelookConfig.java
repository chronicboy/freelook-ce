package btw.community.jeffyjamzhd.freelook.gui;

import btw.community.jeffyjamzhd.freelook.FreeLookAddon;
import net.minecraft.src.*;

public class GuiFreelookConfig extends GuiScreen {
    /*
        @todo
        - Move config handlers to FreelookAddon
        - Complete rebind parity with controls screen
        ( there is currently missing functionality )
        - Clean up this code
     */

    private final GuiScreen parentScreen;
    private static EnumConfig[] enumConfigs;
    protected String screenTitle = "Freelook Options";
    private int buttonId = -1;
    private GuiSliderConfig flAngleSlider;

    public GuiFreelookConfig(GuiScreen gScreen, GameSettings gSettings) {
        this.parentScreen = gScreen;
    }

    private int getLeftBorder() {
        return this.width / 2 - 152;
    }

    public void initGui() {
        // Set title
        this.screenTitle = StatCollector.translateToLocal("freelook.options.title");

        // Add bind buttons
        int lb = this.getLeftBorder();
        for (int i = 0; i < FreeLookAddon.modBinds.length; i++) {
            this.buttonList.add(new GuiSmallButton(i, lb + i % 2 * 154 + 80, this.height / 6, 70, 20,
                    GameSettings.getKeyDisplayString(FreeLookAddon.modBinds[i].keyCode)));
        }

        // Add buttons
        int count = 0;
        for (EnumConfig item : enumConfigs) {
            // Get info
            int ordinal = item.returnEnumOrdinal();
            String name = item.getEnumName();

            // Add buttons
            if (item.getEnumBoolean()) {
                // Is boolean button
                this.buttonList.add(new GuiButton(100 + ordinal, this.width / 2 + 2,
                        this.height / 6 + 24 + (24 * count), 150, 20,
                        boolToString(ordinal, getBoolValue(ordinal))));
            } else if (item.getEnumInt()) {
                // Is int button
                this.buttonList.add(new GuiButton(100 + ordinal, this.width / 2 + 2,
                        this.height / 6 + 24 + (24 * count), 150, 20,
                        intToString(ordinal, getIntValue(ordinal))));
            } else {
                // Is float button
                GuiSliderConfig slider = new GuiSliderConfig(200 + ordinal, this.width / 2 + 2,
                        this.height / 6 + 24 + (24 * count), item.getEnumProperty(),
                        FreeLookAddon.getInstance().getSliderDisplay(item.getEnumProperty()),
                        getFloatValue(ordinal), item.getEnumFloatNotches());
                this.buttonList.add(slider);
                if (ordinal == EnumConfig.FREELOOK_RANGE.returnEnumOrdinal())
                    flAngleSlider = slider;
            }
            count++;
        }
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, StatCollector.translateToLocal("gui.done")));
    }

    private boolean getBoolValue(int ordinal) {
        if (ordinal == EnumConfig.DISABLE_F5.returnEnumOrdinal())
            return FreeLookAddon.enableF5;
        if (ordinal == EnumConfig.SMOOTH_ZOOM.returnEnumOrdinal())
            return FreeLookAddon.smoothZoom;
        return false;
    }

    private int getIntValue(int ordinal) {
        if (ordinal == EnumConfig.ZOOM_TYPE.returnEnumOrdinal())
            return FreeLookAddon.zoomType;
        return -1;
    }

    private float getFloatValue(int ordinal) {
        if (ordinal == EnumConfig.ZOOM_FACTOR.returnEnumOrdinal())
            return FreeLookAddon.zoomFactor;
        if (ordinal == EnumConfig.FREELOOK_RANGE.returnEnumOrdinal())
            return FreeLookAddon.freelookRange;
        return 1.0f;
    }

    private String boolToString(int ordinal, boolean bool) {
        return bool ? "Enabled" : "Disabled";
    }

    private String intToString(int ordinal, int value) {
        String res = "";
        if (ordinal == EnumConfig.ZOOM_TYPE.returnEnumOrdinal()) {
            switch(value){
                case 0: res = "freelook.options.squint.realistic";              break;
                case 1: res = "freelook.options.squint.interpolated";           break;
                case 2: res = "freelook.options.squint.classic";                break;
            }
        }
        return StatCollector.translateToLocal(res);
    }

    private void updateValue(GuiButton button, int ordinal) {
        if (FreeLookAddon.properties == null) {
            // Initialize properties if null
            FreeLookAddon.getInstance().initialize();
        }
        String value;
        if (EnumConfig.values()[ordinal].getEnumBoolean()) {
            value = String.valueOf(toggleBool(button, ordinal));
            button.displayString = boolToString(ordinal, getBoolValue(ordinal));
        } else {
            value = String.valueOf(cycleInt(button, ordinal));
            button.displayString = intToString(ordinal, getIntValue(ordinal));
        }
        FreeLookAddon.properties.put(EnumConfig.values()[ordinal].getEnumProperty(), value);

    }

    private boolean toggleBool(GuiButton button, int ordinal) {
        if (ordinal == EnumConfig.DISABLE_F5.returnEnumOrdinal()) {
            Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
            return FreeLookAddon.enableF5 = !FreeLookAddon.enableF5;
        }
        if (ordinal == EnumConfig.SMOOTH_ZOOM.returnEnumOrdinal())
            return FreeLookAddon.smoothZoom = !FreeLookAddon.smoothZoom;
        return false;
    }

    private int cycleInt(GuiButton button, int ordinal) {
        if (ordinal == EnumConfig.ZOOM_TYPE.returnEnumOrdinal())
            return FreeLookAddon.zoomType = FreeLookAddon.zoomType + 1 > 2 ? 0 : FreeLookAddon.zoomType + 1;
        return -1;
    }

    protected void actionPerformed(GuiButton gButton) {
        if (gButton.enabled) {
            if (gButton.id < 100) {
                this.buttonId = gButton.id;
                String var10001 = gButton.displayString;
                gButton.displayString = "> " + var10001 + " <";
            } else if (gButton.id < 200)
                updateValue(gButton, gButton.id - 100);
            switch (gButton.id) {
                case 200:
                    this.mc.displayGuiScreen(this.parentScreen);
                    FreeLookAddon.getInstance().saveConfigFile();
                    break;
            }
        }
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        if (this.buttonId >= 0) {
            this.updateBind(-100 + par3);
        } else {
            super.mouseClicked(par1, par2, par3);
        }

    }

    protected void keyTyped(char par1, int par2) {
        if (this.buttonId >= 0) {
            this.updateBind(par2);
        } else {
            super.keyTyped(par1, par2);
        }

    }

    public void updateBind(int scancode) {
        if (FreeLookAddon.properties == null) {
            // Initialize properties if null
            FreeLookAddon.getInstance().initialize();
        }
        FreeLookAddon.modBinds[this.buttonId].keyCode = scancode;
        ((GuiButton)this.buttonList.get(this.buttonId)).displayString = GameSettings.getKeyDisplayString(FreeLookAddon.modBinds[this.buttonId].keyCode);
        FreeLookAddon.properties.put(FreeLookAddon.modBinds[this.buttonId].keyDescription, String.valueOf(FreeLookAddon.modBinds[this.buttonId].keyCode));
        this.buttonId = -1;
        KeyBinding.resetKeyBindingArrayAndHash();
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.drawString(this.fontRenderer, StatCollector.translateToLocal(FreeLookAddon.modBinds[0].keyDescription),
                getLeftBorder() + 12, this.height / 6 + 6, 16777215);
        this.drawString(this.fontRenderer, StatCollector.translateToLocal(FreeLookAddon.modBinds[1].keyDescription),
                getLeftBorder() + 162, this.height / 6 + 6, 16777215);
        int count = 0;
        for (EnumConfig config : enumConfigs) {
            this.drawString(this.fontRenderer, StatCollector.translateToLocal(config.getEnumName()), getLeftBorder() + 12, this.height / 6 + 30 + (24 * count), 16777215);
            count++;
        }

        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 15, 16777215);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/freelook.png"));
        this.drawTexturedModalRect(this.width / 2 - 24, this.height / 6 + 120,
                20 + (int) ((flAngleSlider.sliderValue * 9) * 20) % 100,
                20 * (int) Math.floor(flAngleSlider.sliderValue * 9 / 5),
                20, 20);
        super.drawScreen(par1, par2, par3);
    }

    static {
        enumConfigs = new EnumConfig[]{EnumConfig.SMOOTH_ZOOM, EnumConfig.ZOOM_TYPE, EnumConfig.DISABLE_F5, EnumConfig.ZOOM_FACTOR, EnumConfig.FREELOOK_RANGE};
    }
}
