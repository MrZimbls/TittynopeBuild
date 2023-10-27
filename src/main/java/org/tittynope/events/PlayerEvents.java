package org.tittynope.events;

import org.tittynope.TittynopeBuild;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.DARK_AQUA + "Welcome! Start with /map!");
        TittynopeBuild.addPlayer(player);
    }
}
