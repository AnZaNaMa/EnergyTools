package com.AnZaNaMa.EnergyTools.Client.GUI;

import com.AnZaNaMa.EnergyTools.Reference.Reference;
import com.AnZaNaMa.EnergyTools.Utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.server.management.UserList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by Andrew Graber on 4/12/2015.
 */
public class ModGUIEnergy extends Gui {

    ResourceLocation energybar = new ResourceLocation(Reference.MODID, "textures/gui/energybar.png");

    private Minecraft mc;


    public ModGUIEnergy(Minecraft minecraft){
        super();
        this.mc = minecraft;
    }

    @SubscribeEvent
    public void onGUIRenderEvent(RenderGameOverlayEvent event){

        if(event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }

            EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(Minecraft.getMinecraft().thePlayer.getUniqueID());
            String energyString = Integer.toString(player.getEntityData().getInteger("Energy"));
            int xPos = 10 + (energyString.length() * 16);
            int yPos = 10;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);

            mc.renderEngine.bindTexture(energybar);

            for (int i = 0; i < energyString.length(); i++) {
                drawTexturedModalRect(xPos, yPos, ((player.getEntityData().getInteger("Energy") / (int) Math.pow(10, i)) % 10) * 16, 0, 16, 16);
                xPos -= 16;
            }

            //drawString(mc.fontRendererObj, "Energy: " + Integer.toString(player.getEntityData().getInteger("Energy")), 10, 10, 0xFFffffff);
    }

    private int getXPosForNumber(String number){
        int xPos;
        if(number.equals("")){
            return 0;
        }
        else {
            try {
                xPos = 16 * Integer.parseInt(number);
            } catch (NumberFormatException e) {
                LogHelper.error("Number Format Exception on Rendering Energy");
                LogHelper.error("Energy Number: " + number);
                xPos = 0;
            }
            return xPos;
        }
    }

}
