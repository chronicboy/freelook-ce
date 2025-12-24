package btw.community.jeffyjamzhd.freelook;

import api.AddonHandler;
import api.BTWAddon;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;
import org.lwjgl.input.Keyboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class FreeLookAddon extends BTWAddon {
    /*
        @todo
        - Rework how properties are handled, I dont think
          my way was ever the intent with BTW's config api
          (having values set at runtime)
        - Clean up code
     */

    // Declare variables
    private static FreeLookAddon instance;
    public static KeyBinding freelook_key = new KeyBinding(StatCollector.translateToLocal("key.freelook"), Keyboard.KEY_LMENU);
    public static KeyBinding zoom_key = new KeyBinding(StatCollector.translateToLocal("key.zoom"), Keyboard.KEY_C);
    public static KeyBinding[] modBinds;
    public static float freelookRange, zoomFactor;
    public static boolean enableF5, smoothZoom;
    public static int zoomType;

    // CONFIG
    public static Map<String, String> properties;
    // Prepare for a scare!
    private static final Map<String, String[]> propertiesDefault = Map.ofEntries(
            entry("enableF5",
        new String[]{
                "Enables the use of F5 camera modes. (DEFAULT: true)",
                "true"}),
            entry("key.zoom",
        new String[]{
                "Keybind used for zooming the camera / squinting (DEFAULT: " + Keyboard.KEY_C + ")",
                String.valueOf(Keyboard.KEY_C)}),
            entry("key.freelook",
        new String[]{
                "Keybind used for freelooking (DEFAULT: " + Keyboard.KEY_LMENU + ")",
                String.valueOf(Keyboard.KEY_LMENU)}),
            entry("smoothZoom",
        new String[]{
                "Enables smooth camera movement on zoom. When disabled, camera sensitivity will be adjusted\n" +
                "# to fit zoom percentage instead. (DEFAULT: false)",
                "false"}),
            entry("zoomType",
        new String[]{
                "Type of zoom to emulate. (DEFAULT: 2)\n" +
                "# 0 - \"Realistic\": Simulates effects of squinting (tunnel vision, slower zoom)\n" +
                "# 1 - Interpolated: Fast, interpolated zooming\n" +
                "# 2 - Classic: Optifine-like snap zoom (instant)",
                "2"}),
            entry("zoomFactor",
        new String[]{
                "Percentage to zoom in FOV by. (DEFAULT: 800) (EQ: 200% + [VALUE] = 1000% Eagle Eyed)",
                "800"}),
            entry("freelookRange",
        new String[]{
                "Range of view in Freelook mode. (DEFAULT: 180.0 deg.) (EQ: 45 deg + [VALUE] MAX: 180 = Full 360° rotation)",
                "180.0"})
    );

    public FreeLookAddon() {
        super();
    }

    public static FreeLookAddon getInstance() {
        return instance == null ? (new FreeLookAddon()) : instance;
    }

    public void initializeKeybinds() {
        // Set descriptions
        freelook_key.keyDescription = "key.freelook";
        zoom_key.keyDescription = "key.zoom";

        // Add to array
        modBinds = new KeyBinding[]{zoom_key, freelook_key};
    }

    public void preInitialize() {
        // Note: registerProperty doesn't exist in BTW CE 3.0.0 API
        // Configuration is handled via properties file instead
        MixinExtrasBootstrap.init();
    }

    public void handleConfigProperties(Map<String, String> propertyValues) {
        if (propertyValues != null) {
            properties = propertyValues;
        } else if (properties == null) {
            // Initialize with defaults if null
            properties = new HashMap<>();
            for (String key : propertiesDefault.keySet()) {
                properties.put(key, propertiesDefault.get(key)[1]);
            }
        }
        
        // Parse and set values, using defaults if missing
        zoom_key.keyCode = Integer.parseInt(properties.getOrDefault("key.zoom", String.valueOf(Keyboard.KEY_C)));
        freelook_key.keyCode = Integer.parseInt(properties.getOrDefault("key.freelook", String.valueOf(Keyboard.KEY_LMENU)));
        enableF5 = Boolean.parseBoolean(properties.getOrDefault("enableF5", "true"));
        smoothZoom = Boolean.parseBoolean(properties.getOrDefault("smoothZoom", "false"));
        zoomType = Integer.parseInt(properties.getOrDefault("zoomType", "2"));
        zoomFactor = (Float.parseFloat(properties.getOrDefault("zoomFactor", "800"))) / 800.0f;
        freelookRange = (Float.parseFloat(properties.getOrDefault("freelookRange", "180.0"))) / 135.0f;
    }

    @Override
    public void initialize() {
        instance = this;
        // Initialize properties with default values if not already set
        if (properties == null) {
            properties = new HashMap<>();
            for (String key : propertiesDefault.keySet()) {
                properties.put(key, propertiesDefault.get(key)[1]);
            }
        }
        // Initialize config values from properties
        handleConfigProperties(properties);
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    // Handles slider value
    public void setSliderConfig(String property, float value) {
        if (properties == null) {
            properties = new HashMap<>();
        }
        switch (property) {
            case "zoomFactor":
                zoomFactor = value;
                properties.put(property, String.valueOf(value * 800.0f));
                break;
            case "freelookRange":
                freelookRange = value;
                properties.put(property, String.valueOf(freelookRange * 135.0f));
                break;
        }

    }

    // Gets slider display
    public String getSliderDisplay(String property) {
        switch (property) {
            case "zoomFactor":
                // When zoomFactor is 1.0f (800 in config), it means 1000% = "Eagle Eyed"
                return zoomFactor >= 1.0f ? "Eagle Eyed" : String.format("%d%%", (int) ((zoomFactor * 800.0f) + 200.0f));
            case "freelookRange":
                // When freelookRange is 1.0f (180.0 in config), it means 180 degrees = "Head On A Swivel" (full 360° rotation)
                return freelookRange >= 1.0f ? "Head On A Swivel" : String.format("%.1f deg.", (freelookRange * 135.0f) + 45.0f);
        }
        return "";
    }

    // Writes to mod's config file
    public void saveConfigFile() {
        if (properties == null) {
            return; // Can't save if properties not initialized
        }
        String filename = "btwfreelook.properties";
        File config = new File("config/" + filename);

        try {
            BufferedWriter writer = Files.newBufferedWriter(config.toPath(), StandardOpenOption.TRUNCATE_EXISTING);
            List<String> propertyNames = properties.keySet().stream().toList();

            for(int i = 0; i < properties.size(); i++) {
                // Get info
                String propertyName = propertyNames.get(i);
                String comment = propertiesDefault.get(propertyName)[0];

                // Write comment
                if (!comment.isEmpty()) {
                    writer.write("\n# " + comment + "\n");
                }

                // Get & write value
                String propertyValue;
                if (properties.containsKey(propertyName))
                    propertyValue = properties.get(propertyName);
                else
                    propertyValue = propertiesDefault.get(propertyName)[1];
                writer.write(propertyName + "=" + propertyValue + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}