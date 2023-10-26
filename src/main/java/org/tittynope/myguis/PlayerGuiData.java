package org.tittynope.myguis;

import org.tittynope.data.Map;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerGuiData {

    private Player owner;
    private Map maptoedit;
    private Menu parent;
    private UUID playerToLook;
    private boolean lookTicket = true;

    public PlayerGuiData(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public Map getMaptoedit() {
        return maptoedit;
    }

    public void setMaptoedit(Map maptoedit) {
        this.maptoedit = maptoedit;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public UUID getPlayerToLook() {
        return playerToLook;
    }

    public void setPlayerToLook(UUID playerToLook) {
        this.playerToLook = playerToLook;
    }

    public boolean isLookTicket() {
        return lookTicket;
    }

    public void setLookTicket(boolean lookTicket) {
        this.lookTicket = lookTicket;
    }
}
