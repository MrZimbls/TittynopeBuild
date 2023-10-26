package org.tittynope;


import org.tittynope.commands.MapCommands;
import org.tittynope.data.Data;
import org.tittynope.data.Map;
import org.tittynope.events.MenueventListener;
import org.tittynope.events.PlayerEvents;
import org.tittynope.events.WorldEvents;
import org.tittynope.myguis.PlayerGuiData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class TittynopeBuild extends JavaPlugin {

    private static TittynopeBuild plugin;
    private static HashMap<String, Map> mapHashMap = new HashMap<>();
    private static HashSet<Map> mapList = new HashSet<>();
    private static HashMap<UUID, String> serverPlayer = new HashMap<>();
    private static final HashMap<Player, PlayerGuiData> playerguidatamap = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        File file = new File("plugins/TittynopeBuild");
        if (file.mkdir()) {
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Plugin folder created!");
        }
        file = new File("plugins/TittynopeBuild/data");
        if (file.mkdir()) {
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Data folder created!");
        }
        file = new File("plugins/TittynopeBuild/Import");
        if (file.mkdir()) {
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Import folder created!");
        }

        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new MenueventListener(), this);
        getServer().getPluginManager().registerEvents(new WorldEvents(), this);
        this.getCommand("map").setExecutor(new MapCommands());

        String[] datafiles = file.list();
        if (datafiles.length > 0){
            Data buffer = new Data(Data.loadData("plugins/TittynopeBuild/data/mapsdata.data"));
            mapList = buffer.getMapList();
            serverPlayer = buffer.getAllplayer();

            for (Map map : mapList){
                map.registerFlagEvents();
                mapHashMap.put(map.getMapname(), map);
            }
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Data has been loaded!");
        }

        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        boolean saved = new Data(mapList, serverPlayer).saveData("plugins/TittynopeBuild/data/mapsdata.data");
        if (saved){
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Maps saved successfully!");
        }
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[TittyBuild]: Plugin is disabled!");
    }

    public static void addMaptoList(Map map){
        mapList.add(map);
        mapHashMap.put(map.getMapname(), map);
    }

    public static HashSet<Map> getMapList(){
        return mapList;
    }

    public static HashMap<String, Map> getMapHashMap() {
        return mapHashMap;
    }

    public static Map getMapbyName(String name){
        return mapHashMap.get(name);
    }

    public static boolean checkPlayerAccess(Map map, Player player){
        if (player.hasPermission("TittynopeBuild.map.admin")) return true;
        else if (map.getOwner().equals(player.getUniqueId())) return true;
        else if (map.getBuilder().contains(player.getUniqueId())) return true;
        else return false;
    }

    public static PlayerGuiData getplayerguidata(Player p){
        PlayerGuiData playerGuiData;
        if (!playerguidatamap.containsKey(p)) {
            playerGuiData = new PlayerGuiData(p);
            playerguidatamap.put(p, playerGuiData);
            return playerGuiData;
        } else {
            return playerguidatamap.get(p);
        }
    }

    public static HashSet<Map> getPlayerMaps(UUID uuid){
        HashSet<Map> playerMaps = new HashSet<>();
        for (Map map : mapList){
            if (map.getOwner().equals(uuid)){
                playerMaps.add(map);
            }
        }
        return  playerMaps;
    }

    public static UUID getPlayerUUIDbyName(String name){
        for (UUID id : serverPlayer.keySet()){
            if (serverPlayer.get(id).equalsIgnoreCase(name)){
                return id;
            }
        }
        return null;
    }

    public static void removeMap(Map map){
        mapList.remove(map);
        mapHashMap.remove(map.getMapname());
    }

    public static void addPlayer(Player player){
        if (serverPlayer.containsKey(player.getUniqueId())){
            if (serverPlayer.get(player.getUniqueId()).equals(player.getDisplayName())) return;
            else {
                serverPlayer.replace(player.getUniqueId(), player.getDisplayName());
            }
        } else {
            serverPlayer.put(player.getUniqueId(), player.getDisplayName());
        }
    }

    public static HashMap<UUID, String> getServerPlayer() {
        return serverPlayer;
    }

    public static void sendToSpawn(Player player){
        player.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
    }

    public static TittynopeBuild getPlugin() {
        return plugin;
    }
}
