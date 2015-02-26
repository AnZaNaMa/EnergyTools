package com.AnZaNaMa.ExpTools.Proxy;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy implements IProxy {

	public void preInit(FMLPreInitializationEvent event)
	{
        BlockExpTools.addBlocks();
	}
	
	
	public void init(FMLInitializationEvent event)
    {

	}
	
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
