package me.applesfruit.ezstats.gui.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class FullBright extends DragGUI {

    public boolean enabled = true;

    public FullBright(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void drawUI() {
        if (this.enabled)
        {
            this.mc.gameSettings.gammaSetting = 10000f;
        }
        else
        {
            this.mc.gameSettings.gammaSetting = 1f;
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
