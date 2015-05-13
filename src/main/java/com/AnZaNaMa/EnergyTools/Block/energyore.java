package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/18/2015.
 */
public class energyore extends Block {
    public energyore(String unlocalizedName, Material material, float localHardness, float localResistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(localHardness);
        this.setResistance(localResistance);

    }
}
