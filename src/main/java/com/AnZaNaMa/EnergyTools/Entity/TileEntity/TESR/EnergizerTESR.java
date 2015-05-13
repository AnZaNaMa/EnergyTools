package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 4/23/2015.
 */
public class EnergizerTESR extends TileEntitySpecialRenderer{
    public EnergizerTESR(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        ResourceLocation image1 = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer.png");
        ResourceLocation image2 = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer2.png");
        ResourceLocation image3 = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer3.png");
        ResourceLocation image4 = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer4.png");



        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GlStateManager.translate(x, y, z);

        if(tileEntity instanceof TileEntityEnergizer){
            switch(((TileEntityEnergizer) tileEntity).getMultiblockSize()){
                case 1:
                    this.bindTexture(image1);
                    break;
                case 3:
                    this.bindTexture(image2);
                    break;
                case 5:
                    this.bindTexture(image3);
                    break;
                case 7:
                    this.bindTexture(image4);
                    break;
                default:
                    this.bindTexture(image1);
                    break;
            }
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);

        tessellator.getWorldRenderer().startDrawingQuads();
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 0);

        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 1, 0);

        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 0);

        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        tessellator.draw();

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
