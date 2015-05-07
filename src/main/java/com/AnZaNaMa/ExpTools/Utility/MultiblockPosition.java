package com.AnZaNaMa.ExpTools.Utility;

/**
 * Created by Andrew Graber on 5/5/2015.
 */
public class MultiblockPosition {
    private int xpos, ypos, zpos;
    public MultiblockPosition(int x, int y, int z){
        this.xpos = x;
        this.ypos = y;
        this.zpos = z;
    }

    public int posX(){
        return this.xpos;
    }

    public int posY(){
        return this.ypos;
    }

    public int posZ(){
        return this.zpos;
    }
}
