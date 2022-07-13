package me.applesfruit.ezstats.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.Sys;

public class FPS {

    public static String getFPS()
    {
        String fps = Minecraft.getMinecraft().debug;
        return fps;
    }

}
