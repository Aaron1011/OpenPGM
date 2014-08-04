package io.github.Aaron1011.OpenPGM.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class Tutorial {
	private List<TutorialStage> stages;
	private int index;

	public Tutorial() {
		this.stages = new ArrayList<TutorialStage>();
		this.index = 0;
	}
	
	public Tutorial(List<TutorialStage> stages) {
		this.stages = stages;
		this.index = 0;
	}

	public void addStage(TutorialStage stage) {
		this.stages.add(stage);
	}
	
	public void addStageAt(TutorialStage stage, int index) {
		this.stages.add(index, stage);
	}
	
	public TutorialStage nextStage() {
		if (index == this.stages.size() - 1) {
			return this.stages.get(index);
		}
		
		return this.stages.get(index++);
	}
	
	public TutorialStage prevStage() {
		if (index == 0) {
			return this.stages.get(index);
		}
		return this.stages.get(--this.index);
	}
}
