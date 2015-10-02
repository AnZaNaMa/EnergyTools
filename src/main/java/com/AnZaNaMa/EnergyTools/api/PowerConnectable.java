package com.AnZaNaMa.EnergyTools.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/21/2015.
 */
public class PowerConnectable extends TileEntity implements IUpdatePlayerListBox{
    protected PowerConnectable[] systemizedConnections;
    protected EnumFacing[] connections = new EnumFacing[6];
    protected PipeSystem pipeSystem;

    public PowerConnectable(){
        if(anyConnectionSystemized()){
            this.pipeSystem = getFirstSystemizedConnection(this.systemizedConnections).getPipeSystem();
        }
        else{
            this.pipeSystem = new PipeSystem();
        }
    }

    //called every tick (20 times/second)
    @Override
    public void update(){
        this.updateConnections();
    }

    public void findSystemizedConnections(EnumFacing[] connections){
        this.systemizedConnections = new PowerConnectable[6];
        if(connections[0] != null && worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) instanceof PowerConnectable){
            this.systemizedConnections[0] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
        } else { this.systemizedConnections[0] = null;}
        if(connections[1] != null && worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) instanceof PowerConnectable){
            this.systemizedConnections[1] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
        } else { this.systemizedConnections[1] = null;}
        if(connections[2] != null && worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)) instanceof PowerConnectable){
            this.systemizedConnections[2] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
        } else { this.systemizedConnections[2] = null;}
        if(connections[3] != null && worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)) instanceof PowerConnectable){
            this.systemizedConnections[3] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
        } else { this.systemizedConnections[3] = null;}
        if(connections[4] != null && worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())) instanceof PowerConnectable){
            this.systemizedConnections[4] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
        } else { this.systemizedConnections[4] = null;}
        if(connections[5] != null && worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())) instanceof PowerConnectable){
            this.systemizedConnections[5] = (PowerConnectable)worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
        } else { this.systemizedConnections[5] = null;}
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){

    }

    @Override
    public void readFromNBT(NBTTagCompound tag){

    }

    public PowerConnectable getFirstSystemizedConnection(PowerConnectable[] sysconnections){
        PowerConnectable connection = null;
        if(anyConnectionSystemized()){
            for(int i=0; i < sysconnections.length; i++){
                if(sysconnections[i] != null) connection = sysconnections[i];
            }
            return connection;
        } else {
            return null;
        }
    }

    //Checks all objects in this.systemizedConnections and returns true if any of them are not null
    public boolean anyConnectionSystemized(){
        if(this.systemizedConnections == null){
            findSystemizedConnections(this.connections);
        }
        boolean anySystemized = false;
        for(int i=0; i < this.systemizedConnections.length; i++){
            try{
                if(this.systemizedConnections[i] != null){
                    anySystemized = true;
                    break;
                }
                else{}
            } catch(NullPointerException e){}
        }
        return anySystemized;
    }

    public PipeSystem getPipeSystem() {
        return this.pipeSystem;
    }

    public void setPipeSystem(PipeSystem system){
        this.pipeSystem = system;
    }

    //Checks to see if the TileEntities in all directions are compatible to connect to the pipe.
    //Only passes if the TileEntity implements IPowerConnectable
    public void updateConnections(){
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ())) instanceof PowerConnectable) connections[0] = EnumFacing.UP;
        else connections[0] = null;
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())) instanceof PowerConnectable) connections[1] = EnumFacing.DOWN;
        else connections[1] = null;
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)) instanceof PowerConnectable) connections[2] = EnumFacing.NORTH;
        else connections[2] = null;
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)) instanceof PowerConnectable) connections[3] = EnumFacing.SOUTH;
        else connections[3] = null;
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())) instanceof PowerConnectable) connections[4] = EnumFacing.EAST;
        else connections[4] = null;
        if(this.worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())) instanceof PowerConnectable) connections[5] = EnumFacing.WEST;
        else connections[5] = null;
    }

    //Returns an EnumFacing[] containing all of the connections to the pipe. The value will always be either an EnumDirection or null.
    //It is important that the order of the directions in the array must not be changed. If it is, the pipe will not work correctly.
    public EnumFacing[] getConnections(){
        return this.connections;
    }

    /*
     * @Param "directions" (EnumFacing[]): The array to replace this.connections with.
     * IMPORTANT: Must be an EnumFacing[6] containing each of the 6 directions in this order:
     * UP, DOWN, NORTH, SOUTH, EAST, WEST.
     * To Remove a connection from this, set it to null.
     * Make sure that you have 6 objects inside of this array in that order.
     */
    public void setConnections(EnumFacing[] directions){
        this.connections = directions;
    }

    /*
     * @Param "number" (int): The position in the EnumFacing[] this.connections
     * 0 = EnumFacing.UP
     * 1 = EnumFacing.DOWN
     * 2 = EnumFacing.NORTH
     * 3 = EnumFacing.SOUTH
     * 4 = EnumFacing.EAST
     * 5 = EnumFacing.WEST
     * > 5 will return IndexOutOfBounds error, because it's an EnumFacing[6].
     *
     * @Param "direction" (EnumFacing): The Direction you are changing.
     * Can be EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST
     * If you want to remove a connection from the array, SET THIS PARAMETER TO NULL
     */
    public void changeConnection(int number, EnumFacing direction){
        this.connections[number] = direction;
    }

    //Returns an object telling if there is a connected TileEntity in a certain direction.
    //Will either be null or a direction respective to the index provided. Check above comment for list of values.
    public EnumFacing getConnection(int number){
        return this.connections[number];
    }
}