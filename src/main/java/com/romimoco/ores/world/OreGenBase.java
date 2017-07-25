package com.romimoco.ores.world;

import com.romimoco.ores.enums.EnumOreValue;
import com.romimoco.ores.util.OreConfig;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.LinkedList;
import java.util.Random;

public class OreGenBase implements IWorldGenerator {
    private LinkedList<OreGenDefinition> ores;



    public OreGenBase(){
        ores = new LinkedList<OreGenDefinition>();
    }

    public void add(OreGenDefinition def){
        ores.add(def);
    }



    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
       for(OreGenDefinition d : ores) {
           addOreSpawn(world, random, chunkX * 16, chunkZ * 16, 16, 16, d);
       }
    }


    private void addOreSpawn(World world, Random random, int xPos, int zPos, int maxX, int maxZ, OreGenDefinition def){
        for(int i = 0; i < def.spawnChance; i++){
            int x = xPos + random.nextInt(maxX);
            int y = def.minY + random.nextInt(def.maxY - def.minY);
            int z = zPos + random.nextInt(maxZ);

            if(OreConfig.genVariants) {
                (new WorldGenMinable(def.ore.getStateFromMeta(random.nextInt(EnumOreValue.values().length)), def.veinSize, BlockMatcher.forBlock(Blocks.STONE))).generate(world, random, new BlockPos(x, y, z));
            }else{
                (new WorldGenMinable(def.ore.getDefaultState(), def.veinSize, BlockMatcher.forBlock(Blocks.STONE))).generate(world, random, new BlockPos(x, y, z));
            }
        }
    }
}
