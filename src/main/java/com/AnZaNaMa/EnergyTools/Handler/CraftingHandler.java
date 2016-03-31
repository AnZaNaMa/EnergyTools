package com.AnZaNaMa.EnergyTools.Handler;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.Item.ItemEnergyTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 2/26/2015.
 */
public class CraftingHandler {
    public static void init(){
        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.energypick),
                "III",
                " S ",
                " S ", 'I',new ItemStack(ItemEnergyTools.energyingot), 'S', new ItemStack(ItemEnergyTools.coalstick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.energyshovel),
                " I ",
                " S ",
                " S ", 'I', new ItemStack(ItemEnergyTools.energyingot), 'S', new ItemStack(ItemEnergyTools.coalstick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.energyaxe),
                " II",
                " SI",
                " S ", 'I', new ItemStack(ItemEnergyTools.energyingot), 'S', new ItemStack(ItemEnergyTools.coalstick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.energysword),
                " I ",
                " I ",
                " S ", 'I', new ItemStack(ItemEnergyTools.energyingot), 'S', new ItemStack(ItemEnergyTools.coalstick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.energyhoe),
                " II",
                " S ",
                " S ", 'I', new ItemStack(ItemEnergyTools.energyingot), 'S', new ItemStack(ItemEnergyTools.coalstick));

        GameRegistry.addShapedRecipe(new ItemStack(BlockEnergyTools.energizer),
                "PEP",
                "IRI",
                "PIP", 'P', new ItemStack(Items.ender_pearl), 'E', new ItemStack(Items.emerald), 'I', new ItemStack(ItemEnergyTools.energyingot), 'R', new ItemStack(Blocks.redstone_block));

        GameRegistry.addShapedRecipe(new ItemStack(BlockEnergyTools.energizer),
                "SRS",
                "RER",
                "SRS", 'S', new ItemStack(Blocks.stone), 'R', new ItemStack(Blocks.redstone_block), 'E', new ItemStack(BlockEnergyTools.energyblock));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.redbull),
                "IBI",
                "INI",
                "ILI", 'I', new ItemStack(Items.iron_ingot), 'B', new ItemStack(Blocks.iron_bars), 'N', new ItemStack(Items.nether_star), 'L', new ItemStack(Items.potionitem, 1, 8235));

        GameRegistry.addShapedRecipe(new ItemStack(BlockEnergyTools.energyblock),
                "III",
                "III",
                "III", 'I', new ItemStack(ItemEnergyTools.energyingot));

        GameRegistry.addShapedRecipe(new ItemStack(ItemEnergyTools.coalstick),
                "S",
                "S", 'S', new ItemStack(Blocks.coal_block));

        GameRegistry.addShapedRecipe(new ItemStack(BlockEnergyTools.goldbrick),
                "III",
                "III",
                "III", 'I', new ItemStack(Items.gold_nugget));

    }
}
