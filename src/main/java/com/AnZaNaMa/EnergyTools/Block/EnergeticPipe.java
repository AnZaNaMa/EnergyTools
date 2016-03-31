package com.AnZaNaMa.EnergyTools.Block;

import com.AnZaNaMa.EnergyTools.EnergyTools;
import com.AnZaNaMa.EnergyTools.Entity.TileEntity.TileEntityPipe;
import com.AnZaNaMa.EnergyTools.Item.ItemEnergyTools;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Andrew Graber on 5/20/2015.
 */
public class EnergeticPipe extends BaseEnergyBlock implements IEnergyBlock {

    float pixel = 1F / 16F;

    public EnergeticPipe(Material material, String unlocalizedName, float hardness, float resistance){
        super(material);
        GameRegistry.registerBlock(this, unlocalizedName);
        this.useNeighborBrightness = true;
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(EnergyTools.creativeTab);
        this.setHardness(hardness);
        this.setResistance(resistance);

        float pixel = 1F/16F;
        this.setBlockBounds(5.5F*pixel, 5.5F*pixel, 5.5F*pixel, 1-5.5F*pixel, 1-5.5F*pixel, 1-5.5F*pixel);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState blockState){
        TileEntityPipe pipe = (TileEntityPipe)world.getTileEntity(pos);

        if(pipe != null) {
            float minX, minY, minZ, maxX, maxY, maxZ;
            if(pipe.getConnection(2) != null) minZ = 0;
            else minZ = 11*pixel/2;
            if(pipe.getConnection(3) != null) maxZ = 1;
            else maxZ = 1-11*pixel/2;
            if(pipe.getConnection(5) != null) minX = 0;
            else minX = 11*pixel/2;
            if(pipe.getConnection(4) != null) maxX = 1;
            else maxX = 1-11*pixel/2;
            if(pipe.getConnection(1) != null) minY = 0;
            else minY = 11*pixel/2;
            if(pipe.getConnection(0) != null) maxY = 1;
            else maxY = 1-11*pixel/2;

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        return AxisAlignedBB.fromBounds(pos.getX()+this.minX, pos.getY()+this.minY, pos.getZ()+this.minZ, pos.getX()+this.maxX, pos.getY()+this.maxY, pos.getZ()+this.maxZ);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos){
        TileEntityPipe pipe = (TileEntityPipe)world.getTileEntity(pos);

        if(pipe != null) {
            float minX, minY, minZ, maxX, maxY, maxZ;
            if(pipe.getConnection(2) != null) minZ = 0;
            else minZ = 11*pixel/2;
            if(pipe.getConnection(3) != null) maxZ = 1;
            else maxZ = 1-11*pixel/2;
            if(pipe.getConnection(5) != null) minX = 0;
            else minX = 11*pixel/2;
            if(pipe.getConnection(4) != null) maxX = 1;
            else maxX = 1-11*pixel/2;
            if(pipe.getConnection(1) != null) minY = 0;
            else minY = 11*pixel/2;
            if(pipe.getConnection(0) != null) maxY = 1;
            else maxY = 1-11*pixel/2;

            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        return AxisAlignedBB.fromBounds(pos.getX()+this.minX, pos.getY()+this.minY, pos.getZ()+this.minZ, pos.getX()+this.maxX, pos.getY()+this.maxY, pos.getZ()+this.maxZ);
    }

    @Override
    public void onBlockClicked(World world, BlockPos position, EntityPlayer player){

        if(!world.isRemote) {
            TileEntity entity = world.getTileEntity(position);
            if (entity instanceof TileEntityPipe) {
                Item item = null;
                try {
                    item = player.getHeldItem().getItem();
                }catch(NullPointerException e){
                }
                if(item == ItemEnergyTools.infenergyorb){
                }
                else if(item != null){
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityPipe();
    }

    @Override
    public int getRenderType(){
        return -1;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean isFullCube(){
        return false;
    }
}
