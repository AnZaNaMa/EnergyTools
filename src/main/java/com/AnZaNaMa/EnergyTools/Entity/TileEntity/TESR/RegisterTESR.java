package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by Andrew Graber on 5/12/2015.
 */
public class RegisterTESR {
    public static void registerModRenderers(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergizer.class, new EnergizerTESR());
    }
}
