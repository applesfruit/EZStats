package me.applesfruit.ezstats;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import me.applesfruit.ezstats.gui.components.gui.DragGUI;
import me.applesfruit.ezstats.gui.components.gui.customization.ComponentGUI;
import me.applesfruit.ezstats.gui.components.impl.FPS;
import me.applesfruit.ezstats.gui.components.impl.Latency;
import me.applesfruit.ezstats.handlers.CMDHandler;
import me.applesfruit.ezstats.handlers.ClickHandler;
import me.applesfruit.ezstats.handlers.GHandler;
import me.applesfruit.ezstats.gui.components.impl.CPS;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.util.LinkedHashSet;

@Mod(modid = EZStats.modid, name = EZStats.name, version = EZStats.version)
public class EZStats
{

    public static final String modid = "ezstats";
    public static final String name = "EZStats";
    public static final String version = "0.7";

    private Minecraft mc;

    private Latency latency;
    private CPS cps;
    private FPS fps;
    private LinkedHashSet<DragGUI> dragGUIs;

    private File save;

    public MutableFloat scale = new MutableFloat(1.0f);

    public MutableFloat fpsScale = new MutableFloat(1.0f);
    public MutableInt fpsColor = new MutableInt(-1);
    public MutableInt fpsMode = new MutableInt(1);

    public MutableFloat cpsScale = new MutableFloat(1.0f);
    public MutableInt cpsColor = new MutableInt(-1);
    public MutableInt cpsMode = new MutableInt(1);

    public MutableFloat latencyScale = new MutableFloat(1.0f);
    public MutableInt latencyColor = new MutableInt(-1);



    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        this.mc = Minecraft.getMinecraft();

        this.save = new File(Minecraft.getMinecraft().mcDataDir, "\\config\\EZStats.cfg");

        this.dragGUIs = new LinkedHashSet<>();

        this.cps = new CPS(this, 130, 5, 1.0f);
        this.dragGUIs.add(this.cps);

        this.fps = new FPS(this, 130, 10, 1.0f);
        this.dragGUIs.add(this.fps);

        this.latency = new Latency(this, 130, 10, 1.0f);
        this.dragGUIs.add(this.latency);
        this.load();

        ClientCommandHandler.instance.registerCommand(new CMDHandler(this));
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(new ClickHandler());
        FMLCommonHandler.instance().bus().register(new ClickHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GHandler());
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event)
    {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getMinecraft().currentScreen != new ComponentGUI(this) && !Minecraft.getMinecraft().gameSettings.showDebugInfo)
        {
            this.render();
        }

    }

    @Mod.EventHandler
    public void post(FMLPostInitializationEvent event)
    {

    }

    // Getters

    public LinkedHashSet<DragGUI> getDragGUIs()
    {
        return this.dragGUIs;
    }

    public CPS getGuiCPS()
    {
        return this.cps;
    }

    public FPS getGuiFPS() { return this.fps; }

    public Latency getGuiLatency(){
        return this.latency;
    }

    public void render()
    {
        for (DragGUI gui : this.dragGUIs)
        {
            GL11.glTranslatef((float)(-gui.getPosX()) * (gui.getScale() - 1.0f), (float)(-gui.getPosY()) * (gui.getScale() - 1.0f), 0.0f);
            GL11.glScalef(gui.getScale(), gui.getScale(), 1.0f);
            gui.drawUI();
            GL11.glScalef(1.0f / gui.getScale(), 1.0f / gui.getScale(), 1.0f);
            GL11.glTranslatef((float)gui.getPosX() * (gui.getScale() - 1.0f), (float)gui.getPosY() * (gui.getScale() - 1.0f), 0.0f);
        }
    }

    public void save()
    {
        Configuration config = new Configuration(this.save);
        this.update(config, true);
        config.save();
    }

    public void load()
    {
        Configuration config = new Configuration(this.save);
        config.load();
        this.update(config, false);
        this.fps.setScale(this.fpsScale.getValue().floatValue());
        this.cps.setScale(this.cpsScale.getValue().floatValue());
        this.latency.setScale(this.latencyScale.getValue().floatValue());
    }

    private void update(Configuration c, boolean s)
    {
        Property p = c.get("FPS", "FPS_SCALE", 1.0f);
        if (s) p.set(this.getGuiFPS().getScale());
        else this.fpsScale = new MutableFloat(p.getDouble());

        p = c.get("FPS", "FPS_COLOR", new Color(255, 255, 255).getRGB());
        if (s) p.set(this.fpsColor.getValue());
        else this.fpsColor = new MutableInt(p.getInt());

        p = c.get("FPS", "FPS_POSX", 5);
        if (s) p.set(this.fps.getPosX());
        else this.fps.setPosX(p.getInt());

        p = c.get("FPS", "FPS_POSY", 5);
        if (s) p.set(this.fps.getPosY());
        else this.fps.setPosY(p.getInt());

        p = c.get("FPS", "FPS_ENABLED", true);
        if (s) p.set(this.fps.isEnabled());
        else this.fps.setEnabled(p.getBoolean());

        p = c.get("FPS", "FPS_MODE", 1);
        if (s) p.set(this.fpsMode.getValue());
        else this.fpsMode = new MutableInt(p.getInt());


        p = c.get("CPS", "CPS_SCALE", 1.0f);
        if (s) p.set(this.getGuiCPS().getScale());
        else this.cpsScale = new MutableFloat(p.getDouble());

        p = c.get("CPS", "CPS_COLOR", new Color(255, 255, 255).getRGB());
        if (s) p.set(this.cpsColor.getValue());
        else this.cpsColor = new MutableInt(p.getInt());

        p = c.get("CPS", "CPS_POSX", 10);
        if (s) p.set(this.getGuiCPS().getPosX());
        else this.cps.setPosX(p.getInt());

        p = c.get("CPS", "CPS_POSY", 10);
        if (s) p.set(this.getGuiCPS().getPosY());
        else this.cps.setPosY(p.getInt());

        p = c.get("CPS", "CPS_ENABLED", true);
        if (s) p.set(this.cps.isEnabled());
        else this.cps.setEnabled(p.getBoolean());

        p = c.get("CPS", "CPS_MODE", 1);
        if (s) p.set(this.cpsMode.getValue());
        else this.cpsMode = new MutableInt(p.getInt());

        p = c.get("LATENCY", "LATENCY_SCALE", 1.0f);
        if (s) p.set(this.getGuiLatency().getScale());
        else this.latencyScale = new MutableFloat(p.getDouble());

        p = c.get("LATENCY", "LATENCY_COLOR", new Color(255, 255, 255).getRGB());
        if (s) p.set(this.latencyColor.getValue());
        else this.latencyColor = new MutableInt(p.getInt());

        p = c.get("LATENCY", "LATENCY_POSX", 10);
        if (s) p.set(this.getGuiLatency().getPosX());
        else this.latency.setPosX(p.getInt());

        p = c.get("LATENCY", "LATENCY_POSY", 10);
        if (s) p.set(this.getGuiLatency().getPosY());
        else this.latency.setPosY(p.getInt());

        p = c.get("LATENCY", "LATENCY_ENABLED", true);
        if (s) p.set(this.latency.isEnabled());
        else this.latency.setEnabled(p.getBoolean());
    }


    public static EZStats getInstance()
    {
        return new EZStats();
    }

}
