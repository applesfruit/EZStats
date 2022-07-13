package me.applesfruit.ezstats.config;

import net.minecraft.client.Minecraft;

import java.io.File;

public class FileManager {

    public static FileManager instance = new FileManager();

    public void createMain()
    {
        System.out.println("Checking for Folders");
        File main = new File(Minecraft.getMinecraft().mcDataDir, "EZStats");
        File components = new File(main, "components");
        if (main.exists()) return;

        main.mkdir();
        components.mkdir();
        System.out.println("Created Folders");
    }

}
