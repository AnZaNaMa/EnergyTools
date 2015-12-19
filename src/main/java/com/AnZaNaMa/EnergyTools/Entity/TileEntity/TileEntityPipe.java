package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.api.DirectionHandler;
import com.AnZaNaMa.EnergyTools.api.Tech.PipeSystem;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerAcceptor;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerConnectable;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerProvider;
import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class TileEntityPipe extends PowerConnectable {
    private PipeSystem pipeSystem;
    private PowerProvider[] connectedProviders;
    private PowerAcceptor[] connectedAcceptors;

    public TileEntityPipe(){
        super();

    }

    //called every tick (20 times/second)
    @Override
    public void update(){
        super.update();
        getConnectedMachines();

    }

    //Returns true if the pipe should render as a 'long pipe'
    //This means that it has connections on only two sides AND those sides are opposites.
    public boolean isLongPipe(EnumFacing[] directions){
        EnumFacing mainDirection = null;
        boolean isOpposite = false;

        for(int i=0; i < directions.length; i++){
            if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
            if(directions[i] != null && mainDirection != directions[i]){
                if(!DirectionHandler.isOpposite(mainDirection, directions[i])) return false;
                else isOpposite = true;
            }
        }

        return isOpposite;
    }
}
