package me.applesfruit.ezstats.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.applesfruit.ezstats.gui.HUD;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderEvent {

    // This class is used to render everything onto the user's screen.

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event)
    {
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }

        new HUD(Minecraft.getMinecraft());
    }

}
