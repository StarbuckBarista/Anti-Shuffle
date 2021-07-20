package com.starbuckbarista.AntiShuffle.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JoinEvent {

    @SubscribeEvent
    public void onClientChatReceived (ClientChatReceivedEvent clientChatReceivedEvent) {

        if (clientChatReceivedEvent.message.getUnformattedText().contains(" has joined ")) {

            if (clientChatReceivedEvent.message.getFormattedText().contains(String.valueOf(EnumChatFormatting.OBFUSCATED))) {

                clientChatReceivedEvent.setCanceled(true);

                String unformattedText = clientChatReceivedEvent.message.getUnformattedText();
                String formattedText = clientChatReceivedEvent.message.getFormattedText();
                String username = EnumChatFormatting.GRAY + unformattedText.substring(0, unformattedText.indexOf(" has joined "));
                String ending = EnumChatFormatting.YELLOW + formattedText.substring(formattedText.indexOf(" has joined "));

                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(username + ending));
            }
        }
    }
}
