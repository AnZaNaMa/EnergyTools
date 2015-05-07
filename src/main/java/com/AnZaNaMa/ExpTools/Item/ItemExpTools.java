package com.AnZaNaMa.ExpTools.Item;

import com.AnZaNaMa.ExpTools.Reference.ItemNames;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ItemExpTools {
    public static Item.ToolMaterial EXPERIENCE = EnumHelper.addToolMaterial("EXPERIENCE", 3, 0, 10.0F, 4.0F, 5);
    public static Item XPPick;
    public static Item XPAxe;
    public static Item XPShovel;
    public static Item XPSword;
    public static Item XPHoe;
    public static Item infenergyorb;
    public static Item energyingot;
    public static Item redbull;

    public static void addItems(){
        XPPick = new XPPick(EXPERIENCE, ItemNames.XPPICK);
        XPShovel = new XPShovel(EXPERIENCE, ItemNames.XPSHOVEL);
        XPAxe = new XPAxe(EXPERIENCE, ItemNames.XPAXE);
        infenergyorb = new InfiniteEnergyOrb();
        energyingot = new EnergyIngot(ItemNames.ENERGYINGOT);
        redbull = new RedBull(ItemNames.REDBULL);
    }
}
