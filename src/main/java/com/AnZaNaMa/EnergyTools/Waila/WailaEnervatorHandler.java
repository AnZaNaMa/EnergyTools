package com.AnZaNaMa.EnergyTools.Waila;

import com.AnZaNaMa.EnergyTools.Block.EnergeticPipe;
import com.AnZaNaMa.EnergyTools.Block.Energizer;
import com.AnZaNaMa.EnergyTools.Block.Enervator;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnervator;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.impl.TipList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Optional;

/**
 * Created by Andrew Graber on 5/13/2015.
 */
@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaEnervatorHandler implements IWailaDataProvider {

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register){
        WailaEnervatorHandler enervator = new WailaEnervatorHandler();
        register.registerBodyProvider(enervator, Enervator.class);
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

        if(accessor.getTileEntity() instanceof TileEntityEnervator) {
            ((TipList)currenttip).add(((TileEntityEnervator) accessor.getTileEntity()).getEnergyContained() + "/" + ((TileEntityEnervator) accessor.getTileEntity()).getMaxEnergyContained() + " E-E");
            if(config.getConfig("option.energytools.showMore")){
                ((TipList)currenttip).add("Boosted? " + ((TileEntityEnervator) accessor.getTileEntity()).getIsMultiblock());
                ((TipList)currenttip).add("Boost Size: " + ((TileEntityEnervator) accessor.getTileEntity()).getMultiblockNumber());
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
