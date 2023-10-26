package org.tittynope.myguis.menus;

import org.tittynope.TittynopeBuild;
import org.tittynope.data.Map;
import org.tittynope.myguis.PagedMenu;
import org.tittynope.myguis.PlayerGuiData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class WorldList extends PagedMenu{

    private ArrayList<Map> maps;

    public WorldList(PlayerGuiData playerGuiData) {
        super(playerGuiData);

    }

    @Override
    public String getGuiName() {
        return "List of all Maps";
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
                if (!((index + 1) >= maps.size())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
            }

            ArrayList<String> lore = new ArrayList<>(e.getCurrentItem().getItemMeta().getLore());
            int index = Integer.parseInt(ChatColor.stripColor(lore.get(lore.size() - 1)));
            if (!lore.isEmpty()){
                p.sendMessage(ChatColor.DARK_GRAY + "Teleporting to " + maps.get(index).getMapname());
                maps.get(index).teleportToMap(p);
            }

        }else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
            //close inventory
            p.closeInventory();
        }
    }

    @Override
    public void setGuiItems() {
        addMenuBorder();

        maps = new ArrayList<>(TittynopeBuild.getMapList());

        if (maps != null && !maps.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;

                if (index >= maps.size()) break;
                if (maps.get(index) != null) {
                    //MAP ITEM _____________________
                    ItemStack mapItem = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                    SkullMeta mapItemMeta = (SkullMeta) mapItem.getItemMeta();

                    mapItemMeta.setDisplayName(ChatColor.GOLD + maps.get(index).getMapname());

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.DARK_AQUA + Bukkit.getOfflinePlayer(maps.get(index).getOwner()).getName());
                    lore.add(ChatColor.DARK_GRAY + String.valueOf(index));
                    mapItemMeta.setLore(lore);

                    mapItemMeta.setOwner(Bukkit.getOfflinePlayer(maps.get(index).getOwner()).getName());

                    mapItem.setItemMeta(mapItemMeta);
                    inventory.addItem(mapItem);
                    index++;
                    //MAP ITEM END _________________
                }
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
