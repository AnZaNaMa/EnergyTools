package com.AnZaNaMa.EnergyTools.Waila;

import com.AnZaNaMa.EnergyTools.Block.EnergeticPipe;
import com.AnZaNaMa.EnergyTools.Block.Energizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
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
public class WailaEnergizerHandler implements IWailaDataProvider {

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register){
        WailaEnergizerHandler energizer = new WailaEnergizerHandler();
        register.registerBodyProvider(energizer, Energizer.class);
        WailaPipeHandler pipe = new WailaPipeHandler();
        register.registerBodyProvider(pipe, EnergeticPipe.class);
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

        if(accessor.getTileEntity() instanceof TileEntityEnergizer) {
            ((TipList)currenttip).add("Energy: " + accessor.getNBTData().getInteger("Energy"));
            if(config.getConfig("option.energytools.showMore")){
                ((TipList)currenttip).add("Multiblock: " + ((TileEntityEnergizer) accessor.getTileEntity()).getIsMultiblock());
                ((TipList)currenttip).add("Multiblock Size: " + ((TileEntityEnergizer) accessor.getTileEntity()).getMultiblockSize());
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
