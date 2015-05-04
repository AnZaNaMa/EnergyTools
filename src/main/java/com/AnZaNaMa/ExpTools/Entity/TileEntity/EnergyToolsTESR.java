package com.AnZaNaMa.ExpTools.Entity.TileEntity;

import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 4/23/2015.
 */
public class EnergyToolsTESR extends TileEntitySpecialRenderer{
    public EnergyToolsTESR(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        ResourceLocation image = new ResourceLocation(Reference.MODID + ":textures/blocks/energizer.png");
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GlStateManager.translate(x, y, z);
        this.bindTexture(image);
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
