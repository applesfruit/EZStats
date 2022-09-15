package me.applesfruit.ezstats.gui.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import me.applesfruit.ezstats.util.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Collection;
import java.util.Iterator;

public class PotionStatus extends DragGUI {

    public boolean enabled = true;
    protected float zLevel;

    public PotionStatus(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 155;
    }

    @Override
    public  void drawUI() {
        if (!this.enabled)
            return;
        if (this.scale != 0.0f)
        {
            // i really have no idea whats happening with this
            // rendering glitch when holding enchanted items/books and sometimes
            // and anyways its shitty (taken from InventoryEffectRenderer)
            FontRenderer fr = this.mc.fontRenderer;

            int offsetX = 21;
            int offsetY = 14;
            int i = 80;
            int i2 = 16;
            Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

            if (!collection.isEmpty())
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableLighting();
                int l = 33;

                if (collection.size() > 5)
                {
                    l = 132 / (collection.size() - 1);
                }

                for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); i += i2)
                {
                    PotionEffect potionEffect = (PotionEffect) iterator.next();
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                    int i1 = potion.getStatusIconIndex();
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    drawTexturedModalRect((posX + offsetX) - 20, (posY + i2) - offsetY, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);

                    potion.renderInventoryEffect((posX + offsetX) - 20, (posY + i2) - offsetY, potionEffect, Minecraft.getMinecraft());
                    if (!potion.shouldRenderInvText(potionEffect)) continue;
                    String s1 = I18n.format(potion.getName(), new Object[0]);
                    if (potionEffect.getAmplifier() == 1)
                    {
                        s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                    }
                    else if (potionEffect.getAmplifier() == 2)
                    {
                        s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                    }
                    else if (potionEffect.getAmplifier() == 3)
                    {
                        s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                    }

                    GL11.glEnable(3042);
                    fr.drawString(s1, posX + offsetX, (posY + i2) - offsetY, 16777215, true);
                    String s = Potion.getDurationString(potionEffect);
                    fr.drawString(s, posX + offsetX, (posY + i2 + 10) - offsetY, 8355711, true);
                    GL11.glDisable(3042);
                    i2 += l;
                }
            }
        }
    }

    public void drawTexturedModalRect(int x, int y, int tX, int tY, int w, int h)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + h), (double)this.zLevel, (double)((float)(tX + 0) * f), (double)((float)(tY + h) * f1));
        tessellator.addVertexWithUV((double)(x + w), (double)(y + h), (double)this.zLevel, (double)((float)(tX + w) * f), (double)((float)(tY + h) * f1));
        tessellator.addVertexWithUV((double)(x + w), (double)(y + 0), (double)this.zLevel, (double)((float)(tX + w) * f), (double)((float)(tY + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, (double)((float)(tX + 0) * f), (double)((float)(tY + 0) * f1));
        tessellator.draw();
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
