package btw.community.jeffyjamzhd.freelook.gui;

import java.awt.*;

public enum EnumConfig {

    /*
    /   ENUMCONFIG
    /   ---------------------------------------------------------
    /   Name - config translation key
    /   Property - config property to r/w to
    /   isBool - dictates if option is boolean
    /   isFloat - array of values,
    /             [0] - dictates if option is a float, 1 == true, 0 == false
    /             [1] - places to snap the value to (1.0f / [VALUE]) 0 == no snapping
    /   isInt - dictates if option is integer
    */

    DISABLE_F5("freelook.options.disableTP", "enableF5", true, new int[]{0, 0}, false),
    SMOOTH_ZOOM("freelook.options.zoomsmooth", "smoothZoom", true, new int[]{0, 0}, false),
    ZOOM_FACTOR("freelook.options.zoomfactor", "zoomFactor", false, new int[]{1, 16}, false),
    FREELOOK_RANGE("freelook.options.flrange", "freelookRange", false, new int[]{1, 9}, false),
    ZOOM_TYPE("freelook.options.squint", "zoomType", false, new int[]{0, 0}, true);

    private String name;
    private String property;
    private final boolean isBool;
    private final int[] isFloat;
    private final boolean isInt;

    public int returnEnumOrdinal() {
        return this.ordinal();
    }

    EnumConfig(String name, String property, boolean isBool, int[] isFloat, boolean isInt) {
        this.name = name;
        this.property = property;
        this.isBool = isBool;
        this.isFloat = isFloat;
        this.isInt = isInt;
    }

    public String getEnumName() { return this.name; }

    public String getEnumProperty() { return this.property; }

    public boolean getEnumFloat() {
        return this.isFloat[0] != 0;
    }

    public int getEnumFloatNotches() {
        return this.isFloat[1];
    }

    public boolean getEnumBoolean() {
        return this.isBool;
    }

    public boolean getEnumInt() { return this.isInt; }
}
