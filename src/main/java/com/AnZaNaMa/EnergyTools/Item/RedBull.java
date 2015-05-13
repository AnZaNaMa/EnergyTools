package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/4/2015.
 */
public class RedBull extends Item implements IEnergyItem{
    public RedBull(String name){
        GameRegistry.registerItem(this, name, Reference.MODID);
        this.setUnlocalizedName(name);
        this.setCreativeTab(EnergyTools.creativeTab);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
        if(!worldIn.isRemote) {
            if(!playerIn.getEntityData().getBoolean("hasWings")) {
                if (playerIn.getEntityData().getInteger("Energy") > 500) {
                    playerIn.getEntityData().setInteger("Energy", playerIn.getEntityData().getInteger("Energy") - 500);
                    playerIn.getEntityData().setBoolean("hasWings", true);
                    playerIn.capabilities.allowFlying = true;
                    playerIn.sendPlayerAbilities();
                    return itemStackIn;
                } else {
                    playerIn.addChatMessage(new ChatComponentText("Not enough energy to fly..."));
                    return itemStackIn;
                }
            }
            else if(playerIn.getEntityData().getBoolean("hasWings")){
                playerIn.capabilities.allowFlying = false;
                playerIn.sendPlayerAbilities();
                playerIn.getEntityData().setBoolean("hasWings", false);
                return itemStackIn;
            }
            else{
                return itemStackIn;
            }
        }
        else{
            return itemStackIn;
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected){
        if(!worldIn.isRemote) {
            if (entityIn instanceof EntityPlayer && entityIn.getEntityData().getBoolean("hasWings")) {
                if (entityIn.getEntityData().getInteger("Energy") > 0) {
                    entityIn.getEntityData().setInteger("Energy", entityIn.getEntityData().getInteger("Energy") - 1);
                } else if(entityIn.getEntityData().getInteger("Energy") <=0){
                    entityIn.getEntityData().setBoolean("hasWings", false);
                    ((EntityPlayer) entityIn).capabilities.allowFlying = false;
                    ((EntityPlayer) entityIn).sendPlayerAbilities();
                }
            }
        }
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }

}
