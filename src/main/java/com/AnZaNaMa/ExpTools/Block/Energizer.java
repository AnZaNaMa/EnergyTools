package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.ExpTools.ExpTools;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 4/19/2015.
 */
public class Energizer extends BlockContainer {
    public Energizer(Material material, String unlocalizedName, float localhardness, float localresistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        setCreativeTab(ExpTools.creativeTab);
        setHardness(localhardness);
        setResistance(localresistance);
        setUnlocalizedName(unlocalizedName);
    }

    public TileEntity createNewTileEntity(World world, int number){
        return new TileEntityEnergizer();
    }

    @Override
    public void onBlockClicked(World world, BlockPos position, EntityPlayer player){

        if(!world.isRemote) {
            TileEntity entity = world.getTileEntity(position);
            if (entity instanceof TileEntityEnergizer) {
                player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) world.getTileEntity(position)).getEnergyContained()));
            }
        }
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }
}
