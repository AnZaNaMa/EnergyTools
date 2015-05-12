package com.AnZaNaMa.ExpTools.Proxy;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Entity.TileEntity.EnergyToolsTESR;
import com.AnZaNaMa.ExpTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import com.AnZaNaMa.ExpTools.Reference.ItemNames;
import com.AnZaNaMa.ExpTools.Reference.Reference;
import com.AnZaNaMa.ExpTools.Render.Blocks.RenderBlockRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(ItemExpTools.infenergyorb, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.INFENERGYORB, "inventory"));

        renderItem.getItemModelMesher().register(ItemExpTools.energyaxe, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.ENERGYAXE, "inventory"));

        renderItem.getItemModelMesher().register(ItemExpTools.energypick, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.ENERGYPICK, "inventory"));

        renderItem.getItemModelMesher().register(ItemExpTools.energyshovel, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.ENERGYSHOVEL, "inventory"));

        renderItem.getItemModelMesher().register(ItemExpTools.energyingot, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.ENERGYINGOT, "inventory"));

        renderItem.getItemModelMesher().register(ItemExpTools.redbull, 0, new ModelResourceLocation(Reference.MODID + ":" + ItemNames.REDBULL, "inventory"));

        renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockExpTools.energyore), 0, new ModelResourceLocation(Reference.MODID + ":" + BlockNames.ENERGYORE, "inventory"));

        renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockExpTools.energizer), 0, new ModelResourceLocation(Reference.MODID + ":" + BlockNames.ENERGIZER, "inventory"));

        renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockExpTools.energyblock), 0, new ModelResourceLocation(Reference.MODID + ":" + BlockNames.ENERGYBLOCK, "inventory"));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergizer.class, new EnergyToolsTESR());
    }

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
