package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnervator;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by Andrew Graber on 5/12/2015.
 */
public class RegisterTESR {
    public static void registerModRenderers(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergizer.class, new EnergizerTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnervator.class, new EnervatorTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new PipeTESR());
    }
}
