package com.AnZaNaMa.EnergyTools;

import com.AnZaNaMa.EnergyTools.Client.GUI.ModGUIEnergy;
import com.AnZaNaMa.EnergyTools.Client.GUI.ModGUIHandler;
import com.AnZaNaMa.EnergyTools.CreativeTabs.ModCreativeTabs;
import com.AnZaNaMa.EnergyTools.Proxy.IProxy;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class EnergyTools {

    public static ModGUIHandler guiHandler = new ModGUIHandler();

	@Instance(Reference.MODID)
	public static EnergyTools instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static CreativeTabs creativeTab = new ModCreativeTabs(CreativeTabs.getNextID(), "EnergyTab");

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
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
        MinecraftForge.EVENT_BUS.register(new ModGUIEnergy(Minecraft.getMinecraft()));
	}
}