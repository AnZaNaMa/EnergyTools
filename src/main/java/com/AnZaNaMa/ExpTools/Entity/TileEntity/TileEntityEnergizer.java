package com.AnZaNaMa.ExpTools.Entity.TileEntity;

import com.AnZaNaMa.ExpTools.Energy.Energy;
import com.AnZaNaMa.ExpTools.Utility.LogHelper;
import com.AnZaNaMa.ExpTools.Utility.RedstoneHelper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrew Graber on 4/20/2015.
 */
public class TileEntityEnergizer extends TileEntity implements IUpdatePlayerListBox {

    private static int energyContained;
    private static byte repetitions;

    public TileEntityEnergizer(){
        super();
        this.energyContained = 0;
        this.repetitions = 0;
    }

    @Override
    public void update() {
        if (!this.worldObj.isRemote) {
            this.repetitions++;
            List entities = this.worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1)), IEntitySelector.selectAnything);
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof EntityItem && repetitions >= 20) {
                    repetitions = 0;
                    ItemStack items = new ItemStack(((EntityItem) entities.get(i)).getEntityItem().getItem(), ((EntityItem) entities.get(i)).getEntityItem().stackSize, ((EntityItem) entities.get(i)).getEntityItem().getMetadata());
                    int energyToMachine = Energy.getItemEnergyValue(items.getItem());
                    ((EntityItem) entities.get(i)).setInfinitePickupDelay();
                    this.energyContained += (energyToMachine * ((EntityItem) entities.get(i)).getEntityItem().stackSize);
                    this.markDirty();
                    ((EntityItem) entities.get(i)).getEntityItem().stackSize = 0;
                }
                if (entities.get(i) instanceof EntityPlayer) {
                    if (this.energyContained > 0 && RedstoneHelper.isPoweredByRedstone(this.worldObj, this.getPos())) {
                        Energy.tryMoveEnergy(this, (EntityPlayer) entities.get(i), 50);
                        this.markDirty();
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.energyContained = compound.getInteger("Energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("Energy", this.energyContained);
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
}
