package org.tittynope.handler;

import org.tittynope.TittynopeBuild;
import org.tittynope.data.Map;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class WorldGernerateHandler {

    private final WorldCreator wc;
    private final Player player;
    private final String name;

    public WorldGernerateHandler(String name, Player player){
        wc = new WorldCreator(name);
        this.name = name;
        this.player = player;
    }

    public World voidWorld(){
        wc.type(WorldType.FLAT);
        wc.generator(new VoidChunkGenerator());
        World world = wc.createWorld();
        world.setSpawnLocation(0, 60, 0);
        setSpawnBlock(world);
        createMapObject("VOID");
        world.setGameRuleValue("doFireTick", "false");
        return world;
    }

    public World voidWorldHell(){
        wc.type(WorldType.FLAT);
        wc.generator(new VoidChunkGenerator());
        wc.environment(World.Environment.NETHER);
        World world = wc.createWorld();
        world.setSpawnLocation(0, 60, 0);
        setSpawnBlock(world);
        createMapObject("VOID");
        return world;
    }

    public World voidWorldEnd(){
        wc.type(WorldType.FLAT);
        wc.generator(new VoidChunkGenerator());
        wc.environment(World.Environment.THE_END);
        World world = wc.createWorld();
        world.setSpawnLocation(0, 60, 0);
        setSpawnBlock(world);
        createMapObject("VOID");
        return world;
    }

    private void setSpawnBlock(World world){
        Location loc = new Location(world, 0, 59, 0);
        world.getBlockAt(loc).setType(Material.BEDROCK);
    }

    private void createMapObject(String generator){
        Map map = new Map(name, player.getUniqueId());
        TittynopeBuild.addMaptoList(map);
        map.setGenerator(generator);
    }
}