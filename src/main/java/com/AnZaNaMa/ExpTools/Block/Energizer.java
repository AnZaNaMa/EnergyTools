package com.AnZaNaMa.ExpTools.Block;

import com.AnZaNaMa.ExpTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.ExpTools.EnergyTools;
import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
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
public class Energizer extends BlockContainer {
    public Energizer(Material material, String unlocalizedName, float localhardness, float localresistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        setCreativeTab(EnergyTools.creativeTab);
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
                Item item = null;
                try {
                    item = player.getHeldItem().getItem();
                }catch(NullPointerException e){
                    player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) entity).getEnergyContained()));
                }
                if(item == ItemExpTools.infenergyorb){
                    if(((TileEntityEnergizer) entity).getIsMultiblock()) {
                        player.addChatMessage(new ChatComponentText("Is Multiblock of Size: " + ((TileEntityEnergizer) entity).getMultiblockSize()));
                        player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) entity).getEnergyContained()));
                    }
                    else{
                        player.addChatMessage(new ChatComponentText("Is Not Multiblock."));
                        player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) entity).getEnergyContained()));
                    }
                }
                else if(item != null){
                    player.addChatMessage(new ChatComponentText("Energy Stored: " + ((TileEntityEnergizer) entity).getEnergyContained()));
                }
            }
        }
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }
}
