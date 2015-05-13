package com.AnZaNaMa.EnergyTools.CreativeTabs;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class ModCreativeTabs extends CreativeTabs{

    public ModCreativeTabs(int tabID, String unlocalizedName){
        super(tabID, unlocalizedName);
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockEnergyTools.energyblock);
    }
}
