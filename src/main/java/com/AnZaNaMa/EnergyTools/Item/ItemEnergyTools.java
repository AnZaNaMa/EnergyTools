package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Reference.ItemNames;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEnergyTools {
    public static Item.ToolMaterial ENERGY = EnumHelper.addToolMaterial("ENERGY", 3, 0, 20.0F, 6.0F, 5);
    public static Item energypick;
    public static Item energyaxe;
    public static Item energyshovel;
    public static Item energysword;
    public static Item energyhoe;
    public static Item infenergyorb;
    public static Item energyingot;
    public static Item redbull;
    public static Item coalstick;

    public static void addItems(){
        energypick = new EnergyPick(ENERGY, ItemNames.ENERGYPICK);
        energyshovel = new EnergyShovel(ENERGY, ItemNames.ENERGYSHOVEL);
        energyaxe = new EnergyAxe(ENERGY, ItemNames.ENERGYAXE);
        energysword = new EnergySword(ENERGY, ItemNames.ENERGYSWORD);
        energyhoe = new EnergyHoe(ENERGY, ItemNames.ENERGYHOE);
        infenergyorb = new InfiniteEnergyOrb(ItemNames.INFENERGYORB);
        energyingot = new EnergyIngot(ItemNames.ENERGYINGOT);
        redbull = new RedBull(ItemNames.REDBULL);
        coalstick = new CoalStick(ItemNames.COALSTICK);
    }
}
