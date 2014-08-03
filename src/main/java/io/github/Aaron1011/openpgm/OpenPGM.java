package io.github.Aaron1011.openpgm;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenPGM extends JavaPlugin {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("loadworld")) {
			if (args.length == 1) {
				WorldLoader.loadWorld(args[0]);
				return true;
			}
		}
		return false;
	}
}
