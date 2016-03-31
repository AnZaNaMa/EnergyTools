package com.AnZaNaMa.EnergyTools.Waila;

import com.AnZaNaMa.EnergyTools.Block.EnergeticPipe;
import com.AnZaNaMa.EnergyTools.Block.Energizer;
import com.AnZaNaMa.EnergyTools.Block.Enervator;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.impl.TipList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.List;

/**
 * Created by Andrew Graber on 5/13/2015.
 */

@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaEnergizerHandler implements IWailaDataProvider{

    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register){
        WailaEnergizerHandler instance = new WailaEnergizerHandler();
        register.registerBodyProvider(instance, TileEntityEnergizer.class);
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config){
        return accessor.getStack();
    }

    @Override
    @Optional.Method(modid = "Waila")
<<<<<<< HEAD
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){
=======
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
>>>>>>> 72019497d908b898063556123e684760939138b7
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
<<<<<<< HEAD
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){

        if(accessor.getTileEntity() instanceof TileEntityEnergizer) {
            ((TipList)currenttip).add(((TileEntityEnergizer) accessor.getTileEntity()).getEnergyContained() + "/" + ((TileEntityEnergizer) accessor.getTileEntity()).getMaxEnergyContained() + " E-E");
            if(config.getConfig("option.energytools.showMore")){
                ((TipList)currenttip).add("Multiblock: " + ((TileEntityEnergizer) accessor.getTileEntity()).getIsMultiblock());
                ((TipList)currenttip).add("Multiblock Size: " + ((TileEntityEnergizer) accessor.getTileEntity()).getMultiblockSize());
            }
=======
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof TileEntityEnergizer){
            currenttip.add(((TileEntityEnergizer) accessor.getTileEntity()).getEnergyContained() + "/" + ((TileEntityEnergizer) accessor.getTileEntity()).getMaxEnergyContained() + " E-E");
>>>>>>> 72019497d908b898063556123e684760939138b7
        }
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
<<<<<<< HEAD
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){
=======
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
>>>>>>> 72019497d908b898063556123e684760939138b7
        return currenttip;
    }

    @Override
    @Optional.Method(modid = "Waila")
<<<<<<< HEAD
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos){
        if(te != null){
            te.writeToNBT(tag);
        }
=======
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
>>>>>>> 72019497d908b898063556123e684760939138b7
        return tag;
    }
}
