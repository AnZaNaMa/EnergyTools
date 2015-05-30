package com.AnZaNaMa.EnergyTools.Item;

import com.AnZaNaMa.EnergyTools.Energy.Energy;
import com.AnZaNaMa.EnergyTools.Energy.EnergyTransfer;
import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Reference.Reference;
import com.typesafe.config.ConfigException;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
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
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
        try {
            if (playerIn instanceof EntityPlayer) {
                if (stack != null) {
                    if (stack.getItem() instanceof EnergyPick) {
                        if (stack.getTagCompound().getBoolean("Empowered")) {
                            int pitch = (int) (((EntityPlayer) playerIn).rotationPitch);
                            int yaw = (int) (((EntityPlayer) playerIn).rotationYawHead);
                            String direction = "";
                            if (pitch < -45) {
                                direction = "up";
                            } else if (pitch > 45) {
                                direction = "down";
                            } else if (yaw >= 135 || yaw <= -135) {
                                direction = "north";
                            } else if (yaw > -135 && yaw < -45) {
                                direction = "east";
                            } else if (yaw >= -45 && yaw <= 45) {
                                direction = "south";
                            } else if (yaw > 45 && yaw < 135) {
                                direction = "west";
                            }

                            if (direction == "up" || direction == "down") {
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), pos, stack);
                                if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 100)) {
                                    return true;
                                } else {
                                    stack.stackSize = 0;
                                    return false;
                                }
                            } else if (direction == "north" || direction == "south") {
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()), pos, stack);
                                breakBlock(worldIn, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ()), pos, stack);
                                if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 100)) {
                                    return true;
                                } else {
                                    stack.stackSize = 0;
                                    return false;
                                }
                            } else if (direction == "east" || direction == "west") {
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1), pos, stack);
                            breakBlock(worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1), pos, stack);
                            if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 100)) {
                                return true;
                            } else {
                                stack.stackSize = 0;
                                return false;
                            }
                        }

                        } else if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 10)) {
                            return true;
                        } else {
                            stack.stackSize = 0;
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        } catch (NullPointerException e){
            if (EnergyTransfer.subtractEnergy(worldIn, (EntityPlayer) playerIn, 10)) {
                return true;
            } else {
                stack.stackSize = 0;
                return false;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
        Reference.PLAYERNAME = player.getName();
        MinecraftServer server = MinecraftServer.getServer();
        EntityPlayerMP playerMP = server.getConfigurationManager().getPlayerByUsername(Reference.PLAYERNAME);
        dataList.add("Player Energy: " + player.getEntityData().getInteger("Energy"));
        if(player.getEntityData().getBoolean("Empowered")){
            dataList.add("Mode: Empowered");
        }
        else{
            dataList.add("Mode: Regular");
        }
    }

    public String getName(){
        return this.getUnlocalizedName().substring(5);
    }

    private void breakBlock(World worldIn, BlockPos pos, BlockPos origin, ItemStack stack){
        BlockPos currentBlock = pos;
        Block block = worldIn.getBlockState(pos).getBlock();
        if(!worldIn.isAirBlock(currentBlock)) {
            if(block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil || block.getMaterial() == Material.rock) {
                EntityItem item = new EntityItem(worldIn, origin.getX(), origin.getY(), origin.getZ(), new ItemStack(worldIn.getBlockState(currentBlock).getBlock(), 1, worldIn.getBlockState(currentBlock).getBlock().getMetaFromState(worldIn.getBlockState(currentBlock))));
                worldIn.setBlockToAir(currentBlock);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if(!worldIn.isRemote) {
            if (playerIn.isSneaking()) {
                try {
                    if (itemStackIn.getTagCompound().getBoolean("Empowered")) {
                        itemStackIn.getTagCompound().setBoolean("Empowered", false);
                        playerIn.addChatMessage(new ChatComponentText("Mode: Regular"));
                    } else if (!itemStackIn.getTagCompound().getBoolean("Empowered")) {
                        itemStackIn.getTagCompound().setBoolean("Empowered", true);
                        playerIn.addChatMessage(new ChatComponentText("Mode: Empowered"));
                    }
                } catch (NullPointerException e) {
                    itemStackIn.setTagCompound(new NBTTagCompound());
                    itemStackIn.getTagCompound().setBoolean("Empowered", false);
                }
            }
        }
        return itemStackIn;
    }
}
