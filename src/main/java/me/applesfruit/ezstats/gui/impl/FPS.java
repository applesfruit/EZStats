package me.applesfruit.ezstats.gui.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class FPS extends DragGUI {

    public boolean enabled = true;

    public FPS(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return mc.fontRenderer.getStringWidth("FPS: 000") + 4;
    }

    @Override
    public int getHeight() {
        return mc.fontRenderer.FONT_HEIGHT + 2;
    }

    @Override
    public void drawUI() {
        if (!this.enabled)
            return;
        if (this.scale != 0.0f)
        {
            FontRenderer fr =  this.mc.fontRenderer;

            int mx = this.posX + (this.getWidth() - fr.getStringWidth("FPS: 000")) / 2;
            int my = this.posY + (this.getHeight() - fr.FONT_HEIGHT) / 2 + 1;

            GL11.glEnable(3042);
            fr.drawString(mode(), mx, my, this.instance.fpsColor.getValue().intValue(), true);
            GL11.glDisable(3042);
        }
    }

    public String mode()
    {
        if (this.instance.fpsMode.intValue() == 1)
        {
            return normal();
        }
        else if (this.instance.fpsMode.intValue() == 2)
        {
            return noText();
        }
        else if (this.instance.fpsMode.intValue() == 3)
        {
            return brackets();
        }
        else if (this.instance.fpsMode.intValue() == 4)
        {
            return backwards();
        }
        else {
            return normal();
        }
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private String normal()
    {
        return "FPS: " + mc.debug.split(" ")[0].replaceAll(" ", "");
    }

    private String noText()
    {
        return mc.debug.split(" ")[0].replaceAll(" ", "");
    }

    private String brackets()
    {
        return "[FPS: " + mc.debug.split(" ")[0].replaceAll(" ", "") + "]";
    }

    private String backwards()
    {
        return mc.debug.split(" ")[0].replaceAll(" ", "") + " FPS";
    }

}
