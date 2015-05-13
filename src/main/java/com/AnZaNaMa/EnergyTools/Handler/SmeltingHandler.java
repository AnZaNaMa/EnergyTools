package com.AnZaNaMa.EnergyTools.Handler;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import com.AnZaNaMa.EnergyTools.Item.ItemExpTools;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class SmeltingHandler {
    public static void init(){
        GameRegistry.addSmelting(BlockEnergyTools.energyore, new ItemStack(ItemExpTools.energyingot), 0F);
    }
}
