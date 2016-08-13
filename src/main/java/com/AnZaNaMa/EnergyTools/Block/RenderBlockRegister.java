package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.Item.IEnergyItem;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameData;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class RenderBlockRegister {

    public static void registerBlockIcons(){
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.energyblock), 0);
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.energizer), 0);
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.energyore), 0);
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.goldbrick), 0);
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.enervator), 0);
        registerBlock(Item.getItemFromBlock(BlockEnergyTools.energeticpipe), 0);
    }

    public static void registerBlock(Item item, int meta){
            ResourceLocation loc = GameData.getItemRegistry().getNameForObject(item);
            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(loc,"inventory"));
    }
}
