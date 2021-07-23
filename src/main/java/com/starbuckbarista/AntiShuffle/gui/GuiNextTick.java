package com.starbuckbarista.AntiShuffle.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@SuppressWarnings("deprecation")
public class GuiNextTick {

    private final GuiScreen guiScreen;

    public GuiNextTick (GuiScreen guiScreen) {

        this.guiScreen = guiScreen;
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onTick (TickEvent.ClientTickEvent tickEvent) {

        FMLCommonHandler.instance().bus().unregister(this);
        Minecraft.getMinecraft().displayGuiScreen(guiScreen);
    }
}
