package com.AnZaNaMa.EnergyTools.Proxy;

import com.AnZaNaMa.EnergyTools.Block.RenderBlockRegister;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR.RegisterTESR;
import com.AnZaNaMa.EnergyTools.Item.RenderItemRegister;
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
        RenderBlockRegister.registerBlockIcons();
        RenderItemRegister.registerItemIcons();
        RegisterTESR.registerModRenderers();
    }

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
