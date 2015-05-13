package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.Reference.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class BlockEnergyTools {

    public static Block energyblock;
    public static Block energyore;
    public static Block energizer;

    public static void addBlocks(){
        energyblock = new energyblock(BlockNames.ENERGYBLOCK, Material.iron);
        energyore = new energyore(BlockNames.ENERGYORE, Material.rock, 1.5F, 10.0F);
        energizer = new Energizer(Material.iron, BlockNames.ENERGIZER, 1.5F, 10.0F);
    }

}
