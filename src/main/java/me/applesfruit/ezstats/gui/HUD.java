package me.applesfruit.ezstats.gui;

import me.applesfruit.ezstats.gui.components.CPS;
import me.applesfruit.ezstats.gui.components.FPS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends Gui
{

    public HUD(Minecraft mc)
    {
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();

        this.drawCenteredString(mc.fontRenderer, "FPS: " + FPS.getFPS(), w / 2, h / 2, -1);
        this.drawCenteredString(mc.fontRenderer, CPS.normal(), w / 3, h / 3, -1);

    }

}
