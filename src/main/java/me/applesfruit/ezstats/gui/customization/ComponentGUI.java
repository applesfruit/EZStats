package me.applesfruit.ezstats.gui.customization;

import cpw.mods.fml.client.config.GuiSlider;
import me.applesfruit.ezstats.EZStats;
import me.applesfruit.ezstats.gui.DragGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class ComponentGUI extends GuiScreen {

    protected EZStats instance;

    private GuiSlider scale;
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

    private GuiSlider pingRed;
    private GuiSlider pingGreen;
    private GuiSlider pingBlue;
    private GuiSlider pingAlpha;
    public GuiSlider pingModes;

    private GuiSlider memoryRed;
    private GuiSlider memoryGreen;
    private GuiSlider memoryBlue;
    private GuiSlider memoryAlpha;
    public GuiSlider memoryModes;

    public GuiSlider armorModes;

    public ComponentGUI(EZStats instance)
    {
        this.instance = instance;
    }


    @Override
    public void initGui() {
        this.buttonList.clear();
        this.scale = new GuiSlider(999, this.width / 2 - 75, this.height / 7 - 30, 150, 20, "Scale: ", "%", 1.0, 200.0, (double) this.instance.scale.getValue().floatValue() * 100, false, true);
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
        this.buttonList.add(this.scale);

        int prgba = this.instance.pingColor.getValue().intValue();
        int pmode = this.instance.pingMode.getValue().intValue();
        this.buttonList.add(new GuiButton(1014, this.width / 8 + 120, this.height / 7 + 15, 80, 20, "Toggle Ping"));
        this.pingRed = new GuiSlider(1015, this.width / 8 + 120, this.height / 7 + 45, 80, 20, "Red: ", "", 0, 255, prgba >> 16 & 0xFF, false, true);
        this.pingGreen = new GuiSlider(1016, this.width / 8 + 120, this.height / 7 + 75, 80, 20, "Green: ", "", 0, 255, prgba >> 8 & 0xFF, false, true);
        this.pingBlue = new GuiSlider(1017, this.width / 8 + 120, this.height / 7 + 105, 80, 20, "Blue: ", "", 0, 255, prgba & 0xFF, false, true);
        this.pingAlpha = new GuiSlider(1018, this.width / 8 + 120, this.height / 7 + 135, 80, 20, "Alpha: ", "", 1, 255, prgba >> 24 & 0xFF, false, true);
        this.pingModes = new GuiSlider(1019, this.width / 8 + 120, this.height /7 + 165, 80, 20, "Mode: ", "", 1, 4, pmode, false, true);
        this.buttonList.add(new GuiButton(1020, this.width / 8 + 120, this.height / 7 + 195, 80, 20, "Reset Ping"));
        this.buttonList.add(this.pingRed);
        this.buttonList.add(this.pingGreen);
        this.buttonList.add(this.pingBlue);
        this.buttonList.add(this.pingAlpha);
        this.buttonList.add(this.pingModes);

        int mrgba = this.instance.memoryColor.getValue().intValue();
        int mmode = this.instance.memoryMode.getValue().intValue();
        this.buttonList.add(new GuiButton(1021, this.width / 8 + 200, this.height / 7 + 15, 80, 20, "Toggle Memory"));
        this.memoryRed = new GuiSlider(1022, this.width / 8 + 200, this.height / 7 + 45, 80, 20, "Red: ", "", 0, 255, mrgba >> 16 & 0xFF, false, true);
        this.memoryGreen = new GuiSlider(1023, this.width / 8 + 200, this.height / 7 + 75, 80, 20, "Green: ", "", 0, 255, mrgba >> 8 & 0xFF, false, true);
        this.memoryBlue = new GuiSlider(1024, this.width / 8 + 200, this.height / 7 + 105, 80, 20, "Blue: ", "", 0, 255, mrgba & 0xFF, false, true);
        this.memoryAlpha = new GuiSlider(1025, this.width / 8 + 200, this.height / 7 + 135, 80, 20, "Alpha: ", "", 1, 255, mrgba >> 24 & 0xFF, false, true);
        this.memoryModes = new GuiSlider(1026, this.width / 8 + 200, this.height /7 + 165, 80, 20, "Mode: ", "", 1, 4, mmode, false, true);
        this.buttonList.add(new GuiButton(1027, this.width / 8 + 200, this.height / 7 + 195, 80, 20, "Reset Memory"));
        this.buttonList.add(this.memoryRed);
        this.buttonList.add(this.memoryGreen);
        this.buttonList.add(this.memoryBlue);
        this.buttonList.add(this.memoryAlpha);
        this.buttonList.add(this.memoryModes);

        int asmode = this.instance.armorMode.getValue().intValue();
        this.buttonList.add(new GuiButton(1028, this.width / 8 + 280, this.height / 7 + 15, 120, 20, "Toggle Armor Status"));
        this.buttonList.add(new GuiButton(1029, this.width / 8 + 280, this.height / 7 + 45, 120, 20, "Toggle Hand Status"));
        this.armorModes = new GuiSlider(1030, this.width / 8 + 280, this.height /  7 + 75, 120, 20, "Mode: ", "", 1, 2, asmode, false, true);
        this.buttonList.add(new GuiButton(1031, this.width / 8 + 280, this.height / 7 + 105, 120, 20, "Reset Armor Status"));
        this.buttonList.add(this.armorModes);

        this.buttonList.add(new GuiButton(1032, this.width / 8 + 400, this.height / 7 + 15, 120, 20, "Toggle Potion Status"));
        super.initGui();

        this.buttonList.add(new GuiButton(1033, this.width / 8 + 520, this.height / 7 + 15, 100, 20, "Toggle Full Bright"));
        super.initGui();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();

        if (this.instance.getGuiFPS().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "FPS: ON", this.width / 8, this.height / 7, this.instance.fpsColor.getValue().intValue());
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "FPS: OFF", this.width / 8, this.height / 7, this.instance.fpsColor.getValue().intValue());
        }

        if (this.instance.getGuiCPS().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "CPS: ON", this.width / 8 + 80, this.height / 7, this.instance.cpsColor.getValue().intValue());
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "CPS: OFF", this.width / 8 + 80, this.height / 7, this.instance.cpsColor.getValue().intValue());
        }

        if (this.instance.getGuiPing().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "Ping: ON", this.width / 8 + 160, this.height / 7, this.instance.pingColor.getValue().intValue());
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "Ping: OFF", this.width / 8 + 160, this.height / 7, this.instance.pingColor.getValue().intValue());
        }

        if (this.instance.getGuiMemory().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "Memory: ON", this.width / 8 + 240, this.height / 7, this.instance.memoryColor.getValue().intValue());
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "Memory: OFF", this.width / 8 + 240, this.height / 7, this.instance.memoryColor.getValue().intValue());
        }

        if (this.instance.getGuiArmorStatus().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "Armor Status: ON", this.width / 8 + 340, this.height / 7, -1);
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "Armor Status: OFF", this.width / 8 + 340, this.height / 7, -1);
        }

        if (this.instance.getGuiPotionStatus().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "Potion Status: ON", this.width / 8 + 460, this.height / 7, -1);
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "Potion Status: OFF", this.width / 8 + 460, this.height / 7, -1);
        }

        if (this.instance.getGuiFullBright().isEnabled())
        {
            this.drawCenteredString(this.mc.fontRenderer, "Full Bright: ON", this.width / 8 + 570, this.height / 7, -1);
        }
        else
        {
            this.drawCenteredString(this.mc.fontRenderer, "Full Bright: OFF", this.width / 8 + 570, this.height / 7, -1);
        }

        this.update();
        for (DragGUI guis : this.instance.getDragGUIs())
        {
            guis.setScale(this.instance.scale.getValue().floatValue());
        }
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
                this.instance.fpsColor.setValue(-1);
                break;
            case 1007:
                this.instance.getGuiCPS().setEnabled(!this.instance.getGuiCPS().isEnabled());
                break;
            case 1013:
                this.instance.getGuiCPS().setEnabled(true);
                this.instance.getGuiCPS().setPosY(10);
                this.instance.getGuiCPS().setPosX(130);
                this.instance.cpsColor.setValue(-1);
                break;
            case 1014:
                this.instance.getGuiPing().setEnabled(!this.instance.getGuiPing().isEnabled());
                break;
            case 1020:
                this.instance.getGuiPing().setEnabled(true);
                this.instance.getGuiPing().setPosY(15);
                this.instance.getGuiPing().setPosX(130);
                this.instance.pingColor.setValue(-1);
                break;
            case 1021:
                this.instance.getGuiMemory().setEnabled(!this.instance.getGuiMemory().isEnabled());
                break;
            case 1027:
                this.instance.getGuiMemory().setEnabled(true);
                this.instance.getGuiMemory().setPosY(20);
                this.instance.getGuiMemory().setPosX(130);
                this.instance.memoryColor.setValue(-1);
                break;
            case 1028:
                this.instance.getGuiArmorStatus().setEnabled(!this.instance.getGuiArmorStatus().isEnabled());
                break;
            case 1029:
                this.instance.armorAddItemInHand.setValue(!this.instance.armorAddItemInHand.getValue().booleanValue());
                break;
            case 1031:
                this.instance.getGuiArmorStatus().setEnabled(true);
                this.instance.getGuiArmorStatus().setPosY(25);
                this.instance.getGuiArmorStatus().setPosX(130);
                this.instance.armorAddItemInHand.setValue(true);
                this.instance.armorMode.setValue(1);
                break;
            case 1032:
                this.instance.getGuiPotionStatus().setEnabled(!this.instance.getGuiPotionStatus().isEnabled());
                break;
            case 1033:
                this.instance.getGuiFullBright().setEnabled(!this.instance.getGuiFullBright().isEnabled());
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

        int fr, fg, fb, fa, fm;
        fr = this.fpsRed.getValueInt();
        fg = this.fpsGreen.getValueInt();
        fb = this.fpsBlue.getValueInt();
        fa = this.fpsAlpha.getValueInt();
        fm = this.fpsModes.getValueInt();
        this.instance.fpsColor.setValue(new Color(fr, fg, fb, fa).getRGB());
        this.instance.fpsMode.setValue(fm);

        int cr, cg, cb, ca, cm;
        cr = this.cpsRed.getValueInt();
        cg = this.cpsGreen.getValueInt();
        cb = this.cpsBlue.getValueInt();
        ca = this.cpsAlpha.getValueInt();
        cm = this.cpsModes.getValueInt();
        this.instance.cpsColor.setValue(new Color(cr, cg, cb, ca).getRGB());
        this.instance.cpsMode.setValue(cm);

        int pr, pg, pb, pa, pm;
        pr = this.pingRed.getValueInt();
        pg = this.pingGreen.getValueInt();
        pb = this.pingBlue.getValueInt();
        pa = this.pingAlpha.getValueInt();
        pm = this.pingModes.getValueInt();
        this.instance.pingColor.setValue(new Color(pr, pg, pb, pa).getRGB());
        this.instance.pingMode.setValue(pm);

        int mr, mg, mb, ma, mm;
        mr = this.memoryRed.getValueInt();
        mg = this.memoryGreen.getValueInt();
        mb = this.memoryBlue.getValueInt();
        ma = this.memoryAlpha.getValueInt();
        mm = this.memoryModes.getValueInt();
        this.instance.memoryColor.setValue(new Color(mr, mg, mb, ma).getRGB());
        this.instance.memoryMode.setValue(mm);

        int asm;
        asm = this.armorModes.getValueInt();
        this.instance.armorMode.setValue(asm);

        this.instance.scale.setValue((float) this.scale.getValueInt() / 100.0f);
    }
}
