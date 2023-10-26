package org.tittynope.handler.flags;

import org.tittynope.handler.MapFlag;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BlockWeather extends MapFlag {

    private static final long serialVersionUID = 7923522764804864200L;

    public BlockWeather(String map, Boolean listen) {
        super(map, listen);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        World world = event.getWorld();
        if (world.getName().equals(map) && listen)
            event.setCancelled(true);
    }
}
