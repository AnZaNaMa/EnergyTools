package com.AnZaNaMa.EnergyTools.World;

import com.AnZaNaMa.EnergyTools.Block.BlockEnergyTools;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by andre_000 on 4/18/2015.
 */
public class WorldGenEnergyOre implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        switch(world.provider.getDimensionId()){
            case -1: generateNether(world, random, chunkX*16, chunkZ*16);
                break;
            case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
                break;
            case 1: generateEnd(world, random, chunkX*16, chunkZ*16);
        }
    }

    public void generateSurface(World world, Random random, int x, int z){
        this.addOreSpawn(BlockEnergyTools.energyore, world, random, x, z, 16, 16, 4, 2, 0, 15);
    }

    public void generateNether(World world, Random random, int x, int z){


    }

    public void generateEnd(World world, Random random, int x, int z){


    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
        for(int i=0; i < chancesToSpawn; i++){
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);
            BlockPos blockPos = new BlockPos(posX, posY, posZ);
            new WorldGenMinable(block.getDefaultState(), maxVeinSize).generate(world, random, blockPos);
        }
    }
}
