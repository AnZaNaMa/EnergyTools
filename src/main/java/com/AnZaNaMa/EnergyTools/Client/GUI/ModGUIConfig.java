package com.AnZaNaMa.EnergyTools.Client.GUI;

import com.AnZaNaMa.EnergyTools.Handler.ConfigHandler;
import com.AnZaNaMa.EnergyTools.Reference.ConfigCategories;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

/**
 * Created by Andrew Graber on 2/24/2015.
 */
public class ModGUIConfig extends GuiConfig {

    public ModGUIConfig(GuiScreen guiScreen){
        super(guiScreen,
                new ConfigElement(ConfigHandler.config.getCategory(ConfigCategories.ENABLE_BLOCKS)).getChildElements(),
                Reference.MODID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
