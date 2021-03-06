package com.AnZaNaMa.EnergyTools.Waila;

import com.AnZaNaMa.EnergyTools.Block.EnergeticPipe;
import com.AnZaNaMa.EnergyTools.Block.Energizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.impl.TipList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.List;

/**
 * Created by Andrew Graber on 12/14/2015.
 */
@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaPipeHandler implements IWailaDataProvider{

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register){
        WailaPipeHandler instance = new WailaPipeHandler();
        register.registerBodyProvider(instance, TileEntityPipe.class);
        register.addConfig("EnergyTools", "option.energytools.showMore", "Show More?", false);
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config){
        return accessor.getStack();
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof TileEntityPipe){

            currenttip.add(((TileEntityPipe) accessor.getTileEntity()).getEnergyContained() + "/" + ((TileEntityPipe) accessor.getTileEntity()).getMaxEnergyContained() + " E-E");
        }

        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        return tag;
    }
}
