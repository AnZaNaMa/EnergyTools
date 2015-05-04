package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.ExpTools;
import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/18/2015.
 */
public class xpore extends Block {
    public xpore(String unlocalizedName, Material material, float localHardness, float localResistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.setCreativeTab(ExpTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(localHardness);
        this.setResistance(localResistance);

    }
}
