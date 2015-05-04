package com.AnZaNaMa.ExpTools.Entity.TileEntity;

import com.AnZaNaMa.ExpTools.Reference.TileEntityReference;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 4/20/2015.
 */
public class TileEntityEnergyTools {

    public static void init(){
        GameRegistry.registerTileEntity(TileEntityEnergizer.class, TileEntityReference.ENERGIZER);
    }

}
