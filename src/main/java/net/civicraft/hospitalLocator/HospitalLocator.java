package net.civicraft.hospitalLocator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HospitalLocator extends JavaPlugin implements Listener {
    private static HospitalLocator instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("hospital")).setExecutor(new Hospital());
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("HospitalLocator has been enabled! Paramedics on standby.");
    }

    public static HospitalLocator getInstance() {
        return instance;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location deathLocation = player.getLocation();

        Location closestHospital = getClosestHospital(deathLocation);
        if (closestHospital != null) {
            event.setRespawnLocation(closestHospital);
            player.sendMessage(Messages.HOSPITAL_RESPAWN);
        } else {
            player.sendMessage(Messages.HOSPITAL_NONE);
        }
    }

    private Location getClosestHospital(Location deathLocation) {
        FileConfiguration config = instance.getConfig();
        ConfigurationSection hospitalsSection = config.getConfigurationSection("hospitals");

        if (hospitalsSection == null || hospitalsSection.getKeys(false).isEmpty()) {
            return null;
        }

        Location closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (String hospitalName : hospitalsSection.getKeys(false)) {
            double x = config.getDouble("hospitals." + hospitalName + ".x");
            double y = config.getDouble("hospitals." + hospitalName + ".y");
            double z = config.getDouble("hospitals." + hospitalName + ".z");

            Location hospitalLocation = new Location(deathLocation.getWorld(), x, y, z);
            double distance = deathLocation.distance(hospitalLocation);

            if (distance < closestDistance) {
                closest = hospitalLocation;
                closestDistance = distance;
            }
        }

        return closest;
    }

    @Override
    public void onDisable() {
        getLogger().info("HospitalLocator has been disabled! The paramedics are striking!");
    }
}
