package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Andrew Graber on 10/22/2015.
 */
public class TileEntityEnergyBlock extends TileEntity implements IUpdatePlayerListBox{

    public byte textureNumber;
    public byte rotation;
    public TileEntityEnergyBlock(){
        this.textureNumber = 1;
        this.rotation = 1;
    }

    public void update(){}

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        readSyncableDataFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        writeSyncableDataToNBT(compound);
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setByte("texturenumber", this.textureNumber);
        syncData.setByte("rotation", this.rotation);
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(pos, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readSyncableDataFromNBT(pkt.getNbtCompound());
    }

    private void readSyncableDataFromNBT(NBTTagCompound compound){
        this.textureNumber = compound.getByte("texturenumber");
        this.rotation = compound.getByte("rotation");
    }

    private void writeSyncableDataToNBT(NBTTagCompound compound){
        compound.setByte("texturenumber", this.textureNumber);
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

    public void setCorner(int texnum, int rotate){
        if(texnum == 1) this.textureNumber = 3;
        else if(texnum == 2) this.textureNumber = 5;
        else if(texnum == 3) this.textureNumber = 7;
        else this.textureNumber = 3;
        this.setRotation((byte)rotate);
        worldObj.markBlockForUpdate(pos);
        this.markDirty();
    }

    public void setStraight(int texnum, int rotate){
        if(texnum == 1) this.textureNumber = 2;
        else if(texnum == 2) this.textureNumber = 4;
        else if(texnum == 3) this.textureNumber = 6;
        else this.textureNumber = 1;
        this.setRotation((byte)rotate);
        worldObj.markBlockForUpdate(pos);
        this.markDirty();
    }

}
