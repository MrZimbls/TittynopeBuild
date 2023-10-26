package org.tittynope.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

public class SkullBuilder {

    /* Since version 1.16.1, UUIDs in NBT tags are no longer serialised as strings,
       but as an int array of 4 x 32 bits, going from most significant to least significant
       bits of the UUID. */
    private static final boolean newStorageSystem;

    static {
        String versionString = Bukkit.getBukkitVersion();
        int[] version = Arrays.stream(versionString.substring(0, versionString.indexOf('-')).split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();
        newStorageSystem = version[0] > 1
                || (version[0] == 1 && version[1] > 16)
                || (version[0] == 1 && version[1] == 16 && version[2] >= 1);
    }

    /**
     * Creates a skull item stack that uses the given base64-encoded texture
     *
     * @param texture The texture value. Can be found on e.g. https://minecraft-heads.com/custom-heads/
     *                in the "Value" field.
     * @return an ItemStack with this texture.
     */
    public static ItemStack create(String texture) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

        // Assuming you have some method to set the texture to the skull.
        // This method might be specific to the library or plugin you're using.
        setTextureToSkullMeta(skullMeta, texture);

        item.setItemMeta(skullMeta);
        return item;
    }

    private static void setTextureToSkullMeta(SkullMeta skullMeta, String texture) {
        // Implement the method to set the texture to the SkullMeta.
        // The implementation is dependent on the library or plugin you're using.
    }
}
