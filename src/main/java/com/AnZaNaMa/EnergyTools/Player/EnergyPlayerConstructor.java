package com.AnZaNaMa.EnergyTools.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Andrew Graber on 5/1/2015.
 */
public class EnergyPlayerConstructor {

    @SubscribeEvent
    public static void onPlayerCreated(EntityEvent.EntityConstructing event){
        if(event.entity instanceof EntityPlayer)
        event.entity.getEntityData().setInteger("Energy", 0);
        event.entity.getEntityData().setBoolean("showEnergy", true);
    }
}
