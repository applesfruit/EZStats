package me.applesfruit.ezstats.gui.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CPS {

    private static List<Long> leftClicks = new ArrayList<Long>();


    private static List<Long> rightClicks = new ArrayList<Long>();

    // Styles
    public static String noTextCPS()
    {
        return getLeftClicks() + " | " + getRightClicks();
    }

    public static String normal()
    {
        return "CPS: " + getLeftClicks() + " | " + getRightClicks();
    }

    public static String backwards()
    {
        return getLeftClicks() + " | " + getRightClicks() + "CPS";
    }

    public static void addLeftClick() {
        leftClicks.add(System.currentTimeMillis());
    }

    public static int getLeftClicks() {
        Iterator<Long> iterator = leftClicks.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() >= System.currentTimeMillis() - 1000L) continue;
            iterator.remove();
        }
        return leftClicks.size();
    }

    public static void addRightClick() {
        rightClicks.add(System.currentTimeMillis());
    }

    public static int getRightClicks() {
        Iterator<Long> iterator = rightClicks.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() >= System.currentTimeMillis() - 1000L) continue;
            iterator.remove();
        }
        return rightClicks.size();
    }

}
