package com.AnZaNaMa.ExpTools.Handler;

import com.AnZaNaMa.ExpTools.Block.BlockExpTools;
import com.AnZaNaMa.ExpTools.Item.ItemExpTools;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by andre_000 on 4/19/2015.
 */
public class SmeltingHandler {
    public static void init(){
        GameRegistry.addSmelting(BlockExpTools.energyore, new ItemStack(ItemExpTools.energyingot), 0F);
    }
}
