package org.tittynope.myguis.menus;

import org.tittynope.TittynopeBuild;
import org.tittynope.handler.WorldDeleteHandler;
import org.tittynope.myguis.Menu;
import org.tittynope.myguis.PlayerGuiData;
import org.tittynope.utils.CustomSkulls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EditMenu extends Menu {
    private static final String lightRule = "doDaylightCycle";

    public EditMenu(PlayerGuiData playerGuiData) {
        super(playerGuiData);
        playerGuiData.setMaptoedit(TittynopeBuild.getMapbyName(playerGuiData.getOwner().getWorld().getName()));
    }

    @Override
    public String getGuiName() {
        return "Map Options";
    }

    @Override
    public int getRows() {
        return 3;
    }

    @Override
    public void handleGui(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!TittynopeBuild.checkPlayerAccess(playerGuiData.getMaptoedit(), p)) return;

        String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase();

        switch (itemName){
            case "DELETE":
                if (WorldDeleteHandler.deletePermission(playerGuiData.getMaptoedit(), p)){
                    playerGuiData.setParent(this);
                    new DeleteConfirm(playerGuiData).open();
                } else {
                    p.sendMessage(ChatColor.RED + "You can't delete this Map!");
                }
                break;
            case "BUILDER":
                playerGuiData.setParent(this);
                new BuilderMenu(playerGuiData).open();
                break;
            case "LOCK TIME":
                if (Bukkit.getWorld(playerGuiData.getMaptoedit().getMapname()).getGameRuleValue(lightRule).equals("false")){
                    Bukkit.getWorld(playerGuiData.getMaptoedit().getMapname()).setGameRuleValue(lightRule, "true");
                    p.sendMessage("Unlocked time on Map");
                } else {
                    if (!Bukkit.getWorld(playerGuiData.getMaptoedit().getMapname()).isGameRule(lightRule)){
                        p.sendMessage("is no gamerule");
                    }
                    Bukkit.getWorld(playerGuiData.getMaptoedit().getMapname()).setGameRuleValue(lightRule, "false");
                    p.sendMessage("Locked time on Map");
                }
                new EditMenu(playerGuiData).open();
                break;
            case "LOCK WEATHER":
                playerGuiData.getMaptoedit().getFlag("BlockWeather").switchFlag();
                p.sendMessage("Block Weather changed");
                new EditMenu(playerGuiData).open();
                break;
            case "BLOCKPHYSICS":
                playerGuiData.getMaptoedit().getFlag("BlockPhysic").switchFlag();
                p.sendMessage(ChatColor.YELLOW + "Block physics changed!");
                new EditMenu(playerGuiData).open();
                break;
            case "CHANGE OWNER":
                if ((p.hasPermission("TittynopeBuild.map.admin")) || (playerGuiData.getMaptoedit().getOwner().equals(p.getUniqueId()))){
                    playerGuiData.setParent(this);
                    new ChangeOwner(playerGuiData).open();
                } else {
                    p.sendMessage(ChatColor.RED + "You are not the owner of this map!");
                }
                break;
        }
    }

    @Override
    public void setGuiItems() {

        ItemStack delete = CustomSkulls.TRASH_RED.getSkull();
        ItemMeta deleteMeta = delete.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.DARK_RED + "Delete");
        delete.setItemMeta(deleteMeta);
        inventory.setItem(26, delete);

        ItemStack timerule = CustomSkulls.CLOCK.getSkull();
        ItemMeta timeruleMeta = timerule.getItemMeta();
        timeruleMeta.setDisplayName(ChatColor.GOLD + "Lock Time");
        ArrayList<String> lore = new ArrayList<>();
        if (Bukkit.getWorld(playerGuiData.getMaptoedit().getMapname()).getGameRuleValue(lightRule).equals("false")){
            lore.add(ChatColor.RED + "LOCKED");
        } else {
            lore.add(ChatColor.GREEN + "UNLOCKED");
        }
        lore.add(ChatColor.AQUA + "Locks/unlocks the");
        lore.add(ChatColor.AQUA + "time on the map");
        timeruleMeta.setLore(lore);
        timerule.setItemMeta(timeruleMeta);
        inventory.setItem(13, timerule);

        ItemStack builder = CustomSkulls.BUILDER.getSkull();
        ItemMeta builderMeta = builder.getItemMeta();
        builderMeta.setDisplayName(ChatColor.GOLD + "Builder");
        lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "Add/Remove Builder");
        lore.add(ChatColor.AQUA + "from Map");
        builderMeta.setLore(lore);
        builder.setItemMeta(builderMeta);
        inventory.setItem(10, builder);

        ItemStack weather = CustomSkulls.CLOUDS.getSkull();
        ItemMeta weatherMeta = weather.getItemMeta();
        weatherMeta.setDisplayName(ChatColor.GOLD + "Lock Weather");
        lore = new ArrayList<>();
        if (playerGuiData.getMaptoedit().getFlag("BlockWeather").getFlagValue()){
            lore.add(ChatColor.RED + "LOCKED");
        } else {
            lore.add(ChatColor.GREEN + "UNLOCKED");
        }
        lore.add(ChatColor.AQUA + "en/disable physics on");
        lore.add(ChatColor.AQUA + "blocks like Gravel");
        weatherMeta.setLore(lore);
        weather.setItemMeta(weatherMeta);
        inventory.setItem(12, weather);

        ItemStack blockPhy = CustomSkulls.FLAG.getSkull();
        ItemMeta blockPhyMeta = blockPhy.getItemMeta();
        blockPhyMeta.setDisplayName(ChatColor.GOLD + "BlockPhysics");
        lore = new ArrayList<>();
        if (playerGuiData.getMaptoedit().getFlag("BlockPhysic").getFlagValue()){
            lore.add(ChatColor.RED + "DISABLED");
        } else {
            lore.add(ChatColor.GREEN + "ENABLED");
        }
        lore.add(ChatColor.AQUA + "en/disable physics on");
        lore.add(ChatColor.AQUA + "blocks like Gravel");
        blockPhyMeta.setLore(lore);
        blockPhy.setItemMeta(blockPhyMeta);
        inventory.setItem(16, blockPhy);

        ItemStack owner = CustomSkulls.RED_O.getSkull();
        ItemMeta ownerMeta = owner.getItemMeta();
        ownerMeta.setDisplayName(ChatColor.GOLD + "Change Owner");
        lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "Changes the owner");
        lore.add(ChatColor.AQUA + "of a map");
        ownerMeta.setLore(lore);
        owner.setItemMeta(ownerMeta);
        inventory.setItem(11, owner);

    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
