package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/14/2015.
 */
public class CoalStick extends Item implements IEnergyItem{
    public CoalStick(String unlocalizedName){
        GameRegistry.registerItem(this, unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }
}
