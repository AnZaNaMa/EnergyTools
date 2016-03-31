package com.AnZaNaMa.EnergyTools.Utility;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class KeyBinds {
    public static KeyBinding lift, fall, toggleGUI;
    public static void init(){
        //Define Key Bindings with (unlocalizedName, LWJGL Key Constant, unlocalizedCategory)
        lift = new KeyBinding("key.lift", Keyboard.KEY_SPACE, "key.categories.energytools");
        fall = new KeyBinding("key.fall", Keyboard.KEY_LSHIFT, "key.categories.energytools");
        toggleGUI = new KeyBinding("key.togglegui", Keyboard.KEY_HOME, "key.categories.energytools");

        //Register Key Bindings
        ClientRegistry.registerKeyBinding(lift);
        ClientRegistry.registerKeyBinding(fall);
        ClientRegistry.registerKeyBinding(toggleGUI);
    }
}
