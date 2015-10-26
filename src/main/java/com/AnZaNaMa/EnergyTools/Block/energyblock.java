package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class energyblock extends BlockContainer implements IEnergyBlock{

    public energyblock(String unlocalizedName, Material material, float localHardness, float localResistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(localHardness);
        this.setResistance(localResistance);
    }

    public TileEntity createNewTileEntity(World world, int number){
        return new TileEntityEnergyBlock();
    }

    public energyblock(String unlocalizedName, Material material){
        this(unlocalizedName, material, 1.5F, 10.0F);
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

}
