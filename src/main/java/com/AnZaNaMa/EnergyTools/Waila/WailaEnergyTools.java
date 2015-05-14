package com.AnZaNaMa.EnergyTools.Waila;

import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * Created by Andrew Graber on 5/13/2015.
 */
public class WailaEnergyTools {
    public static void init(){
        FMLInterModComms.sendMessage("Waila", "register", "com.AnZaNaMa.EnergyTools.Waila.WailaTileHandler.callbackRegister");
    }
}
