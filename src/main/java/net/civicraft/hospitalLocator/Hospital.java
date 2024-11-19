package net.civicraft.hospitalLocator;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Hospital implements CommandExecutor {

    private final HospitalLocator instance = HospitalLocator.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.NOT_PLAYER);
            return true;
        }

        if (!player.hasPermission("hospital.manage")) {
            player.sendMessage(Messages.NO_PERMISSION);
            return true;
        }

        if (args.length < 2) {
            player.sendMessage(Messages.INCORRECT_USAGE);
            return true;
        }

        String action = args[0].toLowerCase();
        String hospitalName = args[1];

        switch (action) {
            case "add", "set", "new" -> {
                addHospital(player, hospitalName);
                return true;
            }
            case "delete", "del", "remove" -> {
                deleteHospital(player, hospitalName);
                return true;
            }
            case "update" -> {
                updateHospital(player, hospitalName);
            }
            default -> {
                player.sendMessage(Messages.INCORRECT_USAGE);
                return true;
            }
        }
        return true;
    }

    FileConfiguration config = instance.getConfig();

    private void updateHospital(Player player, String hospitalName) {
        if (config.contains("hospitals." + hospitalName)) {
            Location location = player.getLocation();
            config.set("hospitals." + hospitalName + ".x", location.getBlockX());
            config.set("hospitals." + hospitalName + ".y", location.getBlockY());
            config.set("hospitals." + hospitalName + ".z", location.getBlockZ());
            instance.saveConfig();
            player.sendMessage(Messages.HOSPITAL_UPDATED);
        } else {
            player.sendMessage(Messages.HOSPITAL_NOT_FOUND);
        }
    }

    private void addHospital(Player player, String hospitalName) {
        if (config.contains("hospitals." + hospitalName)) {
            player.sendMessage(Messages.HOSPITAL_EXISTS);
        } else {
            Location location = player.getLocation();

            config.set("hospitals." + hospitalName + ".x", location.getBlockX());
            config.set("hospitals." + hospitalName + ".y", location.getBlockY());
            config.set("hospitals." + hospitalName + ".z", location.getBlockZ());
            instance.saveConfig();

            player.sendMessage(Messages.HOSPITAL_ADDED);
        }
    }

    private void deleteHospital(Player player, String hospitalName) {
        if (config.contains("hospitals." + hospitalName)) {
            config.set("hospitals." + hospitalName, null);
            instance.saveConfig();
            player.sendMessage(Messages.HOSPITAL_REMOVED);
        } else {
            player.sendMessage(Messages.HOSPITAL_NOT_FOUND);
        }
    }
}
