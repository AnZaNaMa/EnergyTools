package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class PipeTESR extends TileEntitySpecialRenderer {
    ResourceLocation image = new ResourceLocation(Reference.MODID + ":textures/blocks/energeticpipe.png");
    float pixel = 1F/16F;
    float texpix = 1F/32F;

    public PipeTESR(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        /*
        GL11.glTranslated(x, y, z);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.bindTexture(image);

        TileEntityPipe pipe = (TileEntityPipe)tileEntity;
        if(!pipe.isLongPipe(pipe.getConnections())){
            drawCore();

            for(int i=0; i < pipe.getConnections().length; i++){
                if(pipe.getConnection(i) != null){
                    drawConnection(pipe.getConnection(i));
                }
            }
        }
        else{
            for(int i=0; i < pipe.getConnections().length; i++){
                if(pipe.getConnection(i) != null){
                    drawStraight(pipe.getConnection(i));
                    break;
                }
            }
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-x, -y, -z);
        */
    }
/*
    public void drawCore(){
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getWorldRenderer().startDrawingQuads();

        //Front
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texpix, 5*texpix);

        //Right
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texpix, 5*texpix);

        //Back
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texpix, 5*texpix);

        //Left
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texpix, 5*texpix);

        //Top
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texpix, 5*texpix);

        //Bottom
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texpix, 5*texpix);



        tessellator.draw();
    }

    public void drawStraight(EnumFacing direction){
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getWorldRenderer().startDrawingQuads();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(EnumFacing.NORTH) || direction.equals(EnumFacing.SOUTH)){
            GL11.glRotatef(90F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.EAST) || direction.equals(EnumFacing.WEST)){
            GL11.glRotatef(90F, 0F, 0F, 1F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 26*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 0, 1-11*pixel/2, 10*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texpix, 0*texpix);

        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(EnumFacing.NORTH) || direction.equals(EnumFacing.SOUTH)){
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.EAST) || direction.equals(EnumFacing.WEST)){
            GL11.glRotatef(-90F, 0F, 0F, 1F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    public void drawConnection(EnumFacing direction){
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getWorldRenderer().startDrawingQuads();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(EnumFacing.UP)){
            //Default is UP
        }
        else if(direction.equals(EnumFacing.DOWN)){
            GL11.glRotatef(180F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.NORTH)){
            GL11.glRotatef(270F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.SOUTH)){
            GL11.glRotatef(90F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.EAST)){
            GL11.glRotatef(270F, 0F, 0F, 1F);
        }
        else if(direction.equals(EnumFacing.WEST)){
            GL11.glRotatef(90F, 0F, 0F, 1F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1, 1-11*pixel/2, 10*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 0*texpix);

        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texpix, 5*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texpix, 0*texpix);
        tessellator.getWorldRenderer().addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texpix, 0*texpix);

        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(EnumFacing.UP)){
            //Default is UP
        }
        else if(direction.equals(EnumFacing.DOWN)){
            GL11.glRotatef(-180F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.NORTH)){
            GL11.glRotatef(-270F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.SOUTH)){
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if(direction.equals(EnumFacing.EAST)){
            GL11.glRotatef(-270F, 0F, 0F, 1F);
        }
        else if(direction.equals(EnumFacing.WEST)){
            GL11.glRotatef(-90F, 0F, 0F, 1F);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }
    */
}
