package com.AnZaNaMa.EnergyTools.Entity.TileEntity;

import com.AnZaNaMa.EnergyTools.Reference.TileEntityReference;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 4/20/2015.
 */
public class TileEntityEnergyTools {

    public static void init(){
        GameRegistry.registerTileEntity(TileEntityEnergizer.class, TileEntityReference.ENERGIZER);
        GameRegistry.registerTileEntity(TileEntityEnervator.class, TileEntityReference.ENERVATOR);
        GameRegistry.registerTileEntity(TileEntityPipe.class, TileEntityReference.PIPE);
    }

}
