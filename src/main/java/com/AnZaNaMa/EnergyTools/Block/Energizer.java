package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Item.ItemEnergyTools;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 4/19/2015.
 */
<<<<<<< HEAD
public class Energizer extends BaseEnergyBlock implements IEnergyBlock {
=======

public class Energizer extends BlockContainer implements IEnergyBlock {

>>>>>>> 72019497d908b898063556123e684760939138b7
    public Energizer(Material material, String unlocalizedName, float localhardness, float localresistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setHardness(localhardness);
        this.setResistance(localresistance);
        this.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int number){
        return new TileEntityEnergizer();
    }

    @Override
    public void onBlockClicked(World world, BlockPos position, EntityPlayer player){

        if(!world.isRemote) {
            TileEntity entity = world.getTileEntity(position);
            if (entity instanceof TileEntityEnergizer) {
                Item item = null;
                try {
                    item = player.getHeldItem().getItem();
                }catch(NullPointerException e){
                }
                if(item == ItemEnergyTools.infenergyorb){
                    if(((TileEntityEnergizer) entity).getIsMultiblock()) {
                        player.addChatMessage(new ChatComponentText("Is Multiblock of Size: " + ((TileEntityEnergizer) entity).getMultiblockSize()));
                        player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) entity).getEnergyContained()));
                    }
                    else{
                    }
                }
                else if(item != null){
                }
            }
        }
    }
}
