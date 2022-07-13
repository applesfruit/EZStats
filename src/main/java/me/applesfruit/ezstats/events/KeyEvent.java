package me.applesfruit.ezstats.events;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.ConfigurationGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyEvent
{
    public static KeyBinding[] keyBindings;

    public KeyEvent()
    {
        keyBindings = new KeyBinding[1];

        keyBindings[0] = new KeyBinding("Open GUI", Keyboard.KEY_RSHIFT, "EZStats");

        for (int i = 0; i < keyBindings.length; i++)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event)
    {
       if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class))
       {

           if (Keyboard.getEventKey() == keyBindings[0].getKeyCode())
           {
               Minecraft.getMinecraft().displayGuiScreen(new ConfigurationGUI());
           }

       }
    }

}
