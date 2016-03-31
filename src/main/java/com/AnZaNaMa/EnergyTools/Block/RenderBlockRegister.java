package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.Item.IEnergyItem;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class RenderBlockRegister {

    public static void registerBlockIcons(){
        registerBlock(BlockEnergyTools.energyblock, 0);
        registerBlock(BlockEnergyTools.energizer, 0);
        registerBlock(BlockEnergyTools.energyore, 0);
        registerBlock(BlockEnergyTools.goldbrick, 0);
        registerBlock(BlockEnergyTools.enervator, 0);
        registerBlock(BlockEnergyTools.energeticpipe, 0);
    }

    public static void registerBlock(Block block, int meta){
        if(block instanceof IEnergyBlock) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MODID + ":" + ((IEnergyBlock) block).getName(),"inventory"));
        }
    }
}
