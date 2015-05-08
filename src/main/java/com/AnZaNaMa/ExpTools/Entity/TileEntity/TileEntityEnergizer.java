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
        LogHelper.info("Tile Entity Position: " + this.pos.toString());
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
                    markDirty();
                }
                else{
                    this.isMultiblock = false;
                    this.multiblockSize = 1;
                    this.multiblockMultiplier = findMultiplier();
                    this.pickupArea = new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pos.getX() + 1, this.pos.getY() + 2, this.pos.getZ() + 1);
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
                    this.markDirty();
                    ((EntityItem) entities.get(i)).getEntityItem().stackSize = 0;
                }
                if (entities.get(i) instanceof EntityPlayer) {
                    if (this.energyContained >= 50*this.multiblockMultiplier && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())) {
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), 50*this.multiblockMultiplier);
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
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.xpblock || new BlockPos(x, this.pos.getY(), z) == this.pos) {

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
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.xpblock || new BlockPos(x, this.pos.getY(), z) == this.pos) {

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
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockExpTools.xpblock || new BlockPos(x, this.pos.getY(), z) == this.pos) {

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
            return true;
        }
        else{
            return false;
        }
    }

    private int findMultiblockSize(){
        if(this.completesSmallMultiblock()){
            return 3;
        }
        else if(this.completesMediumMultiblock()){
            return 5;
        }
        else if(this.completesLargeMultiblock()){
            return 7;
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
}