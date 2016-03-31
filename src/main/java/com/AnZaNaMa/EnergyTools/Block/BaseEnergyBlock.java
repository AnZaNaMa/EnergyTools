package com.AnZaNaMa.EnergyTools.Block;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Andrew Graber on 3/31/2016.
 */
public class BaseEnergyBlock extends BlockContainer implements IEnergyBlock {

    public BaseEnergyBlock(Material material){
        super(material);
    }

    public TileEntity createNewTileEntity(World world, int number){
        return new TileEntity() {
        };
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }

    @Override
    public int getRenderType(){
        return 3;
    }
}
