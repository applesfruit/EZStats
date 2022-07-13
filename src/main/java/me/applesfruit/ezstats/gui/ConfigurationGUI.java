package me.applesfruit.ezstats.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

@SideOnly(Side.CLIENT)
public class ConfigurationGUI extends GuiScreen
{

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();
        // IDs are at 1000 so that it doesn't interfere with any other buttons, not sure if it works like that but it's just for safety measure beacuse I don't know anything about 1.7.10
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1000, 50, 50, 80, 20, "Components"));
        super.initGui();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();

        this.drawDefaultBackground();
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "EZStats Configuration GUI", w / 2, h / 3 - h / 4, -1);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id)
        {
            case 1000:
                System.out.println("Hi");
                break;
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
