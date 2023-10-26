package org.tittynope.handler;

import org.tittynope.TittynopeBuild;
import org.tittynope.data.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class WorldDeleteHandler {

    public static void deleteMap(Map map, Player player){
        if (deletePermission(map, player)){
            Path path = Paths.get(Bukkit.getServer().getWorldContainer().toString(), map.getMapname());
            for (Player p : Bukkit.getWorld(map.getMapname()).getPlayers()){
                TittynopeBuild.sendToSpawn(p);
            }
            Bukkit.unloadWorld(map.getMapname(), false);
            TittynopeBuild.removeMap(map);
            try {
                deleteDirectoryStream(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteDirectoryStream(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public static boolean deletePermission(Map map, Player player){
        if (player.hasPermission("TittynopeBuild.map.admin")) return true;
        else if (map.getOwner().equals(player.getUniqueId())) return true;
        else return false;
    }
}
