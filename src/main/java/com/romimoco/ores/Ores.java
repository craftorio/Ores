package com.romimoco.ores;

import com.romimoco.ores.proxy.CommonProxy;
import com.romimoco.ores.events.OreGenEventHandler;
import com.romimoco.ores.events.RegistryEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ores.MODID, version = Ores.VERSION, name = Ores.NAME)
public class Ores
{
    public static final String MODID = "romimocoores";
    public static final String NAME = "ores";
    public static final String VERSION = "0.0.1";
    
    @Mod.Instance
    public static Ores instance;

    @SidedProxy(clientSide = "com.romimoco.ores.proxy.ClientProxy", serverSide = "com.romimoco.ores.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        System.out.println("Romimoco ores PRE-INIT");
        proxy.preInit(event);

        MinecraftForge.EVENT_BUS.register(new RegistryEventHandler());
        MinecraftForge.ORE_GEN_BUS.register(new OreGenEventHandler());


        //0.2.0
        //TODO: Dusts
        //TODO: Recipes -  Variant Armors/tools
        //TODO: Gem generation (coal, diamonds, emeralds)
        //TODO: Fluids
        //TODO: Config option for gems to drop multiple or variants

    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Romimoco ores INIT");
        proxy.init(event);

    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event)
    {
        System.out.println("Romimoco ores POST-INIT");
        proxy.postInit(event);
    }

}