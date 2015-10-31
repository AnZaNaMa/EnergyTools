package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.api.DirectionHandler;
import com.AnZaNaMa.EnergyTools.api.Tech.PipeSystem;
import com.AnZaNaMa.EnergyTools.api.Tech.PowerConnectable;
import net.minecraft.util.EnumFacing;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class TileEntityPipe extends PowerConnectable {
    private PipeSystem pipeSystem;

    public TileEntityPipe(){
        if(anyConnectionSystemized()){
            this.pipeSystem = getFirstSystemizedConnection(this.systemizedConnections).getPipeSystem();
        }
        else{
            this.pipeSystem = new PipeSystem(1, 0, 0, new TileEntityPipe[]{this}, null, null);
        }
    }

    //called every tick (20 times/second)
    @Override
    public void update(){
        super.update();
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
