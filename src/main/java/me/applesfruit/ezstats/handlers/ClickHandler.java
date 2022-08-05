package me.applesfruit.ezstats.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.applesfruit.ezstats.gui.components.impl.CPS;
import net.minecraftforge.client.event.MouseEvent;

public class ClickHandler {

    private boolean clickedInTickLeft = false;
    private boolean clickedInTickRight = false;

    @SubscribeEvent
    public void onClick(MouseEvent event)
    {
        if (event.button == 0)
        {
            if (event.buttonstate)
            {
                clickedInTickLeft = true;
                CPS.addLeftClick();
            }
        }
        else if (event.button == 1)
        {
            if (event.buttonstate)
            {
                clickedInTickRight = true;
                CPS.addRightClick();
            }
        }
        else
        {
            return;
        }
    }

}
