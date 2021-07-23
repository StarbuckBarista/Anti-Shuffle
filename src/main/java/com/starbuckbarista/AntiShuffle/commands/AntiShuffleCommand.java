package com.starbuckbarista.AntiShuffle.commands;

import com.starbuckbarista.AntiShuffle.gui.Config;
import com.starbuckbarista.AntiShuffle.gui.GuiNextTick;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class AntiShuffleCommand extends CommandBase {


    public String getCommandName() {

        return "antishuffle";
    }

    public String getCommandUsage(ICommandSender sender) {

        return "/antishuffle config";
    }

    public boolean canCommandSenderUseCommand (ICommandSender sender) {

        return true;
    }

    public void processCommand(ICommandSender sender, String[] args) {

        new GuiNextTick(new Config());
    }
}
