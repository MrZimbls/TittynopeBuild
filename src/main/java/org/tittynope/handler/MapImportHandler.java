package org.tittynope.handler;

import org.tittynope.TittynopeBuild;
import org.tittynope.data.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MapImportHandler {
    public static void importFromFolder(String name, Player p) throws IOException {
        if (TittynopeBuild.getMapHashMap().containsKey(name)){
            p.sendMessage(ChatColor.RED + "Map already exitists!");
            return;
        }
        String copied = Bukkit.getServer().getWorldContainer().toString() + "/" + name;
        String originalPath = "plugins/TittynopeBuild/Import/" + name;

        Files.walk(Paths.get(originalPath))
                .forEach(source -> {
                    Path destination = Paths.get(copied, source.toString()
                            .substring(originalPath.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        Map tempMap = new Map(name, p.getUniqueId());
        tempMap.setGenerator("VOID");
        TittynopeBuild.addMaptoList(tempMap);

        WorldCreator wc = new WorldCreator(name);
        wc.generator(new VoidChunkGenerator());
        wc.type(WorldType.FLAT);
        Bukkit.getServer().createWorld(wc);
    }
}
