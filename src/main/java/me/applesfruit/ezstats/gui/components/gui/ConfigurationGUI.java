package me.applesfruit.ezstats.gui.components.gui;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.components.gui.customization.ComponentGUI;
import me.applesfruit.ezstats.gui.components.impl.CPS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

@SideOnly(Side.CLIENT)
public class ConfigurationGUI extends DragGUIScreen
{

    public ConfigurationGUI(EZStats instance)
    {
        super(instance);
    }


    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1000, this.width / 2 - 40, this.height / 2 - 10, 80, 20, "Components"));

    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        this.drawCenteredString(this.mc.fontRenderer, "Click any module to change its position!", this.width / 2, this.height / 8, -1);
        for (DragGUI gui : this.instance.getDragGUIs())
        {
            gui.setScale(this.instance.scale.getValue().floatValue());
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id)
        {
            case 1000:
                this.mc.displayGuiScreen(new ComponentGUI(this.instance));
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void onGuiClosed() {
        this.instance.save();
    }
}
