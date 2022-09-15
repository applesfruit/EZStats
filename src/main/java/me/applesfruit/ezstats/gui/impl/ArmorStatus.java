package me.applesfruit.ezstats.gui.impl;

import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorStatus extends DragGUI {

    public boolean enabled = true;

    public ArmorStatus(EZStats instance, int x, int y, float scale) {
        super(instance, x, y, scale);
    }

    @Override
    public int getWidth() {
        return 40;
    }

    @Override
    public int getHeight() {
        if (this.instance.armorAddItemInHand.getValue().booleanValue()) return 84; else return 64;
    }

    @Override
    public void drawUI() {
        if (!this.enabled)
            return;
        if (this.scale != 0.0f)
        {
            FontRenderer fr =  this.mc.fontRenderer;

            GL11.glEnable(3042);
            int armorLength = mc.thePlayer.inventory.armorInventory.length;
            if (this.instance.armorAddItemInHand.getValue().booleanValue())
            {
                for (int i = 0; i < armorLength; i++)
                {
                    ItemStack armor = mc.thePlayer.inventory.armorInventory[i];
                    render(i, armor);
                }
                ItemStack item = mc.thePlayer.inventory.getCurrentItem();
                render(-1, item);
            }
            else
            {
                for (int i = 0; i < armorLength; i++)
                {
                    ItemStack armor = mc.thePlayer.inventory.armorInventory[i];
                    render(i, armor);
                }
            }
            GL11.glDisable(3042);
        }
    }

    private void render(int i, ItemStack armor)
    {
        FontRenderer fr =  this.mc.fontRenderer;
        int mx = this.posX;
        int my = this.posY;
        if (armor == null) return;

        GL11.glPushMatrix();
        if (armor.getItem().isDamageable())
        {
            int dmg = armor.getMaxDamage() - armor.getItemDamage();
            if (this.instance.armorMode.intValue() == 1)
            {
                if (dmg >= 1000)
                {
                    fr.drawString(String.valueOf(dmg), mx + 16, my + (-16 * i) + 55, -1);
                }
                else
                {
                    fr.drawString(String.valueOf(dmg), mx + 18, my + (-16 * i) + 55, -1);
                }
            }
            else if (this.instance.armorMode.intValue() == 2)
            {
                if (dmg >= 1000)
                {
                    fr.drawString(String.valueOf(dmg), (int) (mx + 1.5), my + (-16 * i) + 55, -1);
                }
                else
                {
                    fr.drawString(String.valueOf(dmg), mx + 3, my + (-16 * i) + 55, -1);
                }
            }
            else
            {
                if (dmg >= 1000)
                {
                    fr.drawString(String.valueOf(dmg), mx + 16, my + (-16 * i) + 55, -1);
                }
                else
                {
                    fr.drawString(String.valueOf(dmg), mx + 18, my + (-16 * i) + 55, -1);
                }
            }
        }

        RenderHelper.enableGUIStandardItemLighting();

        if (this.instance.armorMode.intValue() == 1)
        {
            RenderItem.getInstance().renderItemAndEffectIntoGUI(fr, mc.getTextureManager(), armor, mx, my + (-16 * i) + 50);
        }
        else if (this.instance.armorMode.intValue() == 2)
        {
            RenderItem.getInstance().renderItemAndEffectIntoGUI(fr, mc.getTextureManager(), armor,mx + 23, my + (-16 * i) + 50);
        }
        else
        {
            RenderItem.getInstance().renderItemAndEffectIntoGUI(fr, mc.getTextureManager(), armor, mx, my + (-16 * i) + 50);
        }
        GL11.glPopMatrix();

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
