package com.starbuckbarista.AntiShuffle.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class JoinEvent {

    private static EnumChatFormatting color = EnumChatFormatting.WHITE;
    private static EnumChatFormatting style = EnumChatFormatting.RESET;

    public void load () {

        File file = new File(Minecraft.getMinecraft().mcDataDir, "anti-shuffle.dat");

        if (file.exists()) {

            try {

                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                color = (EnumChatFormatting) objectInputStream.readObject();
                style = (EnumChatFormatting) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception exception) {

                exception.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void onClientChatReceived (ClientChatReceivedEvent clientChatReceivedEvent) {

        if (clientChatReceivedEvent.message.getUnformattedText().contains(" has joined ")) {

            if (clientChatReceivedEvent.message.getFormattedText().contains(String.valueOf(EnumChatFormatting.OBFUSCATED))) {

                load();

                String unformattedText = clientChatReceivedEvent.message.getUnformattedText();
                String formattedText = clientChatReceivedEvent.message.getFormattedText();
                String username;

                if (style == EnumChatFormatting.RESET) {

                    username = color + unformattedText.substring(0, unformattedText.indexOf(" has joined "));
                } else {

                    username = color + "" + style + unformattedText.substring(0, unformattedText.indexOf(" has joined "));
                }

                String ending = EnumChatFormatting.YELLOW + formattedText.substring(formattedText.indexOf(" has joined "));

                clientChatReceivedEvent.message = new ChatComponentText(username + ending);
            }
        }
    }
}
