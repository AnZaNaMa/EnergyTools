package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.Energy.Energy;
import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import com.AnZaNaMa.EnergyTools.Utility.RedstoneHelper;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import java.util.List;

/**
 * Created by Andrew Graber on 4/20/2015.
 */
public class TileEntityEnergizer extends PowerProvider {

    byte updateTimer;
    boolean isMultiblock;
    int multiblockSize, multiblockMultiplier;
    AxisAlignedBB pickupArea;
    int ghostSpins;

    public TileEntityEnergizer(){
        super();
        this.updateTimer = 100;
        this.ghostSpins = 0;
    }

    @Override
    public void update() {
        if (!this.worldObj.isRemote) {
            if(updateTimer >= 100){
                if(completesMultiblock()){
                    this.makeMultiblock();
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
            List<Entity> entities = this.worldObj.getEntitiesWithinAABB(Entity.class, this.pickupArea);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof EntityItem) {
                    ItemStack items = new ItemStack(((EntityItem) entities.get(i)).getEntityItem().getItem(), ((EntityItem) entities.get(i)).getEntityItem().stackSize, ((EntityItem) entities.get(i)).getEntityItem().getMetadata());
                    int energyToMachine = Energy.getItemEnergyValue(items.getItem()) * this.multiblockMultiplier;
                    ((EntityItem) entities.get(i)).setInfinitePickupDelay();
                    this.setEnergyContained(this.getEnergyContained() + (energyToMachine * ((EntityItem) entities.get(i)).getEntityItem().stackSize));
                    worldObj.markBlockForUpdate(pos);
                    this.markDirty();
                    ((EntityItem) entities.get(i)).getEntityItem().stackSize = 0;
                }
                if (entities.get(i) instanceof EntityPlayer) {
                    if (this.getEnergyContained() >= 50*this.multiblockMultiplier && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())) {
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), 50*this.multiblockMultiplier);
                        worldObj.markBlockForUpdate(pos);
                        this.markDirty();
                    }
                    else if(this.getEnergyContained() < 50 && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())){
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), this.getEnergyContained());
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
        this.isMultiblock = compound.getBoolean("IsMultiblock");
        this.multiblockSize = compound.getInteger("MultiblockSize");
        this.multiblockMultiplier = compound.getInteger("Multiplier");
        this.pickupArea = findPickupArea();
        this.ghostSpins = 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("MultiblockSize", this.multiblockSize);
        compound.setInteger("Multiplier", this.multiblockMultiplier);
        compound.setBoolean("IsMultiblock", this.isMultiblock);
    }

    public boolean hasEnergy(){
        return this.getEnergyContained() > 0;
    }

    private boolean completesLargeMultiblock(){
        boolean stillMultiblock = true; //hi daddy
        int energizersInZone = 0;
        for(int x = this.pos.getX() - 3; x < this.pos.getX() + 4; x++){
            for(int z = this.pos.getZ() - 3; z < this.pos.getZ() + 4; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer && energizersInZone == 0) {
                        if(worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer){
                            energizersInZone++;
                        }
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
        int energizersInZone = 0;
        for(int x = this.pos.getX() - 2; x < this.pos.getX() + 3; x++){
            for(int z = this.pos.getZ() - 2; z < this.pos.getZ() + 3; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer && energizersInZone == 0) {
                        if(worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer){
                            energizersInZone++;
                        }
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
        int energizersInZone = 0;
        for(int x = this.pos.getX() - 1; x < this.pos.getX() + 2; x++){
            for(int z = this.pos.getZ() - 1; z < this.pos.getZ() + 2; z++){
                try {
                    if (worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energyblock || worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer && energizersInZone == 0) {
                        if(worldObj.getBlockState(new BlockPos(x, this.pos.getY(), z)).getBlock() == BlockEnergyTools.energizer){
                            energizersInZone++;
                        }
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

    public void makeMultiblock(){
        this.isMultiblock = true;
        this.multiblockSize = findMultiblockSize();
        this.multiblockMultiplier = findMultiplier();
        this.pickupArea = findPickupArea();
        this.setSlaveBlocks();
        worldObj.markBlockForUpdate(pos);
        this.markDirty();
    }

    public void setSlaveBlocks(){
        int texNum;
        switch(this.multiblockSize) {
            case 3:
                texNum = 1;
                break;
            case 5:
                texNum = 2;
                break;
            case 7:
                texNum = 3;
                break;
            default:
                texNum = 1;
        }
        if(this.multiblockSize >= 3){
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+1))).setCorner(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-1))).setCorner(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+1))).setCorner(texNum, 3);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-1))).setCorner(texNum, 4);
        }
        if(this.multiblockSize >=5){
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()+1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()+2))).setCorner(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()-1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()-2))).setCorner(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()+1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()+2))).setCorner(texNum, 3);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()-1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()-2))).setCorner(texNum, 4);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+2))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+2))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-2))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-2))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+2))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-2))).setStraight(texNum, 1);
        }
        if(this.multiblockSize == 7){
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()+1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()+2))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()+3))).setCorner(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()-1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()-2))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()-3))).setCorner(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()+1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()+2))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()+3))).setCorner(texNum, 3);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()-1))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()-2))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()-3))).setCorner(texNum, 4);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()))).setStraight(texNum, 2);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()+3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()+3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()+3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()+3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()-3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()+2, pos.getY(), pos.getZ()-3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()-3))).setStraight(texNum, 1);
            ((TileEntityEnergyBlock)worldObj.getTileEntity(new BlockPos(pos.getX()-2, pos.getY(), pos.getZ()-3))).setStraight(texNum, 1);
        }
        worldObj.markBlockRangeForRenderUpdate(new BlockPos(pos.getX()-3, pos.getY(), pos.getZ()-3), new BlockPos(pos.getX()+3, pos.getY(), pos.getZ()+3));
    }

    public int getGhostSpins(){
        return this.ghostSpins;
    }

    public void setGhostSpins(int i){
        this.ghostSpins = i;
    }

    public void addGhostSpin(){
        this.ghostSpins++;
    }
}