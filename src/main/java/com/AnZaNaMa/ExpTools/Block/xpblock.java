package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.ExpTools;
import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class xpblock extends Block {

    public xpblock(String unlocalizedName, Material material, float localHardness, float localResistance){
        super(material);
        GameRegistry.registerBlock(this, BlockNames.XPBLOCK);
        this.setCreativeTab(ExpTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(localHardness);
        this.setResistance(localResistance);
    }
    public xpblock(String unlocalizedName, Material material){
        this(unlocalizedName, material, 1.5F, 10.0F);
    }

}
