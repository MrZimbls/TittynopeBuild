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
    public static void importFromFolder(String name, Player p, WorldType worldType, Long seed) throws IOException {
        if (TittynopeBuild.getMapHashMap().containsKey(name)) {
            p.sendMessage(ChatColor.RED + "Map already exists!");
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

        WorldCreator wc = new WorldCreator(name);

        if (worldType == null) {
            tempMap.setGenerator("VOID");
            wc.generator(new VoidChunkGenerator());
            wc.type(WorldType.FLAT);
        } else {
            wc.type(worldType);
            if (seed != null) {
                wc.seed(seed);
            }
        }

        TittynopeBuild.addMaptoList(tempMap);
        Bukkit.getServer().createWorld(wc);
    }

}
