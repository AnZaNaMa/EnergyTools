package com.AnZaNaMa.ExpTools.Utility;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class KeyBinds {
    public void init(){
        KeyBinding[] key = {new KeyBinding("energyToggle", Keyboard.KEY_END, "EnergyTools")};
        boolean[] repeat = {false};
    }
}
