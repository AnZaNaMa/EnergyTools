package com.AnZaNaMa.ExpTools.Handler;

import com.AnZaNaMa.ExpTools.Reference.ConfigCategories;
import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by Andrew Graber on 2/24/2015.
 */
public class ConfigHandler {
    public static Configuration config;
    public static boolean enablexpblock = false;

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
        enablexpblock = config.getBoolean("enablexpblock", ConfigCategories.ENABLE_BLOCKS, true, "Set to false to disable Experience Block");

        if(config.hasChanged()){
            config.save();
        }
    }
}
