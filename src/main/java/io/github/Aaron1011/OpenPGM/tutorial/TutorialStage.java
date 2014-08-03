package io.github.Aaron1011.OpenPGM.tutorial;

import io.github.Aaron1011.OpenPGM.region.PointProvider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class TutorialStage {
	public String title;
	public List<String> lines;
	public Location location;
	
	public TutorialStage() {
		this.title = "";
		this.lines = new ArrayList<String>();
		this.location = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
	}
	
	public TutorialStage(String title) {
		this.lines = new ArrayList<String>();
		this.title = title;
		this.location = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
	}
	
	public TutorialStage(List<String> lines) {
		this.lines = new ArrayList<String>();
		this.lines = lines;
		this.location = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
	}
	
	public TutorialStage(String title, List<String> lines, Location location) {
		this.title = title;
		this.lines = lines;
		this.location = location;
	}
	
	public void addLine(String line) {
		this.lines.add(line);
	}
}