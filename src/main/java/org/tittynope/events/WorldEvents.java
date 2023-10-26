package org.tittynope.events;

import org.tittynope.TittynopeBuild;
import org.tittynope.handler.WorldLoadHandler;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldEvents implements Listener {

    @EventHandler
    public static void onWorldChange(PlayerChangedWorldEvent e){
        WorldLoadHandler.unloademptyWorlds();
    }

    @EventHandler
    public void onBlockBrake(BlockBreakEvent e){
        Player p = e.getPlayer();
        World w = p.getWorld();
        if (TittynopeBuild.getMapHashMap().containsKey(w.getName())) {
            if (!TittynopeBuild.checkPlayerAccess(TittynopeBuild.getMapbyName(w.getName()), p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        World w = p.getWorld();
        if (TittynopeBuild.getMapHashMap().containsKey(w.getName())) {
            if (!TittynopeBuild.checkPlayerAccess(TittynopeBuild.getMapbyName(w.getName()), p)) {
                e.setCancelled(true);
            }
        }
    }
}
