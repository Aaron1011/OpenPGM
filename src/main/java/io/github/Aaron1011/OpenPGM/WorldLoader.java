package io.github.Aaron1011.OpenPGM;

import io.github.Aaron1011.OpenPGM.region.PointProvider;
import io.github.Aaron1011.OpenPGM.tutorial.TutorialStage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class WorldLoader {
	public static PGMWorld loadWorld(String name) {
		PGMWorld world = new PGMWorld();
		
		String xmlName = name + ".xml";

		Document doc = openXML(xmlName);
		
		Element rootNode = doc.getRootElement();
		Bukkit.broadcastMessage("Root element :" + rootNode.getName());
		
		parseDocument(rootNode, world, false);
		
		return world;
	}
	
	private static Document openXML(String file) {
		File xmlFile = new File(file);
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return doc;
	}

	private static void parseDocument(Element rootNode, PGMWorld world, boolean include) { 
		if (!include) {
			String proto = rootNode.getAttributeValue("proto");
			if (proto != null && !proto.equals("1.3.3")) {
				Bukkit.broadcastMessage("Wrong version - expected 1.3.3, got " + proto);
			}
			String map_name = rootNode.getChildText("name");
			String version = rootNode.getChildText("version");
			String objective = rootNode.getChildText("objective");
		
			world.setName(map_name);
			world.setVersion(version);
			world.setObjective(objective);
		
			Bukkit.broadcastMessage("Name: " + map_name);
			Bukkit.broadcastMessage("Version: " + version);
			Bukkit.broadcastMessage("Objective: " + objective);
		
			List<Element> authors = rootNode.getChild("authors").getChildren();
		
			Bukkit.broadcastMessage("\nAuthors:");
		
			for (Element author: authors) {
				String name = author.getText();
				String contribution = author.getAttributeValue("contribution");
				if (contribution == null) {
					contribution = "";
				}
			
				world.addAuthor(new Author(name, contribution));
			
				Bukkit.broadcastMessage("Author: " + name + "\nContribution: " + contribution + "\n");
			}
		}	
		parseTutorial(rootNode, world);
		parseIncludes(rootNode, world);
			

	}

	private static void parseIncludes(Element rootNode, PGMWorld world) {
		List<Element> includes = rootNode.getChildren("include");
		for (Element f: includes) {
			Document doc = openXML(f.getAttributeValue("src"));
			parseDocument(doc.getRootElement(), world, true); // Recursively parse
		}
	}

	private static void parseTutorial(Element rootNode, PGMWorld world) {
		Element tutorial = rootNode.getChild("tutorial");
		List<TutorialStage> suffix = new ArrayList<TutorialStage>();
		if (tutorial != null) {
			for (Element e: tutorial.getChildren()) {
				if (e.getName().equals("prefix")) {
					TutorialStage stage = parseTutorialStage(e.getChild("stage"), world);
					world.addStageAt(stage, 0);
				}
				if (e.getName().equals("suffix")) {
					world.addStage(parseTutorialStage(e.getChild("stage"), world));
				}
				else if (e.getName().equals("stage")) {
					suffix.add(parseTutorialStage(e, world));
				}
			}
		}
		for (TutorialStage s: suffix) {
			world.addStage(s);
		}
	}
	
	private static TutorialStage parseTutorialStage(Element stage, PGMWorld world) {
		String title = stage.getAttributeValue("title");
		Bukkit.broadcastMessage("Tutorial stage: " + title);
		Bukkit.broadcastMessage("Lines: ");
		TutorialStage section = new TutorialStage(title);
		for (Element line: stage.getChild("message").getChildren("line")) {
			Bukkit.broadcastMessage(line.getText());
			section.addLine(Util.format(line.getText()));
		}
				
		Location location = new Location(Bukkit.getServer().getWorlds().get(0), 0, 0, 0);
		if (stage.getChild("teleport") != null) {
			Element teleport = stage.getChild("teleport").getChild("point");
			try {
				location.setYaw(teleport.getAttribute("yaw").getIntValue());
				location.setPitch(teleport.getAttribute("pitch").getIntValue());
			} catch (DataConversionException e) {
				e.printStackTrace();
				Bukkit.getLogger().severe("Invalid PGM XML data - invalid numeric value for pitch and yaw");					
				return null;
			}
					
			String[] pos = teleport.getValue().split(",");
				
			location.setX(Double.parseDouble(pos[0]));
			location.setY(Double.parseDouble(pos[1]));
			location.setZ(Double.parseDouble(pos[2]));
					
			section.location = location;
		}
		else {
			section.location = null;
		}
			
		return section;
	}
}

