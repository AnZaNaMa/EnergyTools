package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Reference.Reference;
import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Andrew Graber on 5/12/2015.
 */
public class RenderItemRegister {
    public static void registerItemIcons(){
        registerItem(ItemExpTools.energyaxe, 0);
        registerItem(ItemExpTools.energypick, 0);
        registerItem(ItemExpTools.energyshovel, 0);
        registerItem(ItemExpTools.energysword, 0);
        registerItem(ItemExpTools.energyhoe, 0);

        registerItem(ItemExpTools.infenergyorb, 0);
        registerItem(ItemExpTools.energyingot, 0);
        registerItem(ItemExpTools.redbull, 0);
    }

    public static void registerItem(Item item, int meta){
        if(item instanceof IEnergyItem) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + ((IEnergyItem) item).getName(), "inventory"));
        }
    }
}
