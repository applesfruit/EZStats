package me.applesfruit.ezstats.gui.components;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class CPS {

    private List<Long> left = new ArrayList<Long>();
    private long llast;
    private boolean lwas;
    private List<Long> right = new ArrayList<Long>();
    private long rlast;
    private boolean rwas;

    @SubscribeEvent
    public void onClick(InputEvent.MouseInputEvent event)
    {
        String left = Mouse.getButtonName(0);
        String right = Mouse.getButtonName(1);

        boolean lpressed = Mouse.isButtonDown(0);
        boolean r = Mouse.isButtonDown(Mouse.getEventButton());

        if (Mouse.getButtonName(Mouse.getEventButton()) == left)
        {
            if (lpressed != lwas)
            {
                llast = System.currentTimeMillis();
                lwas = lpressed;
                if (lpressed)
                {
                    this.left.add(llast);
                }
            }
        }

        if (Mouse.getButtonName(Mouse.getEventButton()) == right)
        {
            System.out.println("RIGHT");
        }

    }

    public int getRight()
    {
        long current = System.currentTimeMillis();
        right.removeIf(aLong -> aLong + 1000 < current);
        return right.size();
    }

    public int getLeft()
    {
        long current = System.currentTimeMillis();
        left.removeIf(aLong -> aLong + 1000 < current);
        return left.size();
    }

    public static CPS getInstance()
    {
        return new CPS();
    }
}
