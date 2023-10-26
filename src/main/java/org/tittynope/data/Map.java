package org.tittynope.data;

import org.tittynope.TittynopeBuild;
import org.tittynope.handler.MapFlag;
import org.tittynope.handler.VoidChunkGenerator;
import org.tittynope.handler.WorldLoadHandler;
import org.tittynope.handler.flags.BlockPhysic;
import org.tittynope.handler.flags.BlockWeather;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Map implements Serializable, Comparable<Map> {

    private static final long serialVersionUID = -252088618122537564L;

    private String mapname;
    private HashSet<UUID> builder = new HashSet<>();
    private UUID owner;
    private String cdate;
    private String generator;
    private HashMap<String, MapFlag> flags = new HashMap<>();

    public Map (String mname, UUID owner){
        this.mapname = mname;
        this.owner = owner;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.cdate = dtf.format(now);
        this.setStandardFlags();
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public HashSet<UUID> getBuilder() {
        return builder;
    }

    public void removeBuilder(UUID uuid){
        builder.remove(uuid);
    }

    public void addBuilder(UUID builder) {
        this.builder.add(builder);
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public HashMap<String, MapFlag> getFlags() {
        return flags;
    }

    public MapFlag getFlag(String flagName){
        return flags.get(flagName);
    }

    public void setFlags(HashMap<String, MapFlag> flags) {
        this.flags = flags;
    }

    @Override
    public int compareTo(Map map){
        return getCdate().compareTo(map.getCdate());
    }

    public void teleportToMap(Player player){
        if (player.hasPermission("TittynopeBuild.map.teleport.bypass") || TittynopeBuild.checkPlayerAccess(this, player)) {
            if (!WorldLoadHandler.worldLoaded(this.mapname)) {
                WorldCreator wc = new WorldCreator(this.mapname);
                if (!this.generator.isEmpty()){
                    if (this.generator.equals("VOID")) {
                        wc.generator(new VoidChunkGenerator());
                    } else {
                        wc.generator(generator);
                    }
                }
                Bukkit.getServer().createWorld(wc);
            }
            player.teleport(Bukkit.getServer().getWorld(this.mapname).getSpawnLocation());
        }
    }

    public void setStandardFlags(){
        flags.put("BlockPhysic", new BlockPhysic(this.mapname, false));
        flags.put("BlockWeather", new BlockWeather(this.mapname, true));
        registerFlagEvents();
    }

    public void registerFlagEvents(){
        for (String flagName : flags.keySet()){
            Bukkit.getServer().broadcast("test", flagName);
            Bukkit.getPluginManager().registerEvents(flags.get(flagName), TittynopeBuild.getPlugin());
        }
    }
}
