package com.AnZaNaMa.ExpTools.Handler;

import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 2/26/2015.
 */
public class CraftingHandler {
    public static void init(){
        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.XPPick),
                "III",
                " S ",
                " S ", 'I',new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.XPShovel),
                " I ",
                " S ",
                " S ", 'I', new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(ItemExpTools.XPAxe),
                " II",
                " SI",
                " S ", 'I', new ItemStack(ItemExpTools.energyingot), 'S', new ItemStack(Items.stick));
    }
}
