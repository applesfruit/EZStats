package me.applesfruit.ezstats.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import me.applesfruit.ezstats.events.KeyEvent;
import me.applesfruit.ezstats.events.RenderEvent;
import me.applesfruit.ezstats.gui.components.CPS;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy
{

    @Override
    public void register()
    {
        FMLCommonHandler.instance().bus().register(new KeyEvent());
        MinecraftForge.EVENT_BUS.register(new RenderEvent());
        FMLCommonHandler.instance().bus().register(new CPS());
    }
}
