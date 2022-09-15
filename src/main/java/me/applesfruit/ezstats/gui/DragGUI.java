package me.applesfruit.ezstats.gui;

import me.applesfruit.ezstats.EZStats;
import net.minecraft.client.Minecraft;

public abstract class DragGUI {

    protected EZStats instance;
    protected Minecraft mc = Minecraft.getMinecraft();
    protected int posX;
    protected int posY;
    protected float scale;

    public DragGUI(EZStats instance, int x, int y, float scale) {
        this.instance = instance;
        this.posX = x;
        this.posY = y;
        this.scale = scale;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean contains(int x, int y) {
        return x > this.posX && y > this.posY && (float)x < (float)this.posX + (float)this.getWidth() * this.scale && (float)y < (float)this.posY + (float)this.getHeight() * this.scale;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void drawUI();


}
