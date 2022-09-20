package me.applesfruit.ezstats.gui.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class Memory extends DragGUI {

    public boolean enabled = true;

    public Memory(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return mc.fontRenderer.getStringWidth("Mem: 00%") + 4;
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
            FontRenderer fr = this.mc.fontRenderer;

            int mx = this.posX + (this.getWidth() - fr.getStringWidth("Mem: 000")) / 2;
            int my = this.posY + (this.getHeight() - fr.FONT_HEIGHT) / 2 + 1;

            GL11.glEnable(3042);
            fr.drawString(mode(), mx, my, this.instance.memoryColor.getValue().intValue(), true);
            GL11.glDisable(3042);
        }
    }

    public String mode()
    {
        if (this.instance.pingMode.intValue() == 1)
        {
            return normal();
        }
        else if (this.instance.pingMode.intValue() == 2)
        {
            return noText();
        }
        else if (this.instance.pingMode.intValue() == 3)
        {
            return brackets();
        }
        else if (this.instance.pingMode.intValue() == 4)
        {
            return noTextBrackets();
        }
        else {
            return normal();
        }
    }

    private String normal() { return "Mem: " + getMem() + "%"; }

    private String noText() { return getMem() + "%"; }

    private String brackets()
    {
        return "[Mem: " + getMem() + "%]";
    }

    private String noTextBrackets() { return "[" + getMem() + "%]"; }

    private long getMem()
    {
        long m = Runtime.getRuntime().maxMemory();
        long t = Runtime.getRuntime().totalMemory();
        long f = Runtime.getRuntime().freeMemory();
        long p = t - f;
        return Long.valueOf(p * 100L / m);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
