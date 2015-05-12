package com.AnZaNaMa.ExpTools.Render.Blocks;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class RenderBlockRegister {

    public static void RegisterBlockRenderer(){
        registerBlock(BlockExpTools.energyblock, 0);
        registerBlock(BlockExpTools.energizer, 0);
        registerBlock(BlockExpTools.energyore, 0);
    }

    public static void registerBlock(Block block, int meta){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MODID+":"+ block.getUnlocalizedName(), "inventory"));
    }
}
