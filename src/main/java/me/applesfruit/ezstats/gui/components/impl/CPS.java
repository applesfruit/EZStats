package me.applesfruit.ezstats.gui.components.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.components.gui.DragGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CPS extends DragGUI {

    public boolean enabled = true;
    private static List<Long> leftClicks = new ArrayList<Long>();
    private static List<Long> rightClicks = new ArrayList<Long>();

    public CPS(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return mc.fontRenderer.getStringWidth("CPS: 00 | 00") + 4;
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

            int mx = this.posX + (this.getWidth() - fr.getStringWidth("CPS: 00 | 00")) / 2;
            int my = this.posY + (this.getHeight() - fr.FONT_HEIGHT) / 2 + 1;

            GL11.glEnable(3042);
            fr.drawString(mode(), mx, my, this.instance.cpsColor.getValue().intValue());
            GL11.glDisable(3042);
        }
    }

    private String mode()
    {
        if (this.instance.cpsMode.getValue().intValue() == 1)
        {
            return normal();
        }
        else if (this.instance.cpsMode.getValue().intValue() == 2)
        {
            return noText();
        }
        else if (this.instance.cpsMode.getValue().intValue() == 3)
        {
            return noTextBrackets();
        }
        else if (this.instance.cpsMode.getValue().intValue() == 4)
        {
            return brackets();
        }
        else if (this.instance.cpsMode.getValue().intValue() == 5)
        {
            return backwards();
        }
        else if (this.instance.cpsMode.getValue().intValue() == 6)
        {
            return backwardsBrackets();
        }
        else
        {
            return normal();
        }
    }

    private String normal()
    {
        return "CPS: " + getLeftClicks() + " | " + getRightClicks();
    }

    private String noText()
    {
        return getLeftClicks() + " | " + getRightClicks();
    }

    private String noTextBrackets()
    {
        return "[" + getLeftClicks() + " | " + getRightClicks() + "]";
    }

    private String brackets()
    {
        return "[CPS: " + getLeftClicks() + " | " + getRightClicks() + "]";
    }

    private String backwards()
    {
        return getLeftClicks() + " | " + getRightClicks() + " CPS";
    }

    private String backwardsBrackets()
    {
        return "[" + getLeftClicks() + " | " + getRightClicks() + " CPS]";
    }


    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static void addLeftClick() {
        leftClicks.add(System.currentTimeMillis());
    }

    public static int getLeftClicks() {
        Iterator<Long> iterator = leftClicks.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() >= System.currentTimeMillis() - 1000L) continue;
            iterator.remove();
        }
        return leftClicks.size();
    }

    public static void addRightClick() {
        rightClicks.add(System.currentTimeMillis());
    }

    public static int getRightClicks() {
        Iterator<Long> iterator = rightClicks.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() >= System.currentTimeMillis() - 1000L) continue;
            iterator.remove();
        }
        return rightClicks.size();
    }
}
