package com.AnZaNaMa.EnergyTools.Proxy;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergyTools;
import com.AnZaNaMa.EnergyTools.Handler.ConfigHandler;
import com.AnZaNaMa.EnergyTools.Handler.CraftingHandler;
import com.AnZaNaMa.EnergyTools.Handler.SmeltingHandler;
import com.AnZaNaMa.EnergyTools.Item.ItemEnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import com.AnZaNaMa.EnergyTools.Reference.VersioningNBT;
import com.AnZaNaMa.EnergyTools.Waila.WailaEnergyTools;
import com.AnZaNaMa.EnergyTools.World.WorldGenEnergyOre;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {

	public void preInit(FMLPreInitializationEvent event)
	{
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        BlockEnergyTools.addBlocks();
        ItemEnergyTools.addItems();
	}
	
	
	public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(EnergyTools.instance, EnergyTools.guiHandler);
        GameRegistry.registerWorldGenerator(new WorldGenEnergyOre(), 5);
        FMLInterModComms.sendRuntimeMessage(Reference.MODID, "VersionChecker", "addUpdate", new VersioningNBT());
        CraftingHandler.init();
        SmeltingHandler.init();
        TileEntityEnergyTools.init();
        WailaEnergyTools.init();
	}
	
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
