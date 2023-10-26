package org.tittynope.handler;

import org.bukkit.event.Listener;

import java.io.Serializable;

public abstract class MapFlag implements Listener, Serializable {

    public boolean listen;
    public String map;

    public MapFlag(String map, Boolean listen) {
        this.map = map;
        this.listen = listen;
    }

    public void switchFlag(){
        listen = !listen;
    }

    public boolean getFlagValue(){
        return listen;
    }
}
