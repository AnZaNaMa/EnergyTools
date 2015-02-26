package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BlockExpTools {

    public static Block xpblock;

    public static void addBlocks(){
        xpblock = new xpblock(BlockNames.XPBLOCK, Material.iron);
    }

}
