package me.applesfruit.ezstats;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import me.applesfruit.ezstats.gui.DragGUI;
import me.applesfruit.ezstats.gui.customization.ComponentGUI;
import me.applesfruit.ezstats.gui.impl.*;
import me.applesfruit.ezstats.handlers.CMDHandler;
import me.applesfruit.ezstats.handlers.CMDHandlerSettings;
import me.applesfruit.ezstats.handlers.ClickHandler;
import me.applesfruit.ezstats.handlers.GHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

@Mod(modid = EZStats.modid, name = EZStats.name, version = EZStats.version)
public class EZStats
{

    public static final String modid = "ezstats";
    public static final String name = "EZStats";
    public static final String version = "1.1";

    public Minecraft mc;
    private CPS cps;
    private FPS fps;
    private Ping ping;
    private Memory mem;
    private ArmorStatus armorStatus;
    private PotionStatus potionStatus;
    private FullBright fullBright;

    private LinkedHashSet<DragGUI> dragGUIs;
    private File save;

    public MutableFloat scale = new MutableFloat(1.0f);
    public String windowName = "Minecraft 1.7.10";

    public MutableInt fpsColor = new MutableInt(-1);
    public MutableInt fpsMode = new MutableInt(1);

    public MutableInt cpsColor = new MutableInt(-1);
    public MutableInt cpsMode = new MutableInt(1);

    public MutableInt pingColor = new MutableInt(-1);
    public MutableInt pingMode = new MutableInt(1);

    public MutableInt memoryColor = new MutableInt(-1);
    public MutableInt memoryMode = new MutableInt(1);

    public MutableBoolean armorAddItemInHand = new MutableBoolean(true);
    public MutableInt armorMode = new MutableInt(1);


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

        this.ping = new Ping(this, 130, 15, 1.0f);
        this.dragGUIs.add(this.ping);

        this.mem = new Memory(this, 130, 20, 1.0f);
        this.dragGUIs.add(this.mem);

        this.armorStatus = new ArmorStatus(this, 130, 15, 1.0f);
        this.dragGUIs.add(this.armorStatus);

        this.potionStatus = new PotionStatus(this, 130, 20, 1.0f);
        this.dragGUIs.add(this.potionStatus);

        this.fullBright = new FullBright(this, 0, 0, 1.0f);
        this.dragGUIs.add(this.fullBright);

        this.load();

        ClientCommandHandler.instance.registerCommand(new CMDHandler(this));
        ClientCommandHandler.instance.registerCommand(new CMDHandlerSettings(this));
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(new ClickHandler());
        FMLCommonHandler.instance().bus().register(new ClickHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GHandler());
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getMinecraft().currentScreen != new ComponentGUI(this) && !Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            this.render();
        }
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

    public Ping getGuiPing() { return this.ping; }

    public Memory getGuiMemory() { return this.mem; }

    public ArmorStatus getGuiArmorStatus() { return this.armorStatus; }

    public PotionStatus getGuiPotionStatus() { return this.potionStatus; }

    public FullBright getGuiFullBright() { return this.fullBright; };

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
        Display.setTitle(this.windowName);
        this.fps.setScale(this.scale.getValue().floatValue());
        this.cps.setScale(this.scale.getValue().floatValue());
        this.ping.setScale(this.scale.getValue().floatValue());
        this.mem.setScale(this.scale.getValue().floatValue());
        this.armorStatus.setScale(this.scale.getValue().floatValue());
        this.potionStatus.setScale(this.scale.getValue().floatValue());
        this.fullBright.setScale(this.scale.getValue().floatValue());
    }

    private void update(Configuration c, boolean s)
    {
        Property p = c.get("GENERAL", "SCALE", 1.0);
        if (s) p.set(this.scale.getValue().floatValue());
        else this.scale = new MutableFloat(p.getDouble());

        p = c.get("GENERAL", "WINDOWNAME", "Minecraft 1.7.10");
        if (s) p.set(this.windowName);
        else this.windowName = p.getString();


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


        p = c.get("PING", "PING_COLOR", new Color(255, 255, 255).getRGB());
        if (s) p.set(this.pingColor.getValue());
        else this.pingColor = new MutableInt(p.getInt());

        p = c.get("PING", "PING_POSX", 15);
        if (s) p.set(this.getGuiPing().getPosX());
        else this.ping.setPosX(p.getInt());

        p = c.get("PING", "PING_POSY", 15);
        if (s) p.set(this.getGuiPing().getPosY());
        else this.ping.setPosY(p.getInt());

        p = c.get("PING", "PING_ENABLED", false);
        if (s) p.set(this.ping.isEnabled());
        else this.ping.setEnabled(p.getBoolean());

        p = c.get("PING", "PING_MODE", 1);
        if (s) p.set(this.pingMode.getValue());
        else this.pingMode = new MutableInt(p.getInt());


        p = c.get("MEMORY", "MEMORY_COLOR", new Color(255, 255, 255).getRGB());
        if (s) p.set(this.memoryColor.getValue());
        else this.memoryColor = new MutableInt(p.getInt());

        p = c.get("MEMORY", "MEMORY_POSX", 20);
        if (s) p.set(this.getGuiMemory().getPosX());
        else this.mem.setPosX(p.getInt());

        p = c.get("MEMORY", "MEMORY_POSY", 20);
        if (s) p.set(this.getGuiMemory().getPosY());
        else this.mem.setPosY(p.getInt());

        p = c.get("MEMORY", "MEMORY_ENABLED", false);
        if (s) p.set(this.mem.isEnabled());
        else this.mem.setEnabled(p.getBoolean());

        p = c.get("MEMORY", "MEMORY_MODE", 1);
        if (s) p.set(this.memoryMode.getValue());
        else this.memoryMode = new MutableInt(p.getInt());


        p = c.get("ARMORSTATUS", "ARMORSTATUS_POSX", 25);
        if (s) p.set(this.getGuiArmorStatus().getPosX());
        else this.armorStatus.setPosX(p.getInt());

        p = c.get("ARMORSTATUS", "ARMORSTATUS_POSY", 25);
        if (s) p.set(this.getGuiArmorStatus().getPosY());
        else this.armorStatus.setPosY(p.getInt());

        p = c.get("ARMORSTATUS", "ARMORSTATUS_ENABLED", true);
        if (s) p.set(this.armorStatus.isEnabled());
        else this.armorStatus.setEnabled(p.getBoolean());

        p = c.get("ARMORSTATUS", "ARMORSTATUS_MODE", 1);
        if (s) p.set(this.armorMode.getValue());
        else this.armorMode = new MutableInt(p.getInt());

        p = c.get("ARMORSTATUS", "ARMORSTATUS_RENDERITEMHAND", true);
        if (s) p.set(this.armorAddItemInHand.getValue());
        else this.armorAddItemInHand = new MutableBoolean(p.getBoolean());

        p = c.get("POTIONSTATUS", "POTIONSTATUS_ENABLED", false);
        if (s) p.set(this.potionStatus.isEnabled());
        else this.potionStatus.setEnabled(p.getBoolean());

        p = c.get("FULLBRIGHT", "FULLBRIGHT_ENABLED", true);
        if (s) p.set(this.fullBright.isEnabled());
        else this.fullBright.setEnabled(p.getBoolean());
    }


    public static EZStats getInstance()
    {
        return new EZStats();
    }

}
