package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnervator;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 5/18/2015.
 */
public class EnervatorTESR extends TileEntitySpecialRenderer {

    public EnervatorTESR(){

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
        ResourceLocation sideon = new ResourceLocation(Reference.MODID + ":textures/blocks/enervatoron.png");
        ResourceLocation sideoff = new ResourceLocation(Reference.MODID + ":textures/blocks/enervatoroff.png");
        ResourceLocation blankon = new ResourceLocation(Reference.MODID + ":textures/blocks/enervatorblankon.png");
        ResourceLocation blankoff = new ResourceLocation(Reference.MODID + ":textures/blocks/enervatorblankoff.png");

        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GlStateManager.translate(x + 1, y + 1, z);

        try {
            if (tileEntity instanceof TileEntityEnervator){
                TileEntityEnervator enervator = (TileEntityEnervator)tileEntity;
                if(enervator.getIsActive()){
                    this.bindTexture(sideon);
                }
                else{
                    this.bindTexture(sideoff);
                }

                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glDepthMask(true);

                GL11.glRotatef(180.0F, 0.0f, 0.0f, 1.0f);

                tessellator.getWorldRenderer().startDrawingQuads();

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

                //back
                tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 0, 0);
                tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 0, 1);
                tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 1, 1);
                tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 1, 0);

                //right
                tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 0, 0);
                tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 0, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

                tessellator.draw();

                GL11.glPopAttrib();
                GL11.glPopMatrix();

                if(enervator.getIsActive()){
                    this.bindTexture(blankon);
                } else {
                    this.bindTexture(blankoff);
                }

                GL11.glPushMatrix();
                GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
                GlStateManager.translate(x + 1, y + 1, z);

                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

                tessellator.getWorldRenderer().startDrawingQuads();

                //top
                tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 0);
                tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 0);

                //bottom
                tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
                tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 1);
                tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

                tessellator.draw();

                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
        }catch(NullPointerException e){

        }
    }
}
