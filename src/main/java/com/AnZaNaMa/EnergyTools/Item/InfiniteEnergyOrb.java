package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.ItemNames;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/13/2015.
 */
public class InfiniteEnergyOrb extends Item{

    public InfiniteEnergyOrb(String unlocalizedName){
        GameRegistry.registerItem(this, unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);

    }

    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
        if(!worldIn.isRemote) {
            if (playerIn.isSneaking()) {
                if(playerIn.isInWater()){
                    if(playerIn.getEntityData().getBoolean("showEnergy")){
                        playerIn.getEntityData().setBoolean("showEnergy", false);
                    }
                    else if(!playerIn.getEntityData().getBoolean("showEnergy")){
                        playerIn.getEntityData().setBoolean("showEnergy", true);
                    }
                }
                if (playerIn.getEntityData().getInteger("Energy") < 100) {
                    playerIn.getEntityData().setInteger("Energy", 0);
                } else {
                    playerIn.getEntityData().setInteger("Energy", playerIn.getEntityData().getInteger("Energy") - 100);
                }
            } else {
                playerIn.getEntityData().setInteger("Energy", playerIn.getEntityData().getInteger("Energy") + 10);
            }
            return itemStackIn;
        }
        else{
            return itemStackIn;
        }
    }

}
