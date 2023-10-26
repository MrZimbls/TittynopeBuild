package org.tittynope.myguis.menus;

import org.tittynope.TittynopeBuild;
import org.tittynope.myguis.PagedMenu;
import org.tittynope.myguis.PlayerGuiData;
import org.tittynope.utils.CustomSkulls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class BuilderMenu extends PagedMenu {

    private ArrayList<UUID> players;
    private ArrayList<UUID> builder;

    public BuilderMenu(PlayerGuiData playerGuiData) {
        super(playerGuiData);
    }

    @Override
    public String getGuiName() {
        return "Manage Builder on " + playerGuiData.getMaptoedit().getMapname();
    }

    @Override
    public int getRows() {
        return 6;
    }

    @Override
    public void handleGui(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {

            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Previous")){
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
            }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next")){
                if (!((index + 1) >= players.size())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
            } else {
                ArrayList<String> lore = new ArrayList<>(e.getCurrentItem().getItemMeta().getLore());
                int index = Integer.parseInt(ChatColor.stripColor(lore.get(lore.size() - 1)));
                if (!lore.isEmpty()){
                    if (builder.contains(players.get(index))) {
                        playerGuiData.getMaptoedit().removeBuilder(players.get(index));
                        p.sendMessage(ChatColor.YELLOW + Bukkit.getOfflinePlayer(players.get(index)).getName() + " is no longer Builder on this Map!");
                        new BuilderMenu(playerGuiData).open();
                    } else {
                        playerGuiData.getMaptoedit().addBuilder(players.get(index));
                        p.sendMessage(ChatColor.GREEN + Bukkit.getOfflinePlayer(players.get(index)).getName() + " is now Builder on this Map!");
                        new BuilderMenu(playerGuiData).open();
                    }
                }
            }
        }else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
            playerGuiData.getParent().open();
        }
    }

    @Override
    public void setGuiItems() {

        addMenuBorder();

        players = new ArrayList<>(TittynopeBuild.getServerPlayer().keySet());
        builder = new ArrayList<>(playerGuiData.getMaptoedit().getBuilder());

        if (players != null && !players.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;

                if (index >= players.size()) break;
                if (players.get(index) != null) {
                    //Player ITEM _____________________

                    if (builder.contains(players.get(index))){
                        ItemStack playerItem = new ItemStack(CustomSkulls.YELLOW_CHECK.getSkull());
                        ItemMeta playerItemMeta = playerItem.getItemMeta();
                        playerItemMeta.setDisplayName(ChatColor.GOLD + Bukkit.getOfflinePlayer(players.get(index)).getName());
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(ChatColor.DARK_GRAY + String.valueOf(index));
                        playerItemMeta.setLore(lore);
                        playerItem.setItemMeta(playerItemMeta);
                        inventory.addItem(playerItem);
                    } else {
                        ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                        SkullMeta playerItemMeta = (SkullMeta) playerItem.getItemMeta();
                        playerItemMeta.setDisplayName(ChatColor.GOLD + Bukkit.getOfflinePlayer(players.get(index)).getName());
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(ChatColor.DARK_GRAY + String.valueOf(index));
                        playerItemMeta.setLore(lore);
                        playerItemMeta.setOwner(Bukkit.getOfflinePlayer(players.get(index)).getName());
                        playerItem.setItemMeta(playerItemMeta);
                        inventory.addItem(playerItem);
                    }
                    index++;
                    //Player ITEM END _________________
                }
            }
        }

    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
