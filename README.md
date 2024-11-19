# HospitalLocator

HospitalLocator is an extracted feature from CiviCraft's health plugin CiviMed. It allows players will the permission `hospital.manage` to add, update, and remove hospitals. When a player dies, the plugin determines the closest hospital and respawns them at the specified location.

## Installation
- Download latest release
- Upload to your plugins folder
- Restart server
- Done!

## Commands
- `/hospital add|set|new <name>` - Adds a new hospital at the location the command sender is standing at.
- `/hospital update <name>` - Updates the specified hospital's location to be at the location the command sender is standing at.
- `/hospital delete|del|remove <name>` - Removes the specified hospital.

**NOTE:** Updates may be made in the future to extend functionality to this extracted version. Also, update `config.yml` manually with caution -- try using `/hospital update <name>` first.