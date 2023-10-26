package org.tittynope.data;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Data implements Serializable {
    private static transient final long serialVersionUID = -4302548013838943656L;
    public final HashSet<Map> allmaps;
    public final HashMap<UUID, String> allplayer;

    public Data(HashSet<Map> maplist, HashMap<UUID, String>playerlist){
        this.allmaps = maplist;
        this.allplayer = playerlist;
    }

    public Data(Data loadedData){
        this.allmaps = loadedData.allmaps;
        this.allplayer = loadedData.allplayer;
    }

    public boolean saveData(String filePath){
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashSet<Map> getMapList(){
        return allmaps;
    }

    public HashMap<UUID, String> getAllplayer(){
        return allplayer;
    }
}
