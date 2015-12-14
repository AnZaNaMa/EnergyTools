package com.AnZaNaMa.EnergyTools.api.Tech;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;

/**
 * Created by Andrew Graber on 5/26/2015.
 */
public class PipeSystem {
    private int pipesInSystem, numEnergyProviders, numEnergyAcceptors;
    private TileEntityPipe[] pipes;
    private PowerProvider[] providers;
    private PowerAcceptor[] acceptors;

    public PipeSystem(int numPipes, int numProviders, int numAcceptors, TileEntityPipe[] pipesIn, PowerProvider[] providersIn, PowerAcceptor[] acceptorsIn){
        this.pipesInSystem = numPipes;
        this.numEnergyProviders = numProviders;
        this.numEnergyAcceptors = numAcceptors;
        this.pipes = pipesIn;
        this.providers = providersIn;
        this.acceptors = acceptorsIn;
    }

    public PipeSystem(TileEntityPipe[] pipesIn, PowerProvider[] providersIn, PowerAcceptor[] acceptorsIn){
        this(pipesIn.length, providersIn.length, acceptorsIn.length, pipesIn, providersIn, acceptorsIn);
    }

    public PipeSystem(TileEntityPipe[] pipesIn){
        this(pipesIn.length, 0, 0, pipesIn, null, null);
    }

    public PipeSystem(){
        this(0, 0, 0, null, null, null);
    }

    public void updatePipeSystem(){
        PowerConnectable[] adjacentMachines;
        for(int i=0; i<pipesInSystem; i++){
            if(isInSystem(pipes[i])){
                adjacentMachines = pipes[i].getConnectedMachines();
                for(int j=0; j<adjacentMachines.length; j++){
                    if(adjacentMachines[j].getPipeSystem() == this){
                    } else {
                        adjacentMachines[j].setPipeSystem(this);
                    }
                }
            }
        }
    }

    public boolean isInSystem(PowerConnectable tileEntity){
        return tileEntity.getPipeSystem() == this;
    }
}
