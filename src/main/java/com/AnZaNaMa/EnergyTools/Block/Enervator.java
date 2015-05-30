package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnervator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/14/2015.
 */

public class Enervator extends BlockContainer implements IEnergyBlock {

    public Enervator(Material material, String unlocalizedName, float localhardness, float localresistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setHardness(localhardness);
        this.setResistance(localresistance);
        this.setUnlocalizedName(unlocalizedName);
    }

    public TileEntity createNewTileEntity(World world, int number){
        return new TileEntityEnervator();
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
        if(!worldIn.isRemote) {
            try {
                if (worldIn.getTileEntity(pos) instanceof TileEntityEnervator) {
                    TileEntityEnervator enervator = (TileEntityEnervator) worldIn.getTileEntity(pos);
                    playerIn.addChatMessage(new ChatComponentText("Energy: " + enervator.getEnergyContained()));
                    playerIn.addChatMessage(new ChatComponentText("Boost: " + enervator.getMultiblockNumber()));
                } else {

                }
            } catch (NullPointerException e) {
            }
        }
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }
}
