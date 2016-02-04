package com.AnZaNaMa.EnergyTools.api.Tech;

import com.AnZaNaMa.EnergyTools.Utility.LogHelper;

/**
 * Created by Andrew Graber on 2/3/2016.
 */
public class PowerTransfer {
    public static void addEnergy(PowerConnectable machine, int energy){
        if(machine.energyContained + energy <= machine.getMaxEnergyContained()) {
            machine.energyContained = machine.energyContained + energy;
        }
        else{
            machine.energyContained = machine.getMaxEnergyContained();
        }
    }

    public static void subtractEnergy(PowerConnectable machine, int energy){
        if(machine.energyContained - energy >= 0) {
            machine.energyContained = machine.energyContained - energy;
        }
        else{
            machine.energyContained = 0;
        }
    }

    public static void transferEnergy(PowerConnectable sender, PowerConnectable receiver, int amount){
        addEnergy(receiver, amount);
        subtractEnergy(sender, amount);
    }
}
