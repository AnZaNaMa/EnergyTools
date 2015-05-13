package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class RenderBlockRegister {

    public static void registerBlockIcons(){
        registerBlock(BlockEnergyTools.energyblock, 0);
        registerBlock(BlockEnergyTools.energizer, 0);
        registerBlock(BlockEnergyTools.energyore, 0);
    }

    public static void registerBlock(Block block, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MODID+":"+ block.getUnlocalizedName(), "inventory"));
    }
}
