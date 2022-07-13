package me.applesfruit.ezstats;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import me.applesfruit.ezstats.config.FileManager;
import me.applesfruit.ezstats.events.KeyEvent;
import me.applesfruit.ezstats.gui.GHandler;
import me.applesfruit.ezstats.proxy.ServerProxy;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@Mod(modid = EZStats.modid, name = EZStats.name, version = EZStats.version)
public class EZStats
{

    @Mod.Instance
    static EZStats mod;
    public static final String modid = "ezstats";
    public static final String name = "EZStats";
    public static final String version = "1.0";

    @SidedProxy(clientSide = "me.applesfruit.ezstats.proxy.ClientProxy", serverSide = "net.applesfruit.ezstats.proxy.ServerProxy")

    public static ServerProxy sp;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        sp.register();

        NetworkRegistry.INSTANCE.registerGuiHandler(mod, new GHandler());
    }

    @Mod.EventHandler
    public void post(FMLPostInitializationEvent event)
    {
        FileManager.instance.createMain();
    }

    public static EZStats getInstance()
    {
        return new EZStats();
    }


}
