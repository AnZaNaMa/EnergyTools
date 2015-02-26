package com.AnZaNaMa.ExpTools;

import com.AnZaNaMa.ExpTools.CreativeTabs.ModCreativeTabs;
import com.AnZaNaMa.ExpTools.Handler.ConfigHandler;
import com.AnZaNaMa.ExpTools.Proxy.CommonProxy;
import com.AnZaNaMa.ExpTools.Proxy.IProxy;
import com.AnZaNaMa.ExpTools.Reference.Reference;

import com.AnZaNaMa.ExpTools.Utility.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class ExpTools {
	
	@Instance(Reference.MODID)
	public static ExpTools instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static CreativeTabs creativeTab = new ModCreativeTabs(CreativeTabs.getNextID(), "EXPTab");
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        LogHelper.info("Mod Pre-Initialized!");
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event){
        proxy.init(event);
        LogHelper.info("Mod Initialized!");
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
        LogHelper.info("Mod Post-Initialized!");
	}
	
}
