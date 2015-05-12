package com.AnZaNaMa.ExpTools.Item;

import com.AnZaNaMa.ExpTools.EnergyTools;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class EnergyIngot extends Item {
    public EnergyIngot(String name){
        GameRegistry.registerItem(this, name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(EnergyTools.creativeTab);
    }
}
