package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BlockExpTools {

    public static Block xpblock;
    public static Block energyore;
    public static Block energizer;

    public static void addBlocks(){
        xpblock = new xpblock(BlockNames.XPBLOCK, Material.iron);
        energyore = new xpore(BlockNames.XPORE, Material.rock, 1.5F, 10.0F);
        energizer = new Energizer(Material.iron, BlockNames.ENERGIZER, 1.5F, 10.0F);
    }

}
