package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class BlockExpTools {

    public static Block energyblock;
    public static Block energyore;
    public static Block energizer;

    public static void addBlocks(){
        energyblock = new energyblock(BlockNames.ENERGYBLOCK, Material.iron);
        energyore = new energyore(BlockNames.ENERGYORE, Material.rock, 1.5F, 10.0F);
        energizer = new Energizer(Material.iron, BlockNames.ENERGIZER, 1.5F, 10.0F);
    }

}
