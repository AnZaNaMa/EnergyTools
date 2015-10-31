package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergyBlock;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 10/22/2015.
 */
public class EnergyBlockTESR extends TileEntitySpecialRenderer{
    private boolean isCorner = false;

    public EnergyBlockTESR(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        ResourceLocation image;
        byte rotation = ((TileEntityEnergyBlock)tileEntity).getRotation();
        switch(((TileEntityEnergyBlock)tileEntity).getTextureNumber()){
            case 1:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energyblock.png");
                break;
            case 2:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer2mid.png");
                break;
            case 3:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer2corner.png");
                isCorner = true;
                break;
            case 4:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer3mid.png");
                break;
            case 5:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer3corner.png");
                isCorner = true;
                break;
            case 6:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer4mid.png");
                break;
            case 7:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer4corner.png");
                isCorner = true;
                break;
            default:
                image = new ResourceLocation(Reference.MODID + ":textures/blocks/energyblock.png");
                break;
        }

        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GlStateManager.translate(x, y, z);
        this.bindTexture(image);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);

        tessellator.getWorldRenderer().startDrawingQuads();

        switch(rotation){
            case 1:
                renderRotation1(tessellator);
                break;
            case 2:
                renderRotation2(tessellator);
                break;
            case 3:
                renderRotation3(tessellator);
                break;
            case 4:
                renderRotation4(tessellator);
                break;
            default:
                renderRotation1(tessellator);
        }

        tessellator.draw();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void renderRotation1(Tessellator tessellator){

        //front
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);

        //left
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 0);

        //right
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        //back
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

        //top
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 0);

        //bottom
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);
    }

    public static void renderRotation2(Tessellator tessellator){
        //front
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);

        //left
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 0);

        //right
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        //back
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

        //top
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 0);

        //bottom
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
    }

    public static void renderRotation3(Tessellator tessellator){
        //front
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);

        //left
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 0);

        //right
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        //back
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

        //top
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);

        //bottom
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 1);
    }

    public static void renderRotation4(Tessellator tessellator){
        //front
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);

        //left
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 0);

        //right
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        //back
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

        //top
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);

        //bottom
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);
    }
}
