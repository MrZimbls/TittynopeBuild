package org.tittynope.handler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldLoadHandler {

    public static void unloademptyWorlds(){
        for (World world : Bukkit.getWorlds()){
            if (world.getPlayers().isEmpty()){
                Bukkit.getServer().unloadWorld(world.getName(), true);
            }
        }
    }

    public static boolean worldLoaded(String name){
        for (World world : Bukkit.getServer().getWorlds()){
            if (world.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void lodIfNot(String name){
        if (worldLoaded(name)) return;
        // DOSE NOT CONSIDER WORLD GENERATOR!
        WorldCreator wc = new WorldCreator(name);
        Bukkit.getServer().createWorld(wc);
    }
}
