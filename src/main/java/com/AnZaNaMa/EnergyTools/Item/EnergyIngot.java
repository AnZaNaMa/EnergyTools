package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class EnergyIngot extends Item implements IEnergyItem{
    public EnergyIngot(String name){
        GameRegistry.registerItem(this, name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(EnergyTools.creativeTab);
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }
}
