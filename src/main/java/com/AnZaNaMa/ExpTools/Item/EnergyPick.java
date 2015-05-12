package com.AnZaNaMa.ExpTools.Item;

import com.AnZaNaMa.ExpTools.EnergyTools;
import com.AnZaNaMa.ExpTools.Reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Andrew Graber on 2/25/2015.
 */
public class EnergyPick extends ItemPickaxe {

    public EnergyPick(ToolMaterial material, String unlocalizedName){
        super(material);
        GameRegistry.registerItem(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
    }
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if(!worldIn.isRemote) {
            if (playerIn.getEntityData().getInteger("Energy") <= 10) {
                playerIn.getEntityData().setInteger("Energy", 0);
                if(playerIn instanceof EntityPlayer){
                    ((EntityPlayer) playerIn).destroyCurrentEquippedItem();
                }
            } else {
                playerIn.getEntityData().setInteger("Energy", playerIn.getEntityData().getInteger("Energy") - 10);
                if (playerIn.getEntityData().getInteger("Energy") < 0) {
                    playerIn.getEntityData().setInteger("Energy", 0);
                }
            }
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
        Reference.PLAYERNAME = player.getName();
        MinecraftServer server = MinecraftServer.getServer();
        EntityPlayerMP playerMP = server.getConfigurationManager().getPlayerByUsername(Reference.PLAYERNAME);
        dataList.add("Player Energy: " + playerMP.getEntityData().getInteger("Energy"));
    }
}
