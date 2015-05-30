package com.AnZaNaMa.EnergyTools.api;

import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/29/2015.
 */
public class DirectionHandler {
    /*
     * Checks if "direction" and "otherDirection" are opposites. Pairs are:
     * NORTH & SOUTH, EAST & WEST, UP & DOWN
     */
    public static boolean isOpposite(EnumFacing direction, EnumFacing otherDirection){
        if((direction.equals(EnumFacing.NORTH) && otherDirection.equals(EnumFacing.SOUTH)) || (direction.equals(EnumFacing.SOUTH) && otherDirection.equals(EnumFacing.NORTH))) return true;
        if((direction.equals(EnumFacing.EAST) && otherDirection.equals(EnumFacing.WEST)) || (direction.equals(EnumFacing.WEST) && otherDirection.equals(EnumFacing.EAST))) return true;
        if((direction.equals(EnumFacing.UP) && otherDirection.equals(EnumFacing.DOWN)) || (direction.equals(EnumFacing.DOWN) && otherDirection.equals(EnumFacing.UP))) return true;
        else return false;
    }
}
