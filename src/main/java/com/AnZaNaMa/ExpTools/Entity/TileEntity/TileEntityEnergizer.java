package com.AnZaNaMa.ExpTools.Entity.TileEntity;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Energy.Energy;
import com.AnZaNaMa.ExpTools.Utility.LogHelper;
import com.AnZaNaMa.ExpTools.Utility.MultiblockPosition;
import com.AnZaNaMa.ExpTools.Utility.RedstoneHelper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import java.util.List;

/**
 * Created by Andrew Graber on 4/20/2015.
 */
public class TileEntityEnergizer extends TileEntity implements IUpdatePlayerListBox {

    private byte updateTimer;
    private boolean isMultiblock;
    private int energyContained, multiblockSize, multiblockMultiplier;
    private AxisAlignedBB pickupArea;

    public TileEntityEnergizer(){
        super();
        this.updateTimer = 100;
        this.energyContained = 0;
    }

    @Override
    public void update() {
        if (!this.worldObj.isRemote) {
            if(updateTimer >= 100){
                if(completesMultiblock()){
                    this.isMultiblock = true;
                    this.multiblockSize = findMultiblockSize();
                    this.multiblockMultiplier = findMultiplier();
                    this.pickupArea = findPickupArea();
                    worldObj.markBlockForUpdate(pos);
                    this.markDirty();
                }
                else{
                    this.isMultiblock = false;
                    this.multiblockSize = 1;
                    this.multiblockMultiplier = findMultiplier();
                    this.pickupArea = new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX() + 1, this.pos.getY() + 2, this.pos.getZ() + 1);
                    worldObj.markBlockForUpdate(pos);
                    this.markDirty();
                }
                updateTimer = 0;
            }
            if(this.pickupArea == null){
                findPickupArea();
            }
            List entities = this.worldObj.getEntitiesWithinAABB(Entity.class, this.pickupArea, IEntitySelector.selectAnything);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof EntityItem) {
                    ItemStack items = new ItemStack(((EntityItem) entities.get(i)).getEntityItem().getItem(), ((EntityItem) entities.get(i)).getEntityItem().stackSize, ((EntityItem) entities.get(i)).getEntityItem().getMetadata());
                    int energyToMachine = Energy.getItemEnergyValue(items.getItem()) * this.multiblockMultiplier;
                    ((EntityItem) entities.get(i)).setInfinitePickupDelay();
                    this.energyContained += (energyToMachine * ((EntityItem) entities.get(i)).getEntityItem().stackSize);
                    worldObj.markBlockForUpdate(pos);
                    this.markDirty();
                    ((EntityItem) entities.get(i)).getEntityItem().stackSize = 0;
                }
                if (entities.get(i) instanceof EntityPlayer) {
                    if (this.energyContained >= 50*this.multiblockMultiplier && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())) {
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), 50*this.multiblockMultiplier);
                        worldObj.markBlockForUpdate(pos);
                        this.markDirty();
                    }
                    else if(this.energyContained < 50 && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())){
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), this.energyContained);
                        worldObj.markBlockForUpdate(pos);
                        this.markDirty();
                    }
                }
            }
            updateTimer++;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);

        this.energyContained = compound.getInteger("Energy");
        this.isMultiblock = compound.getBoolean("IsMultiblock");
        this.multiblockSize = compound.getInteger("MultiblockSize");
        this.multiblockMultiplier = compound.getInteger("Multiplier");
        this.pickupArea = findPickupArea();
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);

        compound.setInteger("Energy", this.energyContained);
        compound.setInteger("MultiblockSize", this.multiblockSize);
        compound.setInteger("Multiplier", this.multiblockMultiplier);
        compound.setBoolean("IsMultiblock", this.isMultiblock);
    }

    public int getEnergyContained(){
        return this.energyContained;
    }

    public void setEnergyContained(int amount){
        this.energyContained = amount;
    }

    public void addEnergyContained(int amount){
        this.energyContained += amount;
    }

    public void subtractEnergyContained(int amount){
        this.energyContained -= amount;
    }

    public boolean hasEnergy(){
        return this.energyContained > 0 ? true : false;
    }

    private boolean completesLargeMultiblock(){
        boolean stillMultiblock = true;
        for(int x = this.pos.getX() - 3; x < this.pos.getX() + 4; x++){
            for(int z = this.pos.getZ() - 3; z < this.pos.getZ() + 4; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energizer) {
                    } else {
                        stillMultiblock = false;
                        break;
                    }
                }catch(NullPointerException e){
                    stillMultiblock = false;
                    break;
                }
            }
        }
        return stillMultiblock;
    }

    private boolean completesMediumMultiblock(){
        boolean stillMultiblock = true;
        for(int x = this.pos.getX() - 2; x < this.pos.getX() + 3; x++){
            for(int z = this.pos.getZ() - 2; z < this.pos.getZ() + 3; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energizer) {
                    } else {
                        stillMultiblock = false;
                        break;
                    }
                }catch(NullPointerException e){
                    stillMultiblock = false;
                    break;
                }
            }
        }
        return stillMultiblock;
    }

    private boolean completesSmallMultiblock(){
        boolean stillMultiblock = true;
        for(int x = this.pos.getX() - 1; x < this.pos.getX() + 2; x++){
            for(int z = this.pos.getZ() - 1; z < this.pos.getZ() + 2; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.energizer) {
                    } else {
                        stillMultiblock = false;
                        break;
                    }
                }catch(NullPointerException e){
                    stillMultiblock = false;
                    break;
                }
            }
        }
        return stillMultiblock;
    }

    private boolean completesMultiblock(){
        if(completesLargeMultiblock() || completesMediumMultiblock() || completesSmallMultiblock()){
            LogHelper.info("Was Multiblock.");
            return true;
        }
        else{
            LogHelper.info("Was not multiblock.");
            return false;
        }
    }

    private int findMultiblockSize(){
        if(this.completesLargeMultiblock()){
            return 7;
        }
        else if(this.completesMediumMultiblock()) {
            return 5;
        }
        else if(this.completesSmallMultiblock()){
            return 3;
        }
        else{
            return 1;
        }
    }

    public void setIsMultiblock(boolean bool){
        this.isMultiblock = bool;
    }

    public void setMultiblockSize(int size){
        this.multiblockSize = size;
    }

    public boolean getIsMultiblock(){
        return this.isMultiblock;
    }

    public int getMultiblockSize(){
        return this.multiblockSize;
    }

    private AxisAlignedBB findPickupArea(){
        int radius = 1;
        if(this.multiblockSize == 1){
            return new AxisAlignedBB(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ()), new BlockPos(this.pos.getX() + 1, this.pos.getY() + 2, this.pos.getZ() + 1));
        }
        else if(this.multiblockSize == 3){
            radius = 1;
        }
        else if(this.multiblockSize == 5){
            radius = 2;
        }
        else if(this.multiblockSize == 7){
            radius = 3;
        }
        return new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY(), this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + 2, this.pos.getZ() + radius);
    }

    private int findMultiplier(){
        if(this.multiblockSize == 7){
            return 4;
        }
        else if(this.multiblockSize == 5){
            return 3;
        }
        else if(this.multiblockSize == 3){
            return 2;
        }
        else if(this.multiblockSize == 1){
            return 1;
        }
        else{
            return 1;
        }
    }

    public int getMultiplier(){
        return this.multiblockMultiplier;
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(pos, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readFromNBT(pkt.getNbtCompound());
    }
}