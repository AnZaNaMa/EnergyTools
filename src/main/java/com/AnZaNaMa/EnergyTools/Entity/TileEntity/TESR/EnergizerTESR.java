package com.AnZaNaMa.EnergyTools.Entity.TileEntity.TESR;

import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityEnergizer;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.sql.Ref;

/**
 * Created by Andrew Graber on 4/23/2015.
 */
public class EnergizerTESR extends TileEntitySpecialRenderer{
    private float yRotationAngle;
    private float rotationSpeed;

    public EnergizerTESR(){
        yRotationAngle = 0.10F;
        rotationSpeed = 1.0F;
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
/*
        tessellator.getWorldRenderer().begin();

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

        //top
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 0, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 1, 1, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 1, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 1, 0, 1, 0);

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

        //bottom
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 1, 0, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0, 0, 0, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 0, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(1, 0, 1, 1, 0);

        tessellator.draw();

        GL11.glPopAttrib();
        GL11.glPopMatrix();

        ResourceLocation ghost1 = new ResourceLocation(Reference.MODID + ":gfx/energizerghost.png");
        ResourceLocation ghost2 = new ResourceLocation(Reference.MODID + ":gfx/energizerghost2.png");
        ResourceLocation ghost3 = new ResourceLocation(Reference.MODID + ":gfx/energizerghost3.png");
        ResourceLocation ghost4 = new ResourceLocation(Reference.MODID + ":gfx/energizerghost4.png");
        TileEntityEnergizer t = (TileEntityEnergizer)tileEntity;

        switch (t.getMultiblockSize()){
            case 1:
                this.bindTexture(ghost1);
                break;
            case 3:
                this.bindTexture(ghost2);
                break;
            case 5:
                this.bindTexture(ghost3);
                break;
            case 7:
                this.bindTexture(ghost4);
                break;
            default:
                this.bindTexture(ghost1);
        }

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_BLEND);

        if(t.getGhostSpins() > 1 && t.getGhostSpins() < 5){
            rotationSpeed = 50.0F;
        }
        else if(t.getGhostSpins() > 4 && t.getGhostSpins() < 7){
            rotationSpeed = 100.0F;
        }
        else{
            rotationSpeed = 1.0F;
        }

        yRotationAngle += number*rotationSpeed;
        if(yRotationAngle >= 360){
            yRotationAngle -= 360;
            if(t.getGhostSpins() >= 6){
                t.setGhostSpins(0);
            }
            else{
                t.addGhostSpin();
            }
        }

        GlStateManager.translate(x + .5, y + 1, z + .5 );
        GL11.glRotatef(yRotationAngle, 0.0f, 1.0f, 0.0f);

        tessellator.getWorldRenderer().startDrawingQuads();

        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, -0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, -0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, 0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, 0.2, 0, 0);

        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, -0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, -0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, -0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, -0.2, 0, 0);

        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, -0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, 0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, 0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, -0.2, 0, 0);

        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, 0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, 0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, 0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.2, 0.2, 0, 0);

        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, 0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, -0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, -0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.2, 0.2, 0, 0);

        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, 0.2, 0, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, 0.2, 1, 1);
        tessellator.getWorldRenderer().addVertexWithUV(0.2, 0.6, -0.2, 1, 0);
        tessellator.getWorldRenderer().addVertexWithUV(-0.2, 0.6, -0.2, 0, 0);

        tessellator.draw();

        GL11.glPopAttrib();
        GL11.glPopMatrix();
        */
    }
}
