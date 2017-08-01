package com.romimoco.ores.events;

import com.romimoco.ores.Items.BaseArmor;
import com.romimoco.ores.Items.BaseIngot;
import com.romimoco.ores.Items.ModItems;
import com.romimoco.ores.blocks.BaseBlock;
import com.romimoco.ores.blocks.BaseOre;
import com.romimoco.ores.blocks.ModBlocks;
import com.romimoco.ores.blocks.itemBlocks.ItemBlockBaseOre;
import com.romimoco.ores.crafting.RecipeManager;
import com.romimoco.ores.util.IHasCustomModel;
import com.romimoco.ores.util.OreConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryEventHandler
{
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event){

        System.out.println("Registering Blocks");
        IForgeRegistry<Block> r = event.getRegistry();

        for(Block b : ModBlocks.ORES){
            r.register(b);
        }
        for(Block b : ModBlocks.BLOCKS.values()){
            r.register(b);
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event){

        IForgeRegistry<Item> r = event.getRegistry();
        //Register ItemBlocks for blocks
        for(Block b : ModBlocks.ORES){
            ItemBlock i = new ItemBlockBaseOre(b);

            r.register(i.setRegistryName(b.getRegistryName()));
            String name = ((BaseOre)b).name;
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            System.out.println(name);

            if(OreConfig.genVariants)
            {
                OreDictionary.registerOre("ore"+ name + "poor", new ItemStack(i, 1, 0));
                OreDictionary.registerOre("ore"+ name + "low", new ItemStack(i, 1, 1));
                OreDictionary.registerOre("ore"+ name + "moderate", new ItemStack(i, 1, 2));
                OreDictionary.registerOre("ore"+ name + "high", new ItemStack(i, 1, 3));
                OreDictionary.registerOre("ore"+ name, new ItemStack(i, 1, 4));
            }else {
                OreDictionary.registerOre("ore" + name, i);
            }
        }

        for(Block b : ModBlocks.BLOCKS.values()){
            ItemBlock i = new ItemBlock(b);

            r.register(i.setRegistryName(b.getRegistryName()));
            String name = ((BaseBlock)b).name;
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            System.out.println(name);

            OreDictionary.registerOre("block" +name, b);
        }

        //Register the remaining items
        for(Item i: ModItems.INGOTS.values()){
            r.register(i);
            String name = ((BaseIngot)i).name;
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            if(OreConfig.genVariants)
            {
                OreDictionary.registerOre("nugget"+ name, new ItemStack(i, 1, 0));
                OreDictionary.registerOre("shard"+ name, new ItemStack(i, 1, 1));
                OreDictionary.registerOre("chunk"+ name, new ItemStack(i, 1, 2));
                OreDictionary.registerOre("hunk"+ name, new ItemStack(i, 1, 3));
                OreDictionary.registerOre("ingot"+ name, new ItemStack(i, 1, 4));
            }else {
                OreDictionary.registerOre("ingot" + name, i);
            }
        }

        for(Item i: ModItems.DUSTS.values()){
            r.register(i);
            String name = ((BaseIngot)i).name;
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            OreDictionary.registerOre("dust" + name, i);
        }


        for(Item i: ModItems.TOOLS.values()){
            r.register(i);
        }
        for(Item i: ModItems.ARMORS.values()){
            r.register(i);
        }

    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event){
        for(Block b : ModBlocks.ORES){
            ((BaseOre)b).initModel();
        }
        for(Block b : ModBlocks.BLOCKS.values()){
            ((IHasCustomModel)b).initModel();
        }
        for(Item i : ModItems.INGOTS.values()){
            ((BaseIngot)i).initModel();
        }
        for(Item i : ModItems.ARMORS.values()){
            ((BaseArmor)i).initModel();
        }
        for(Item i : ModItems.TOOLS.values()){
            ((IHasCustomModel)i).initModel();
        }
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event){
        for(Block b: ModBlocks.ORES){
            RecipeManager.registerSmeltingRecipes((BaseOre)b);

            if(OreConfig.genArmor){
                RecipeManager.registerArmorRecipes((BaseOre)b);
            }

            if(OreConfig.genTools){
                RecipeManager.registerToolRecipes((BaseOre)b);
            }

            if(OreConfig.genFullBlocks){
                RecipeManager.registerMetalBlockRecipes((BaseOre)b);
            }

            if(OreConfig.genVariants && OreConfig.recipes.variantCombinationRecipes){
                RecipeManager.registerVariantCombinationRecipes((BaseOre)b);
            }
        }

    }
}