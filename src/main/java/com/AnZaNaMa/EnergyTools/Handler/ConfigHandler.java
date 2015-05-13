package com.AnZaNaMa.EnergyTools.Handler;

import com.AnZaNaMa.EnergyTools.Reference.ConfigCategories;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by Andrew Graber on 2/24/2015.
 */
public class ConfigHandler {
    public static Configuration config;
    public static boolean enableenergyblock = false;

    public static void init(File configFile){
        if(config == null){
            config = new Configuration(configFile);
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(Reference.MODID)){
            loadConfig();
        }
    }

    public void loadConfig(){
        enableenergyblock = config.getBoolean("enableenergyblock", ConfigCategories.ENABLE_BLOCKS, true, "Set to false to disable Energy Block");

        if(config.hasChanged()){
            config.save();
        }
    }
}
