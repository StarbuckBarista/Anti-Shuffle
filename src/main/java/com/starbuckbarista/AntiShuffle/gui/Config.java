package com.starbuckbarista.AntiShuffle.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

import java.io.*;
import java.util.ArrayList;

public class Config extends GuiScreen {

    private static EnumChatFormatting color = EnumChatFormatting.WHITE;
    private static EnumChatFormatting style = EnumChatFormatting.RESET;

    private GuiButton colorButton;
    private GuiButton styleButton;

    public Config () {

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

    public void initGui () {

        buttonList.add(colorButton = new GuiButton(0, width / 2 - 100, height / 2 - 50, 200, 20, color + "Color: " + color.getFriendlyName().toUpperCase()));
        buttonList.add(styleButton = new GuiButton(1, width / 2 - 100, height / 2 - 20, 200, 20, style + "Style: " + style.getFriendlyName().toUpperCase()));
    }

    public void drawScreen (int x, int y, float partial) {

        drawDefaultBackground();

        super.drawScreen(x, y, partial);
    }

    public void actionPerformed (GuiButton guiButton) {

        if (guiButton.id == 0) {

            setNextColor();
        } else if (guiButton.id == 1) {

            setNextStyle();
        }

        colorButton.displayString = color + "Color: " + color.getFriendlyName().toUpperCase();
        styleButton.displayString = style + "Style: " + style.getFriendlyName().toUpperCase();
        save();
    }

    private void setNextColor () {

        ArrayList<EnumChatFormatting> colorCycle = new ArrayList();
        colorCycle.add(EnumChatFormatting.WHITE);
        colorCycle.add(EnumChatFormatting.BLACK);
        colorCycle.add(EnumChatFormatting.DARK_BLUE);
        colorCycle.add(EnumChatFormatting.DARK_GREEN);
        colorCycle.add(EnumChatFormatting.DARK_AQUA);
        colorCycle.add(EnumChatFormatting.DARK_RED);
        colorCycle.add(EnumChatFormatting.DARK_PURPLE);
        colorCycle.add(EnumChatFormatting.GOLD);
        colorCycle.add(EnumChatFormatting.GRAY);
        colorCycle.add(EnumChatFormatting.DARK_GRAY);
        colorCycle.add(EnumChatFormatting.BLUE);
        colorCycle.add(EnumChatFormatting.GREEN);
        colorCycle.add(EnumChatFormatting.AQUA);
        colorCycle.add(EnumChatFormatting.RED);
        colorCycle.add(EnumChatFormatting.LIGHT_PURPLE);
        colorCycle.add(EnumChatFormatting.YELLOW);

        EnumChatFormatting currentColor = color;

        if (colorCycle.indexOf(currentColor)  == colorCycle.size() - 1) {

            color = colorCycle.get(0);
        } else {

            color = colorCycle.get(colorCycle.indexOf(currentColor) + 1);
        }
    }

    private void setNextStyle () {

        ArrayList<EnumChatFormatting> styleCycle = new ArrayList();
        styleCycle.add(EnumChatFormatting.RESET);
        styleCycle.add(EnumChatFormatting.BOLD);
        styleCycle.add(EnumChatFormatting.ITALIC);
        styleCycle.add(EnumChatFormatting.UNDERLINE);
        styleCycle.add(EnumChatFormatting.STRIKETHROUGH);
        styleCycle.add(EnumChatFormatting.OBFUSCATED);

        EnumChatFormatting currentStyle = style;

        if (styleCycle.indexOf(currentStyle)  == styleCycle.size() - 1) {

            style = styleCycle.get(0);
        } else {

            style = styleCycle.get(styleCycle.indexOf(currentStyle) + 1);
        }
    }

    public void save () {

        File file = new File(Minecraft.getMinecraft().mcDataDir, "anti-shuffle.dat");

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(color);
            objectOutputStream.writeObject(style);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }
}
