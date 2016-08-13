package com.AnZaNaMa.EnergyTools.Utility;

import com.AnZaNaMa.EnergyTools.api.Tech.PowerConnectable;

/**
 * Created by Andrew Graber on 4/18/2016.
 */
public class MultiblockManager {
    int spanX, spanY, spanZ;
    public MultiblockManager(int XSpan, int YSpan, int ZSpan){
        this.spanX = XSpan;
        this.spanY = YSpan;
        this.spanZ = ZSpan;
    }

    public void setSpan(int xIn, int yIn, int zIn){
        this.spanX = xIn;
        this.spanY = yIn;
        this.spanZ = zIn;
    }

    public void setSpanX(int xIn){
        this.spanX = xIn;
    }

    public void setSpanY(int yIn){
        this.spanY = yIn;
    }

    public void setSpanZ(int zIn){
        this.spanZ = zIn;
    }

    public int getSpanX(){
        return this.spanX;
    }

    public int getSpanY(){
        return this.spanY;
    }

    public int getSpanZ(){
        return this.spanZ;
    }
}
