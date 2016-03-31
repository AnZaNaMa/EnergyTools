package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/15/2015.
 */
public class GoldBrick extends BaseEnergyBlock implements IEnergyBlock {

    public GoldBrick(Material material, String unlocalizedName){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);

        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }
}
