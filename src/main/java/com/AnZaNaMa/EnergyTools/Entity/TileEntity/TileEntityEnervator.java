package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.api.PowerAcceptor;
import com.AnZaNaMa.EnergyTools.api.PowerConnectable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by Andrew Graber on 5/14/2015.
 */
public class TileEntityEnervator extends PowerAcceptor {

    private static final byte MAX_MULTIBLOCK = 10;
    private static final int MAX_STORAGE = 100000;

    private byte counter;
    private int energyContained, multiblockNumber;
    private boolean isActive, isMultiblock;

    public TileEntityEnervator(){
        super();
        this.counter = 100;
        this.energyContained = 0;
        this.isActive = false;
        this.isMultiblock = false;
        this.multiblockNumber = 0;
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            if(this.counter >= 100){
                if(!this.isMultiblock && completesMultiblock()){
                    this.multiblockNumber = getMultiblockSize();
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        this.energyContained = tag.getInteger("Energy");
        this.multiblockNumber = tag.getInteger("multiblockSize");
        this.isMultiblock = tag.getBoolean("isMultiblock");
        this.isActive = tag.getBoolean("isMovingPlayer");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        tag.setInteger("Energy", this.energyContained);
        tag.setInteger("multiblockSize", this.multiblockNumber);
        tag.setBoolean("isMultiblock", this.isMultiblock);
        tag.setBoolean("isMovingPlayer", this.isActive);
    }

    private boolean completesMultiblock(){
        if(worldObj.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == BlockEnergyTools.energyblock){
            return true;
        }
        else{
            return false;
        }
    }

    private int getMultiblockSize(){
        boolean stillMultiblock = true;
        byte count = 0;
        while(stillMultiblock){
            if(worldObj.getBlockState(new BlockPos(pos.getX(), pos.getY() - (counter + 1), pos.getZ())).getBlock() == BlockEnergyTools.energyblock && count < MAX_MULTIBLOCK){
                count++;
            }
            else{
                stillMultiblock = false;
            }
        }
        return count;
    }

    public int getEnergyContained(){
        return this.energyContained;
    }

    public int getMultiblockNumber(){
        return this.multiblockNumber;
    }

    public boolean getIsActive(){
        return this.isActive;
    }


}
