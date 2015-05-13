package com.AnZaNaMa.EnergyTools.Utility;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Andrew Graber on 4/30/2015.
 */
public class RedstoneHelper {
    public static boolean isPoweredByRedstone(World world, BlockPos pos){
        boolean powered = false;
        if(world.getRedstonePower(pos, EnumFacing.NORTH) > 0){
            powered = true;
        }
        else if(world.getRedstonePower(pos, EnumFacing.EAST) > 0){
            powered = true;
        }
        else if(world.getRedstonePower(pos, EnumFacing.SOUTH) > 0){
            powered = true;
        }
        else if(world.getRedstonePower(pos, EnumFacing.WEST) > 0){
            powered = true;
        }
        else if(world.getRedstonePower(pos, EnumFacing.UP) > 0){
            powered = true;
        }
        else if(world.getRedstonePower(pos, EnumFacing.DOWN) > 0){
            powered = true;
        }
        return powered ? true : false;
    }
}
