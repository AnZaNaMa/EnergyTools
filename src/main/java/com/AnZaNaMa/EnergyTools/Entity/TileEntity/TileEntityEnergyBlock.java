package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Andrew Graber on 10/22/2015.
 */
public class TileEntityEnergyBlock extends TileEntity implements IUpdatePlayerListBox{

    public static byte textureNumber;
    public static byte rotation;
    public TileEntityEnergyBlock(){
        this.textureNumber = 1;
        this.rotation = 1;
    }

    public void update(){}

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.textureNumber = compound.getByte("texturenumber");
        this.rotation = compound.getByte("rotation");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setByte("texutrenumber", this.textureNumber);
        compound.setByte("rotation", this.rotation);
    }

    public void setTextureNumber(byte b){
        this.textureNumber = b;
    }

    public byte getTextureNumber(){
        return this.textureNumber;
    }

    public void setRotation(byte b){
        this.rotation = b;
    }

    public byte getRotation(){
        return this.rotation;
    }

}