package net.civicraft.hospitalLocator;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class Messages {
    public static final TextColor PRIMARY = TextColor.color(136, 13, 30);
    public static final TextColor SECONDARY = TextColor.color(242, 106, 141);

    public static final Component PREFIX = Component.text("[CiviMed] ").color(PRIMARY);
    public static final Component NO_PERMISSION = PREFIX.append(Component.text("You do not have permission to perform this command").color(SECONDARY));

    public static final Component NOT_PLAYER = PREFIX.append(Component.text("Only players can run this command.").color(SECONDARY));
    public static final Component INCORRECT_USAGE = PREFIX.append(Component.text("Incorrect usage. /hospital <add|del> <name>").color(SECONDARY));

    public static final Component HOSPITAL_ADDED = PREFIX.append(Component.text("Hospital added!").color(SECONDARY));
    public static final Component HOSPITAL_REMOVED = PREFIX.append(Component.text("Hospital removed.").color(SECONDARY));
    public static final Component HOSPITAL_UPDATED = PREFIX.append(Component.text("Hospital updated!").color(SECONDARY));
    public static final Component HOSPITAL_NOT_FOUND = PREFIX.append(Component.text("Hospital could not be found. Does it exist?").color(SECONDARY));
    public static final Component HOSPITAL_EXISTS = PREFIX.append(Component.text("The specified hospital already exists. Use /hospital update <name> to update the location.").color(SECONDARY));

    public static final Component HOSPITAL_RESPAWN = PREFIX.append(Component.text("An ambulance has brought you to the nearest hospital.").color(SECONDARY));
    public static final Component HOSPITAL_NONE = PREFIX.append(Component.text("The ambulance could not find a hospital, so they just dropped you off here.").color(SECONDARY));

}
