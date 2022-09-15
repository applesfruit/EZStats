package me.applesfruit.ezstats.gui.impl;


import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.lwjgl.opengl.GL11;

import java.util.List;

// S/O Poiu (notpoiu)
public class Ping extends DragGUI {

    private int ping = 0;
    public boolean enabled = true;

    public Ping(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return mc.fontRenderer.getStringWidth("Ping: 000ms") + 4;
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
            if (mc.isSingleplayer())
                ping = 0;
            else
            {

                NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
                List<GuiPlayerInfo> playerInfoList = handler.playerInfoList;
                playerInfoList.stream().filter(EntityPlayerSP.class::isInstance);
                playerInfoList.stream().anyMatch(mc.thePlayer.getClass()::isInstance);
                GuiPlayerInfo player = playerInfoList.get(0);



                ping = player.responseTime;
            }

            int mx = this.posX + (this.getWidth() - fr.getStringWidth("Ping: 000ms")) / 2;
            int my = this.posY + (this.getHeight() - fr.FONT_HEIGHT) / 2 + 1;

            GL11.glEnable(3042);
            fr.drawString(mode(), mx, my, this.instance.pingColor.getValue().intValue()) ;
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

    private String normal() { return "Ping: " + ping + "ms"; }

    private String noText() { return ping + "ms"; }

    private String brackets()
    {
        return "[Ping: " + ping + "ms]";
    }

    private String noTextBrackets() { return "[" + ping + "ms]"; }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
