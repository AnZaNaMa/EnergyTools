package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Andrew Graber on 5/12/2015.
 */
public class RenderItemRegister {
    public static void registerItemIcons(){
        registerItem(ItemEnergyTools.energyaxe, 0);
        registerItem(ItemEnergyTools.energypick, 0);
        registerItem(ItemEnergyTools.energyshovel, 0);
        registerItem(ItemEnergyTools.energysword, 0);
        registerItem(ItemEnergyTools.energyhoe, 0);

        registerItem(ItemEnergyTools.infenergyorb, 0);
        registerItem(ItemEnergyTools.energyingot, 0);
        registerItem(ItemEnergyTools.redbull, 0);
        registerItem(ItemEnergyTools.coalstick, 0);
    }

    public static void registerItem(Item item, int meta){
        if(item instanceof IEnergyItem) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + ((IEnergyItem) item).getName(), "inventory"));
        }
    }
}
