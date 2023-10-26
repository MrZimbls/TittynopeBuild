package org.tittynope.commands;

import org.tittynope.TittynopeBuild;
import org.tittynope.data.Map;
import org.tittynope.handler.MapImportHandler;
import org.tittynope.handler.WorldGernerateHandler;
import org.tittynope.myguis.menus.EditMenu;
import org.tittynope.myguis.menus.PersonalWorlds;
import org.tittynope.myguis.menus.WorldList;
import org.tittynope.myguis.menus.Worlddelete;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class MapCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0){

                plugininfo(player);

            }
            else if (args.length == 1){

                if (args[0].equalsIgnoreCase("listall")){
                    new WorldList(TittynopeBuild.getplayerguidata(player)).open();
                }
                else if (args[0].equalsIgnoreCase("list")){
                    TittynopeBuild.getplayerguidata(player).setPlayerToLook(player.getUniqueId());
                    new PersonalWorlds(TittynopeBuild.getplayerguidata(player)).open();
                }
                else if (args[0].equalsIgnoreCase("delete")){
                    new Worlddelete(TittynopeBuild.getplayerguidata(player)).open();
                }
                else if (args[0].equalsIgnoreCase("setspawn")){
                    player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    player.sendMessage(ChatColor.GREEN + "new map spawn set!");
                }
                else if (args[0].equalsIgnoreCase("create")){
                    plugininfo(player);
                }
                else if (args[0].equalsIgnoreCase("edit")){
                    checkEdit(player);
                }
                else if (args[0].equalsIgnoreCase("info")){
                    mapinfo(player);
                }

            }
            else if (args.length == 2){
                if (args[0].equalsIgnoreCase("create")){
                    if (TittynopeBuild.getMapHashMap().containsKey(args[1])) {
                        player.sendMessage(ChatColor.RED + "Map already exist!");
                    } else {
                        player.sendMessage("Generating new world");
                        World world = new WorldGernerateHandler(args[1], player).voidWorld();
                        player.teleport(world.getSpawnLocation());
                    }
                }
                else if (args[0].equalsIgnoreCase("tp")){
                    if (TittynopeBuild.getMapHashMap().containsKey(args[1])){
                        TittynopeBuild.getMapbyName(args[1]).teleportToMap(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "This map dose not Exist!");
                    }
                }
                else if (args[0].equalsIgnoreCase("listall")){
                    if (TittynopeBuild.getServerPlayer().containsValue(args[1])){
                        TittynopeBuild.getplayerguidata(player).setLookTicket(true);
                        TittynopeBuild.getplayerguidata(player).setPlayerToLook(TittynopeBuild.getPlayerUUIDbyName(args[1]));
                        new PersonalWorlds(TittynopeBuild.getplayerguidata(player)).open();
                    } else {
                        player.sendMessage("Player was never on the server!");
                    }
                }
                else if (args[0].equalsIgnoreCase("import")){
                    try {
                        MapImportHandler.importFromFolder(args[1], player);
                        player.sendMessage(ChatColor.GREEN + "Map imported");
                    } catch (IOException e) {
                        e.printStackTrace();
                        player.sendMessage(ChatColor.RED + "A file with this name dose not exist!");
                    }
                }
            }
            else if (args.length == 3){

            }
        }

        return true;
    }

    private void checkEdit(Player player){
        Map current = TittynopeBuild.getMapbyName(player.getWorld().getName());
        if (player.hasPermission("TittynopeBuild.map.admin")){
            new EditMenu(TittynopeBuild.getplayerguidata(player)).open();
        } else {
            if (current.getOwner().equals(player.getUniqueId())){
                new EditMenu(TittynopeBuild.getplayerguidata(player)).open();
            } else if (current.getBuilder().contains(player.getUniqueId())){
                new EditMenu(TittynopeBuild.getplayerguidata(player)).open();
            } else {
                player.sendMessage(ChatColor.RED + "You cant edit this map!");
            }
        }
    }

    private void plugininfo(Player player){
        player.sendMessage(ChatColor.GRAY + "------------------------");
        player.sendMessage(ChatColor.GOLD + "Buildsystem by Tittynope");
        player.sendMessage(ChatColor.GRAY + "------------------------");
        player.sendMessage(ChatColor.AQUA + "/map create <name>  " + ChatColor.WHITE + ">> Creates void map");
        player.sendMessage(ChatColor.AQUA + "/map listall <player>  " + ChatColor.WHITE + ">> Lists all maps or all from <player>");
        player.sendMessage(ChatColor.AQUA + "/map list  " + ChatColor.WHITE + ">> Lists all own maps");
        player.sendMessage(ChatColor.AQUA + "/map tp <name> " + ChatColor.WHITE + ">> teleports you to given map");
        player.sendMessage(ChatColor.AQUA + "/map delete  " + ChatColor.WHITE + ">> shows all maps you can delete");
        player.sendMessage(ChatColor.AQUA + "/map edit  " + ChatColor.WHITE + ">> opens gui to edit map propertys");
        player.sendMessage(ChatColor.AQUA + "/map import <name> " + ChatColor.WHITE + ">> imports map from import folder");
        player.sendMessage(ChatColor.AQUA + "/map info " + ChatColor.WHITE + ">> displays info about the map you are standing on");
        player.sendMessage(ChatColor.AQUA + "/map setspawn " + ChatColor.WHITE + ">> sets the map spawn where you are standing");
    }

    private void mapinfo(Player player){
        if (TittynopeBuild.getMapHashMap().containsKey(player.getWorld().getName())){
            Map map = TittynopeBuild.getMapbyName(player.getWorld().getName());
            player.sendMessage(ChatColor.GRAY + "------------------------");
            player.sendMessage(ChatColor.GOLD + map.getMapname() + " INFO");
            player.sendMessage(ChatColor.GRAY + "------------------------");
            player.sendMessage(ChatColor.AQUA + "Owner: " + ChatColor.WHITE + TittynopeBuild.getServerPlayer().get(map.getOwner()));
            player.sendMessage(ChatColor.AQUA + "Created: " + ChatColor.WHITE + map.getCdate());
            player.sendMessage(ChatColor.AQUA + "Builder:");
            for (UUID uuid : map.getBuilder()){
                player.sendMessage(ChatColor.WHITE + TittynopeBuild.getServerPlayer().get(uuid));
            }
        }
    }
}
