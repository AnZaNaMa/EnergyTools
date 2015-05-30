package com.AnZaNaMa.EnergyTools.api;

/**
 * Created by Andrew Graber on 5/29/2015.
 */
public class PowerProvider extends PowerConnectable{
    protected int energyContained;

    public PowerProvider(){
        this.energyContained = 0;
    }
}
