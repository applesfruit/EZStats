package me.applesfruit.ezstats.gui.components.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.components.gui.DragGUI;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayerMP;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class Latency extends DragGUI {

    private int latency = 0;
    public boolean enabled = true;
    public Latency(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return mc.fontRenderer.getStringWidth("Ping: " + latency) + 4;
    }

    @Override
    public int getHeight() {
        return mc.fontRenderer.FONT_HEIGHT;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void drawUI() {
        if (this.scale != 0.0f)
        {
            FontRenderer fr =  this.mc.fontRenderer;
            NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
            List<GuiPlayerInfo> playerInfoList = handler.playerInfoList;
            playerInfoList.stream()
                    .filter(EntityPlayerSP.class::isInstance);

            GuiPlayerInfo player = (GuiPlayerInfo) handler.playerInfoList.get(0); // idk but it works lol
            
            if(mc.isSingleplayer())
                latency = 0;
            else
                latency = player.responseTime;

            int mx = this.posX + (this.getWidth() - fr.getStringWidth("Ping: " + latency)) / 2;
            int my = this.posY + (this.getHeight() - fr.FONT_HEIGHT) / 2 + 1;

            GL11.glEnable(3042);
            fr.drawString("Ping: " + latency, mx, my, this.instance.latencyColor.getValue().intValue());
            GL11.glDisable(3042);
        }
    }
}
