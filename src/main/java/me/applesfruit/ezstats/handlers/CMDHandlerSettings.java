package me.applesfruit.ezstats.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.settings.SettingsGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMDHandlerSettings extends CommandBase {

    private EZStats instance;

    public CMDHandlerSettings(EZStats instance)
    {
        this.instance = instance;
    }


    @Override
    public String getCommandName() {
        return "ezsettings";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/ezsettings";
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List getCommandAliases() {
        List aliases = new ArrayList<String>();
        aliases.add("ezsg");
        return aliases;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event)
    {
        FMLCommonHandler.instance().bus().unregister(this);
        Minecraft.getMinecraft().displayGuiScreen(new SettingsGUI(this.instance));
    }

}
