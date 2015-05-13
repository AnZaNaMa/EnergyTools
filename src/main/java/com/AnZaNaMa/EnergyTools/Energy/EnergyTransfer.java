package com.AnZaNaMa.EnergyTools.Energy;

import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by Andrew Graber on 5/12/2015.
 */
public class EnergyTransfer {
    public static boolean addEnergy(World world, EntityPlayer player, int amount){
        if(!world.isRemote) {
            NBTTagCompound data = player.getEntityData();
            if (data.getInteger("Energy") + amount <= Integer.MAX_VALUE) {
                data.setInteger("Energy", data.getInteger("Energy") + amount);
                return true;
            } else {
                LogHelper.error("Player: " + player.getDisplayNameString() + " has too much energy! Value was higher than Integer.MAX_VALUE");
                player.addChatMessage(new ChatComponentText("You Have Too Much Energy To Transfer. Consider Storing Some."));
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static boolean subtractEnergy(World world, EntityPlayer player, int amount){
        if(!world.isRemote) {
            NBTTagCompound data = player.getEntityData();
            if (data.getInteger("Energy") - amount >= 0) {
                data.setInteger("Energy", data.getInteger("Energy") - amount);
                return true;
            } else if (data.getInteger("Energy") > 0 && data.getInteger("Energy") < amount) {
                data.setInteger("Energy", 0);
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
