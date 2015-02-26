package com.AnZaNaMa.ExpTools.Proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Andrew Graber on 2/24/2015.
 */
public interface IProxy {
    public void preInit(FMLPreInitializationEvent eventt);
    public void init(FMLInitializationEvent event);
    public void postInit(FMLPostInitializationEvent event);
}
