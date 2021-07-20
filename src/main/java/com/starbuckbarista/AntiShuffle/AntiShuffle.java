package com.starbuckbarista.AntiShuffle;

import com.starbuckbarista.AntiShuffle.events.JoinEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid="antishuffle", name="Anti-Shuffle", version="1.0")
public class AntiShuffle {

    @Mod.EventHandler
    public static void onPreFMLInitialization (FMLPreInitializationEvent FMLPreInitializationEvent) {

        Logger logger = FMLPreInitializationEvent.getModLog();
        logger.info("Anti-Shuffle Initialized");
    }

    @Mod.EventHandler
    public static void onFMLPostInitialization (FMLPostInitializationEvent fmlPostInitializationEvent) {

        MinecraftForge.EVENT_BUS.register(new JoinEvent());
    }
}
