package io.github.Aaron1011.OpenPGM;

import io.github.Aaron1011.OpenPGM.tutorial.TutorialStage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenPGM extends JavaPlugin {
	private PGMWorld world;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("loadworld")) {
			if (args.length == 1) {
				this.world = WorldLoader.loadWorld(args[0]);
				Bukkit.broadcastMessage("Finished world: " + world);
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("tutorial")) {
			if (this.world != null) {
				if (args.length == 1) {
					TutorialStage stage;
					if (args[0].equals("next")) {
						stage = this.world.getTutorial().nextStage();
					}
					else if (args[0].equals("prev")) {
						stage = this.world.getTutorial().prevStage();
					}
					else {
						return false;
					}
					
					if (sender instanceof Player && stage.location != null) {
						((Player) sender).teleport(stage.location);
					}
					
					sender.sendMessage("");
					sender.sendMessage("");
					sender.sendMessage("");
					sender.sendMessage(Util.format("`r   `e`l" + stage.title + "`r"));
					for (String line: stage.lines) {
						sender.sendMessage(line);
					}
					
					return true;
				}
			}
		}
		return false;
	}
}
