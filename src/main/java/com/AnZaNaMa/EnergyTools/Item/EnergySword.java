package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Energy.EnergyTransfer;
import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
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
    public EnergySword(Item.ToolMaterial toolMaterial, String unlocalizedName){
        super(toolMaterial);
        GameRegistry.registerItem(this, unlocalizedName, Reference.MODID);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        if(attacker instanceof EntityPlayer) {
            if (EnergyTransfer.subtractEnergy(attacker.getEntityWorld(), (EntityPlayer) attacker, 10)) {
                return true;
            }
            else{
                stack.stackSize = 0;
                return false;
            }
        }
        else{
            return false;
        }
    }
}
