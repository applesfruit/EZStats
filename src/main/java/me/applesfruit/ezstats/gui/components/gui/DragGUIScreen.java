package me.applesfruit.ezstats.gui.components.gui;

import me.applesfruit.ezstats.EZStats;
import net.minecraft.client.gui.GuiScreen;

public class DragGUIScreen extends GuiScreen {
    protected DragGUI draggedGui;
    protected EZStats instance;
    protected int lastX;
    protected int lastY;

    public DragGUIScreen(EZStats instance)
    {
        this.instance = instance;
    }

    @Override
    public void initGui() {
        for (DragGUI gui : this.instance.getDragGUIs())
        {
            this.fitGuiIntoScreen(gui);
        }
    }

    @Override
    public void drawScreen(int x, int y, float partial) {
        super.drawScreen(x, y, partial);
        this.instance.render();
        for (DragGUI gui : this.instance.getDragGUIs())
        {
            hollowRect(gui.getPosX(), gui.getPosY(), gui.getWidth(), gui.getHeight(), -1);
        }

        if (this.draggedGui != null)
        {
            this.draggedGui.setPosX(this.draggedGui.getPosX() + x - this.lastX);
            this.draggedGui.setPosY(this.draggedGui.getPosY() + y - this.lastY);
            this.fitGuiIntoScreen(this.draggedGui);
        }
        this.lastX = x;
        this.lastY = y;
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        for (DragGUI gui : this.instance.getDragGUIs())
        {
            if (x < gui.getPosX() || y < gui.getPosY() || !((float)x <= (float)gui.getPosX() + (float)gui.getWidth() * gui.getScale()) || !((float)y <= (float)gui.getPosY() + (float)gui.getHeight() * gui.getScale())) continue;
            this.draggedGui = gui;
            this.lastX = x;
            this.lastY = y;
            break;
        }

    }

    @Override
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
        this.draggedGui = null;
    }

    private void fitGuiIntoScreen(DragGUI gui) {
        gui.setPosX(Math.min(this.width - (int)((float)gui.getWidth() * gui.getScale()), Math.max(0, gui.getPosX())));
        gui.setPosY(Math.min(this.height - (int)((float)gui.getHeight() * gui.getScale()), Math.max(0, gui.getPosY())));
    }

    private void hollowRect(int x, int y, int w, int h, int color)
    {
        this.drawHorizontalLine(x, x + w, y ,color);
        this.drawHorizontalLine(x, x + w, y + h, color);
        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);
    }
}
