package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import com.AnZaNaMa.EnergyTools.api.DirectionHandler;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerConnectable;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerProvider;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerTransfer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class TileEntityPipe extends PowerConnectable {
    int maxTransferValue = 20;

    public TileEntityPipe(){
        super();
        this.setMaxEnergyContained(1000);
    }

    //called every tick (20 times/second)
    @Override
    public void update(){
        super.update();
        if(!worldObj.isRemote) {
            transferPower();
        }
    }

    //Returns true if the pipe should render as a 'long pipe'
    //This means that it has connections on only two sides AND those sides are opposites.
    public boolean isLongPipe(EnumFacing[] directions){
        EnumFacing mainDirection = null;
        boolean isOpposite = false;

        for(int i=0; i < directions.length; i++){
            if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
            if(directions[i] != null && mainDirection != directions[i]){
                if(!DirectionHandler.isOpposite(mainDirection, directions[i])) return false;
                else isOpposite = true;
            }
        }

        return isOpposite;
    }

    public void transferPower(){
        grabPower();
        sendPower();
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("maxtransfer", this.maxTransferValue);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.maxTransferValue = compound.getInteger("maxtransfer");
    }

    private void grabPower(){
        PowerConnectable[] connectedMachines = this.getConnectedMachines();
        for(int i=0; i<connectedMachines.length; i++){
            if(connectedMachines[i] instanceof PowerProvider && connectedMachines[i].getEnergyContained()>= maxTransferValue && (this.getMaxEnergyContained()-this.getEnergyContained())>= this.maxTransferValue){
                PowerTransfer.transferEnergy(connectedMachines[i], this, maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof PowerProvider && connectedMachines[i].getEnergyContained() < maxTransferValue && connectedMachines[i].getEnergyContained() > 0 && (this.getMaxEnergyContained()-this.getEnergyContained()>=connectedMachines[i].getEnergyContained())) {
                PowerTransfer.transferEnergy(connectedMachines[i], this, connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
        }
    }

    private void sendPower(){
        PowerConnectable[] connectedMachines = this.getConnectedMachines();
        for(int i=0; i<connectedMachines.length; i++){
            if(connectedMachines[i] instanceof PowerAcceptor && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained()) >= this.maxTransferValue && this.getEnergyContained()>=this.maxTransferValue){
                PowerTransfer.transferEnergy(this, connectedMachines[i], this.maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof PowerAcceptor && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained() < this.maxTransferValue) && this.getEnergyContained()>=(connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained())){
                PowerTransfer.transferEnergy(this, connectedMachines[i], connectedMachines[i].getMaxEnergyContained() - connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof TileEntityPipe && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained()) >= this.maxTransferValue && this.getEnergyContained()>=this.maxTransferValue){
                PowerTransfer.transferEnergy(this, connectedMachines[i], this.maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof TileEntityPipe && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained() < this.maxTransferValue) && this.getEnergyContained()>=(connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained())){
                PowerTransfer.transferEnergy(this, connectedMachines[i], connectedMachines[i].getMaxEnergyContained() - connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
        }
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.pos, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){
        this.readFromNBT(packet.getNbtCompound());
    }
}
