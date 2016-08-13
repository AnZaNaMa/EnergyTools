package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Utility.MultiblockManager;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Andrew Graber on 4/18/2016.
 */
public class TileEntitySmasher extends PowerAcceptor implements ITickable{
    AxisAlignedBB pickupArea;
    boolean isMultiblock;

    public TileEntitySmasher(){
        this.isMultiblock = isMultiblock();
        this.pickupArea = findPickupArea(this.isMultiblock);
    }

    @Override
    public void update() {
        super.update();
        List<EntityItem> items = checkForItems(this.pickupArea);
        for(int i=0; i<items.size(); i++){
            if(OreDictionary.)
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    private AxisAlignedBB findPickupArea(boolean multiblock){
        AxisAlignedBB area;
        if(multiblock){
            area = new AxisAlignedBB(this.pos.add(-2, -2, -2), this.pos.add(3, 3, 3));
        }
        else{
            area = new AxisAlignedBB(this.pos.add(-1, -1, -1), this.pos.add(2, 2, 2));
        }
        return area;
    }

    private boolean isMultiblock(){
        boolean bool = true;
        for(int i=-2; i<3; i++){
            for(int j=-1; j<2; j++){
                for(int k=-1; k<2; k++){
                    if(worldObj.getTileEntity(new BlockPos(pos.getX()+i, pos.getY()+j, pos.getZ()+k)) instanceof TileEntityEnergyBlock || (i==0 && j==0 && k==0)){}
                    else{
                        bool = false;
                    }
                }
            }
        }
        return bool;
    }

    private List<EntityItem> checkForItems(AxisAlignedBB area){
        List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, area);
        return items;
    }
}
