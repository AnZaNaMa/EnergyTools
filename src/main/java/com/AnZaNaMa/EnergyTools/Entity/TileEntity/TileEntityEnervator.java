package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import java.util.List;
import java.util.UUID;

/**
 * Created by Andrew Graber on 5/14/2015.
 */
public class TileEntityEnervator extends PowerAcceptor {

    private static final byte MAX_MULTIBLOCK = 10;
    private static final int MAX_STORAGE = 100000;

    byte counter;
    int multiblockNumber, cloudDistance;
    boolean isActive, isMultiblock;
    UUID actingPlayerUUID;

    public TileEntityEnervator(){
        super();
        this.counter = 50;
        this.isActive = false;
        this.isMultiblock = false;
        this.multiblockNumber = 0;
        this.cloudDistance = 0;
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            if(this.counter >= 50){
                if(!this.isMultiblock && completesMultiblock()){
                    this.multiblockNumber = getMultiblockSize();
                }
            }
            AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY() + this.cloudDistance, pos.getZ()), new BlockPos(pos.getX() + 1, pos.getY() + this.cloudDistance + 2, pos.getZ() + 1));
            List entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
            if(entities.size() <= 0){
                this.isActive = false;
                this.actingPlayerUUID = null;
                worldObj.markBlockForUpdate(pos);
                this.markDirty();
            }
            for (int i = 0; i < entities.size(); i++) {
                if (this.isActive) {
                    break;
                } else {
                    if (entities.get(i) instanceof EntityPlayer) {
                        this.isActive = true;
                        this.actingPlayerUUID = ((EntityPlayer) entities.get(i)).getUniqueID();
                        worldObj.markBlockForUpdate(pos);
                        this.markDirty();
                        break;
                    }
                }
            }
            if(this.isActive){
                if(this.actingPlayerUUID != null){
                    EntityPlayer actingPlayer = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(this.actingPlayerUUID);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        this.multiblockNumber = tag.getInteger("multiblockSize");
        this.isMultiblock = tag.getBoolean("isMultiblock");
        this.isActive = tag.getBoolean("isMovingPlayer");
        this.cloudDistance = tag.getInteger("cloudDistance");
        if(this.isActive) this.actingPlayerUUID = UUID.fromString(tag.getString("actingPlayerUUID"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setInteger("multiblockSize", this.multiblockNumber);
        tag.setBoolean("isMultiblock", this.isMultiblock);
        tag.setBoolean("isMovingPlayer", this.isActive);
        tag.setInteger("cloudDistance", this.cloudDistance);
        if(this.actingPlayerUUID != null) tag.setString("actingPlayerUUID", this.actingPlayerUUID.toString());
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
        if(worldObj.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != BlockEnergyTools.energyblock){
            return 0;
        }
        while(stillMultiblock){
            if(worldObj.getBlockState(new BlockPos(pos.getX(), pos.getY() - (count + 1), pos.getZ())).getBlock() == BlockEnergyTools.energyblock && count < MAX_MULTIBLOCK){
                count++;
            }
            else{
                stillMultiblock = false;
            }
        }
        return count;
    }

    public int getMultiblockNumber(){
        return this.multiblockNumber;
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public String getActingPlayerName(){
        if(this.actingPlayerUUID == null){
            return "None";
        }
        EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(actingPlayerUUID);
        return player.getDisplayNameString();
    }

    public boolean getIsMultiblock() {
        return this.isMultiblock;
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
