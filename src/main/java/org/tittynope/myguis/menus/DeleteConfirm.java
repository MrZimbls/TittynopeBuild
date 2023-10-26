package org.tittynope.myguis.menus;

import org.tittynope.handler.WorldDeleteHandler;
import org.tittynope.handler.WorldLoadHandler;
import org.tittynope.myguis.Menu;
import org.tittynope.myguis.PlayerGuiData;
import org.tittynope.utils.CustomSkulls;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeleteConfirm extends Menu {
    public DeleteConfirm(PlayerGuiData playerGuiData) {
        super(playerGuiData);
    }

    @Override
    public String getGuiName() {
        String mname = playerGuiData.getMaptoedit().getMapname();
        if (mname.length() > 5){
            mname = mname.substring(0, 5) + "...";
        }
        return (ChatColor.BOLD.toString() + ChatColor.DARK_RED + "Delete Map" + ChatColor.DARK_GRAY + " => " + ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + mname);
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public void handleGui(InventoryClickEvent e) {
        String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase();
        Player p = (Player) e.getWhoClicked();

        switch (itemName){
            case "DELETE":
                WorldLoadHandler.lodIfNot(playerGuiData.getMaptoedit().getMapname());
                p.sendMessage(ChatColor.RED + playerGuiData.getMaptoedit().getMapname() + " deleted!");
                WorldDeleteHandler.deleteMap(playerGuiData.getMaptoedit(), p);
                p.closeInventory();
                break;
            case "BACK":
                playerGuiData.getParent().open();
                break;
        }
    }

    @Override
    public void setGuiItems() {

        setFillerGlass();

        ItemStack confirm = CustomSkulls.TRASH_RED.getSkull();
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "DELETE");
        confirm.setItemMeta(confirmMeta);
        inventory.setItem(3, confirm);

        ItemStack back = CustomSkulls.ARROW_LEFT_BLACK.getSkull();
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.DARK_GREEN + "Back");
        back.setItemMeta(backMeta);
        inventory.setItem(5, back);

    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
