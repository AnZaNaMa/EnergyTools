package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import com.AnZaNaMa.EnergyTools.api.DirectionHandler;
import com.AnZaNaMa.EnergyTools.api.Tech.PipeSystem;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerConnectable;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerProvider;
import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class TileEntityPipe extends PowerConnectable {
    public static final int maxTransferValue = 10;
    private PowerProvider[] connectedProviders;
    private PowerAcceptor[] connectedAcceptors;

    public TileEntityPipe(){
        super();
        this.maxEnergyContained = 1000;
    }

    //called every tick (20 times/second)
    @Override
    public void update(){
        super.update();
        transferPower();
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

    private void grabPower(){
        for(int i=0; i<connectedMachines.length; i++){
            if(connectedMachines[i] instanceof PowerProvider && connectedMachines[i].getEnergyContained()>= maxTransferValue && (this.getMaxEnergyContained()-this.getEnergyContained())>= this.maxTransferValue){
                transferEnergy(connectedMachines[i], this, maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
                LogHelper.info("Transfering max energy into pipe at: " + this.pos.toString() + " from PowerProvider of type: " + connectedMachines[i].getClass().toString() + " at location: " + connectedMachines[i].getPos().toString());
            }
            else if(connectedMachines[i] instanceof PowerProvider && connectedMachines[i].getEnergyContained() < maxTransferValue && connectedMachines[i].getEnergyContained() > 0 && (this.getMaxEnergyContained()-this.getEnergyContained()>=connectedMachines[i].getEnergyContained())) {
                transferEnergy(connectedMachines[i], this, connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
                LogHelper.info("Transferring " + connectedMachines[i].getEnergyContained() + "energy into pipe at: " + this.pos.toString() + "from PowerProvider of type: " + connectedMachines[i].getClass().toString() + " at location: " + connectedMachines[i].getPos().toString());
            }
            else {
                LogHelper.info("Pipe Transfer Condiditions Not Met...");
                if(connectedMachines[0] != null) {
                    LogHelper.info("First Connected Machine is: " + connectedMachines[0].getClass().toString() + " at: " + connectedMachines[0].getPos().toString());
                    if (connectedMachines[0] instanceof PowerProvider) LogHelper.info("Is PowerProvider");
                    if (connectedMachines[0].getEnergyContained() >= maxTransferValue)
                        LogHelper.info("Machine Can Transfer Max Energy");
                    LogHelper.info("Energy Left In Pipe: " + (this.getMaxEnergyContained() - this.getEnergyContained()));
                    if ((this.getMaxEnergyContained() - this.getEnergyContained()) >= this.maxTransferValue)
                        LogHelper.info("Pipe Has Enough Room For Max Energy");
                } else {
                    LogHelper.info("No Connected Machines");
                }
            }
        }
    }

    private void sendPower(){
        for(int i=0; i<connectedMachines.length; i++){
            if(connectedMachines[i] instanceof PowerAcceptor && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained()) >= this.maxTransferValue){
                transferEnergy(this, connectedMachines[i], this.maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof PowerAcceptor && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained() < this.maxTransferValue)){
                transferEnergy(this, connectedMachines[i], connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof TileEntityPipe && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained()) >= this.maxTransferValue){
                transferEnergy(this, connectedMachines[i], this.maxTransferValue);
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
            else if(connectedMachines[i] instanceof TileEntityPipe && (connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained() < this.maxTransferValue)){
                transferEnergy(this, connectedMachines[i], connectedMachines[i].getMaxEnergyContained()-connectedMachines[i].getEnergyContained());
                this.markDirty();
                connectedMachines[i].markDirty();
                worldObj.markBlockForUpdate(pos);
                worldObj.markBlockForUpdate(connectedMachines[i].getPos());
            }
        }
    }
}
