package net.goosix;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class CoordsUtil {

    public static Location toLocationFromString(String stringLocation) {
        String[] splitted = stringLocation.split(";");
        return new Location(Bukkit.getWorld(splitted[0]),
                Double.parseDouble(splitted[1]),
                Double.parseDouble(splitted[2]),
                Double.parseDouble(splitted[3]));
    }

}
