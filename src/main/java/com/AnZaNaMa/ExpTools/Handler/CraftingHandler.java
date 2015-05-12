package com.AnZaNaMa.ExpTools.Handler;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 2/26/2015.
 */
public class CraftingHandler {
    public static void init(){
        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.energypick),
                "III",
                " S ",
                " S ", 'I',new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.energyshovel),
                " I ",
                " S ",
                " S ", 'I', new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.energyaxe),
                " II",
                " SI",
                " S ", 'I', new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(BlockExpTools.energizer),
                "PEP",
                "IRI",
                "PIP", 'P', new ItemStack(Items.ender_pearl), 'E', new ItemStack(Items.emerald), 'I', new ItemStack(ItemExpTools.energyingot), 'R', new ItemStack(Blocks.redstone_block));

        GameRegistry.addShapedRecipe(new ItemStack(BlockExpTools.energizer),
                "SRS",
                "RER",
                "SRS", 'S', new ItemStack(Blocks.stone), 'R', new ItemStack(Blocks.redstone_block), 'E', new ItemStack(BlockExpTools.energyblock));

        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.redbull),
                "IBI",
                "INI",
                "ILI", 'I', new ItemStack(Items.iron_ingot), 'B', new ItemStack(Blocks.iron_bars), 'N', new ItemStack(Items.nether_star), 'L', new ItemStack(Items.potionitem, 1, 8235));

        GameRegistry.addShapedRecipe(new ItemStack(BlockExpTools.energyblock),
                "III",
                "III",
                "III", 'I', new ItemStack(ItemExpTools.energyingot));

    }
}
