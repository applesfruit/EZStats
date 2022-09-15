package me.applesfruit.ezstats.gui.settings;

import me.applesfruit.ezstats.EZStats;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class SettingsGUI extends GuiScreen {

    private GuiTextField windowName;
    private EZStats instance;

    public SettingsGUI(EZStats instance)
    {
        this.instance = instance;
    }

    @Override
    public void initGui() {
        this.windowName = new GuiTextField(this.fontRendererObj, this.width / 8 - 50, this.height / 3, 100, 20);
        this.windowName.setMaxStringLength(32);
        this.windowName.setText(this.instance.windowName);
        this.windowName.setFocused(true);
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        this.windowName.drawTextBox();
        this.drawCenteredString(this.fontRendererObj, "Change Window Name", this.width / 8, this.height / 3 - 20, -1);
        update();
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.windowName.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_) {
        super.keyTyped(p_73869_1_, p_73869_2_);
        this.windowName.textboxKeyTyped(p_73869_1_, p_73869_2_);
    }

    @Override
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }

    @Override
    public void onGuiClosed() {
        this.windowName.setFocused(false);
        this.instance.save();
        this.instance.load();
    }

    private void update()
    {
        String dn;
        dn = this.windowName.getText();
        this.instance.windowName = dn;
    }
}
