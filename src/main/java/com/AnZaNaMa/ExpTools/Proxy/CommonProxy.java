package com.AnZaNaMa.ExpTools.Proxy;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Entity.TileEntity.TileEntityEnergyTools;
import com.AnZaNaMa.ExpTools.ExpTools;
import com.AnZaNaMa.ExpTools.Handler.ConfigHandler;
import com.AnZaNaMa.ExpTools.Handler.CraftingHandler;
import com.AnZaNaMa.ExpTools.Handler.SmeltingHandler;
import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
import com.AnZaNaMa.ExpTools.World.WorldGenEnergyOre;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {

	public void preInit(FMLPreInitializationEvent event)
	{
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        BlockExpTools.addBlocks();
        ItemExpTools.addItems();
        CraftingHandler.init();
        SmeltingHandler.init();
        TileEntityEnergyTools.init();
	}
	
	
	public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(ExpTools.instance, ExpTools.guiHandler);
        GameRegistry.registerWorldGenerator(new WorldGenEnergyOre(), 5);
	}
	
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
