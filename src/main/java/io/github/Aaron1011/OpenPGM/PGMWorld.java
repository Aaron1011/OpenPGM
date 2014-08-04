package io.github.Aaron1011.OpenPGM;

import java.util.ArrayList;
import java.util.List;

import io.github.Aaron1011.OpenPGM.tutorial.Tutorial;
import io.github.Aaron1011.OpenPGM.tutorial.TutorialStage;

// Represents all the PGM XML information associated with a world
public class PGMWorld {
	private String name;
	private String version;
	private String objective;
	private List<Author> authors;
	private Tutorial tutorial;

	public PGMWorld() {
		this.name = "";
		this.version = "";
		this.objective = "";
		this.authors = new ArrayList<Author>();
		this.tutorial = new Tutorial();
	}
	
	public PGMWorld(String name, String version, String objective,
			List<Author> authors, Tutorial tutorial) {
		this.name = name;
		this.version = version;
		this.objective = objective;
		this.authors = authors;
		this.tutorial = tutorial;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void addStage(TutorialStage section) {
		this.tutorial.addStage(section);
	}
	
	public void addStageAt(TutorialStage section, int index) {
		this.tutorial.addStageAt(section, index);
	}
}
