package com.AnZaNaMa.EnergyTools.Client.GUI;

import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Andrew Graber on 4/13/2015.
 */
public class ModGUIHandler implements IGuiHandler {

    Minecraft mc = Minecraft.getMinecraft();

    public ModGUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID){
            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID){
            default: return null;
        }
    }

}
