package me.applesfruit.ezstats.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import me.applesfruit.ezstats.events.ClickEvent;
import me.applesfruit.ezstats.events.KeyEvent;
import me.applesfruit.ezstats.events.RenderEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy
{

    @Override
    public void register()
    {
        MinecraftForge.EVENT_BUS.register(new RenderEvent());
        MinecraftForge.EVENT_BUS.register(new ClickEvent());
        FMLCommonHandler.instance().bus().register(new KeyEvent());
        FMLCommonHandler.instance().bus().register(new ClickEvent());
    }
}
