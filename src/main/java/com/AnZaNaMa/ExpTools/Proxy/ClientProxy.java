package com.AnZaNaMa.ExpTools.Proxy;

import com.AnZaNaMa.ExpTools.Render.Blocks.RenderBlockRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
        RenderBlockRegister.RegisterBlockRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
