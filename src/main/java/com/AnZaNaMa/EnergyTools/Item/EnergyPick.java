package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Energy.EnergyTransfer;
import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
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
public class EnergyPick extends ItemPickaxe implements IEnergyItem{

    public EnergyPick(ToolMaterial material, String unlocalizedName){
        super(material);
        GameRegistry.registerItem(this, unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setUnlocalizedName(unlocalizedName);
    }
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if(playerIn instanceof EntityPlayer) {
            if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 10)) {
                return true;
            }
            else{
                stack.stackSize = 0;
                return false;
            }
        }
        else{
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
        Reference.PLAYERNAME = player.getName();
        MinecraftServer server = MinecraftServer.getServer();
        EntityPlayerMP playerMP = server.getConfigurationManager().getPlayerByUsername(Reference.PLAYERNAME);
        dataList.add("Player Energy: " + playerMP.getEntityData().getInteger("Energy"));
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }
}
