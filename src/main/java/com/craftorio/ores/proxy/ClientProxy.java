package com.craftorio.ores.proxy;

import com.craftorio.ores.blocks.ModBlocks;
import com.craftorio.ores.util.OreColor;
import com.craftorio.ores.Items.BaseBucket;
import com.craftorio.ores.Items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        //register item/blockColorHandlers
        OreColor colorHandler = new OreColor();
        for (Block B : ModBlocks.ORES) {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(colorHandler, B);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, B);
        }
        for (Block B : ModBlocks.GEMS.values()) {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(colorHandler, B);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, B);
        }
        for (Block B : ModBlocks.BLOCKS.values()) {
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(colorHandler, B);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, B);
        }
        for (Item i : ModItems.INGOTS.values()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
        }
        for (Item i : ModItems.GEMS.values()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
        }
        for (Item i : ModItems.DUSTS.values()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
        }
        for (Item i : ModItems.ARMORS.values()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
        }
        for (Item i : ModItems.TOOLS.values()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
        }
        for (Item i : ModItems.MISC.values()) {
            if (!(i instanceof BaseBucket)) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(colorHandler, i);
            }
        }
        //    ModBlocks.postInit();
    }

}
