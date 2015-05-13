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
        register(TileEntityEnergizer.class, new EnergizerTESR());
    }

    private static void register(Class tileEntityClass, TileEntitySpecialRenderer renderer){
        if(tileEntityClass.isAssignableFrom(TileEntity.class)) {
            ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, renderer);
        }
    }
}
