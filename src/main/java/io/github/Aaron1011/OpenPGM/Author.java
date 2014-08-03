package io.github.Aaron1011.OpenPGM;

public class Author {
	public String name;
	public String contribution;
	
	public Author(String name) {
		this.name = name;
		this.contribution = "";
	}
	
	public Author(String name, String contribution) {
		this.name = name;
		this.contribution = contribution;
	}
}
