package com.AnZaNaMa.EnergyTools.Reference;

import net.minecraft.nbt.NBTTagCompound;

public class VersioningNBT extends NBTTagCompound {
    public VersioningNBT(){
        this.setString("modDisplayName", Reference.MODNAME);
        this.setString("newVersion", "1.8-1.0b1");
        this.setString("updateUrl", "https://github.com/SomeRandomPerson9/EnergyTools/releases/download/1.8-1.0b1/EnergyTools-1.0.jar");
        this.setBoolean("isDirectLink", true);
    }
}
