package com.AnZaNaMa.ExpTools.Item;

import com.AnZaNaMa.ExpTools.Reference.ItemNames;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ItemExpTools {
    public static Item.ToolMaterial ENERGY = EnumHelper.addToolMaterial("ENERGY", 3, 0, 20.0F, 6.0F, 5);
    public static Item energypick;
    public static Item energyaxe;
    public static Item energyshovel;
    public static Item energysword;
    public static Item energyhoe;
    public static Item infenergyorb;
    public static Item energyingot;
    public static Item redbull;

    public static void addItems(){
        energypick = new EnergyPick(ENERGY, ItemNames.ENERGYPICK);
        energyshovel = new EnergyShovel(ENERGY, ItemNames.ENERGYSHOVEL);
        energyaxe = new EnergyAxe(ENERGY, ItemNames.ENERGYAXE);
        energysword = new EnergySword(ENERGY, ItemNames.ENERGYSWORD);
        infenergyorb = new InfiniteEnergyOrb();
        energyingot = new EnergyIngot(ItemNames.ENERGYINGOT);
        redbull = new RedBull(ItemNames.REDBULL);
    }
}
