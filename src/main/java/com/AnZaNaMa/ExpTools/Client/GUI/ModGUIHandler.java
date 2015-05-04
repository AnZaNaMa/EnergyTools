package com.AnZaNaMa.ExpTools.Client.GUI;

import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.util.BlockPos;
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
