package org.tittynope.handler.flags;

import org.tittynope.handler.MapFlag;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockPhysic extends MapFlag {

    private static final long serialVersionUID = -6329711367109987L;

    public BlockPhysic(String map, Boolean listen) {
        super(map, listen);
    }

    @EventHandler
    public void onPhysic(BlockPhysicsEvent event) {
        World world = event.getBlock().getLocation().getWorld();
        if (world.getName().equals(map) && listen)
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        World world = event.getBlock().getWorld();
        if (world.getName().equals(map) && event.getEntityType() == EntityType.FALLING_BLOCK && event.getTo() == Material.AIR && listen) {
            event.setCancelled(true);
            event.getBlock().getState().update(false);
        }
    }
}
