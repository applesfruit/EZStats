package me.applesfruit.ezstats.gui.components.gui.customization;

import cpw.mods.fml.client.config.GuiSlider;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.components.gui.DragGUI;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.mutable.MutableInt;

import java.awt.*;

public class ComponentGUI extends GuiScreen {

    protected EZStats instance;

    private GuiSlider fpsRed;
    private GuiSlider fpsGreen;
    private GuiSlider fpsBlue;
    private GuiSlider fpsAlpha;
    public GuiSlider fpsModes;

    private GuiSlider cpsRed;
    private GuiSlider cpsGreen;
    private GuiSlider cpsBlue;
    private GuiSlider cpsAlpha;
    public GuiSlider cpsModes;

    public ComponentGUI(EZStats instance)
    {
        this.instance = instance;
    }


    @Override
    public void initGui() {
        this.buttonList.clear();
        int frgba = this.instance.fpsColor.getValue().intValue();
        int fmode = this.instance.fpsMode.getValue().intValue();
        this.buttonList.add(new GuiButton(1000, this.width / 8 - 40, this.height / 7 + 15, 80, 20, "Toggle FPS"));
        this.fpsRed = new GuiSlider(1001, this.width / 8 - 40, this.height / 7 + 45, 80, 20, "Red: ", "", 0, 255, frgba >> 16 & 0xFF, false, true);
        this.fpsGreen = new GuiSlider(1002, this.width / 8 - 40, this.height / 7 + 75, 80, 20, "Green: ", "", 0, 255, frgba >> 8 & 0xFF, false, true);
        this.fpsBlue = new GuiSlider(1003, this.width / 8 - 40, this.height / 7 + 105, 80, 20, "Blue: ", "", 0, 255, frgba & 0xFF, false, true);
        this.fpsAlpha = new GuiSlider(1004, this.width / 8 - 40, this.height / 7 + 135, 80, 20, "Alpha: ", "", 1, 255, frgba >> 24 & 0xFF, false, true);
        this.fpsModes = new GuiSlider(1005, this.width / 8 - 40, this.height /7 + 165, 80, 20, "Mode: ", "", 1, 4, fmode, false, true);
        this.buttonList.add(new GuiButton(1006, this.width / 8 - 40, this.height / 7 + 195, 80, 20, "Reset FPS"));
        this.buttonList.add(this.fpsRed);
        this.buttonList.add(this.fpsGreen);
        this.buttonList.add(this.fpsBlue);
        this.buttonList.add(this.fpsAlpha);
        this.buttonList.add(this.fpsModes);

        int crgba = this.instance.cpsColor.getValue().intValue();
        int cmode = this.instance.cpsMode.getValue().intValue();
        this.buttonList.add(new GuiButton(1007, this.width / 8 + 40, this.height / 7 + 15, 80, 20, "Toggle CPS"));
        this.cpsRed = new GuiSlider(1008, this.width / 8 + 40, this.height / 7 + 45, 80, 20, "Red: ", "", 0, 255, crgba >> 16 & 0xFF, false, true);
        this.cpsGreen = new GuiSlider(1009, this.width / 8 + 40, this.height / 7 + 75, 80, 20, "Green: ", "", 0, 255, crgba >> 8 & 0xFF, false, true);
        this.cpsBlue = new GuiSlider(1010, this.width / 8 + 40, this.height / 7 + 105, 80, 20, "Blue: ", "", 0, 255, crgba & 0xFF, false, true);
        this.cpsAlpha = new GuiSlider(1011, this.width / 8 + 40, this.height / 7 + 135, 80, 20, "Alpha: ", "", 1, 255, crgba >> 24 & 0xFF, false, true);
        this.cpsModes = new GuiSlider(1012, this.width / 8 + 40, this.height /7 + 165, 80, 20, "Mode: ", "", 1, 6, cmode, false, true);
        this.buttonList.add(new GuiButton(1013, this.width / 8 + 40, this.height / 7 + 195, 80, 20, "Reset CPS"));
        this.buttonList.add(this.cpsRed);
        this.buttonList.add(this.cpsGreen);
        this.buttonList.add(this.cpsBlue);
        this.buttonList.add(this.cpsAlpha);
        this.buttonList.add(this.cpsModes);
        super.initGui();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();

        this.drawCenteredString(this.mc.fontRenderer, "FPS", this.width / 8, this.height / 7, -1);

        this.drawCenteredString(this.mc.fontRenderer, "CPS", this.width / 8 + 80, this.height / 7, -1);

        this.update();
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1000:
                this.instance.getGuiFPS().setEnabled(!this.instance.getGuiFPS().isEnabled());
                break;
            case 1006:
                this.instance.getGuiFPS().setEnabled(true);
                this.instance.getGuiFPS().setPosY(5);
                this.instance.getGuiFPS().setPosX(130);
                this.instance.getGuiFPS().setScale(1.0f);
                break;
            case 1007:
                this.instance.getGuiCPS().setEnabled(this.instance.getGuiCPS().isEnabled());
                break;
            case 1013:
                this.instance.getGuiCPS().setEnabled(true);
                this.instance.getGuiCPS().setPosY(10);
                this.instance.getGuiCPS().setPosX(130);
                this.instance.getGuiFPS().setScale(1.0f);
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

    @Override
    public void onGuiClosed() {
        this.instance.save();
    }

    private void update()
    {
        int fr, fg, fb, fa, fm, fs;
        // gonna add scale soon, its currently broken :((
        fr = this.fpsRed.getValueInt();
        fg = this.fpsGreen.getValueInt();
        fb = this.fpsBlue.getValueInt();
        fa = this.fpsAlpha.getValueInt();
        fm = this.fpsModes.getValueInt();
        this.instance.fpsColor.setValue(new Color(fr, fg, fb, fa).getRGB());
        this.instance.fpsMode.setValue(fm);

        int cr, cg, cb, ca, cm, cs;
        cr = this.cpsRed.getValueInt();
        cg = this.cpsGreen.getValueInt();
        cb = this.cpsBlue.getValueInt();
        ca = this.cpsAlpha.getValueInt();
        cm = this.cpsModes.getValueInt();
        this.instance.cpsColor.setValue(new Color (cr, cg, cb, ca).getRGB());
        this.instance.cpsMode.setValue(cm);
    }
}
