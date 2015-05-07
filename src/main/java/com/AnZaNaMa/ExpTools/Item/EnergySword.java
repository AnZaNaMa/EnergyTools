package com.AnZaNaMa.ExpTools.Item;

import com.AnZaNaMa.ExpTools.ExpTools;
import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/5/2015.
 */
public class EnergySword extends ItemSword{
    public EnergySword(String unlocalizedName, Item.ToolMaterial toolMaterial){
        super(toolMaterial);
        GameRegistry.registerItem(this, unlocalizedName, Reference.MODID);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ExpTools.creativeTab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        if(attacker instanceof EntityPlayer && attacker.getEntityData().getInteger("Energy") >= 10){
            attacker.getEntityData().setInteger("Energy", attacker.getEntityData().getInteger("Energy") - 10);
            return true;
        }
        if(attacker instanceof EntityPlayer && attacker.getEntityData().getInteger("Energy") < 10){
            attacker.getEntityData().setInteger("Energy", 0);
            return true;
        }
        else{
            return true;
        }
    }
}
