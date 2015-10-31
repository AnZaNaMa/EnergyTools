package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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

    private byte counter;
    private int energyContained, multiblockNumber, cloudDistance;
    private boolean isActive, isMultiblock;
    private UUID actingPlayerUUID;

    public TileEntityEnervator(){
        super();
        this.counter = 100;
        this.energyContained = 0;
        this.isActive = false;
        this.isMultiblock = false;
        this.multiblockNumber = 0;
        this.cloudDistance = 0;
    }

    @Override
    public void update(){
        if(!this.worldObj.isRemote){
            if(this.counter >= 100){
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
        this.energyContained = tag.getInteger("Energy");
        this.multiblockNumber = tag.getInteger("multiblockSize");
        this.isMultiblock = tag.getBoolean("isMultiblock");
        this.isActive = tag.getBoolean("isMovingPlayer");
        this.cloudDistance = tag.getInteger("cloudDistance");
        this.actingPlayerUUID = UUID.fromString(tag.getString("actingPlayerUUID"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        tag.setInteger("Energy", this.energyContained);
        tag.setInteger("multiblockSize", this.multiblockNumber);
        tag.setBoolean("isMultiblock", this.isMultiblock);
        tag.setBoolean("isMovingPlayer", this.isActive);
        tag.setInteger("cloudDistance", this.cloudDistance);
        tag.setString("actingPlayerUUID", this.actingPlayerUUID.toString());
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





    public int getEnergyContained(){
        return this.energyContained;
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


}
