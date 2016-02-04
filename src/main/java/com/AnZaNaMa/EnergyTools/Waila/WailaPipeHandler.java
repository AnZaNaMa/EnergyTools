package com.AnZaNaMa.EnergyTools.Waila;

import com.AnZaNaMa.EnergyTools.Block.EnergeticPipe;
import com.AnZaNaMa.EnergyTools.Block.Energizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.impl.TipList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Optional;

/**
 * Created by Andrew Graber on 12/14/2015.
 */
@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaPipeHandler implements IWailaDataProvider {

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register){
        WailaPipeHandler instance = new WailaPipeHandler();
        register.registerBodyProvider(instance, EnergeticPipe.class);
        register.addConfig("EnergyTools", "option.energytools.showMore", "Show More?", false);
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config){
        return accessor.getStack();
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaHead(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaBody(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){

        if(accessor.getTileEntity() instanceof TileEntityPipe) {
            ((TipList)currenttip).add(((TileEntityPipe) accessor.getTileEntity()).getEnergyContained() + "/" + ((TileEntityPipe) accessor.getTileEntity()).getMaxEnergyContained() + " E-E");
            if(config.getConfig("option.energytools.showMore")){
            }
        }
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ITaggedList.ITipList getWailaTail(ItemStack itemStack, ITaggedList.ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public NBTTagCompound getNBTData(TileEntity te, NBTTagCompound tag, IWailaDataAccessorServer accessor){
        if(te != null){
            te.writeToNBT(tag);
        }
        return tag;
    }

}
