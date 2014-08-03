package io.github.Aaron1011.openpgm;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.bukkit.Bukkit;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class WorldLoader {
	public static boolean loadWorld(String name) {
		String xmlName = name + ".xml";
		File xmlFile = new File(xmlName);
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		Element rootNode = doc.getRootElement();
		Bukkit.broadcastMessage("Root element :" + rootNode.getName());
		
		parseDocument(rootNode);
		
		return true;
	}

	private static void parseDocument(Element rootNode) {
		String proto = rootNode.getAttributeValue("proto");
		if (!proto.equals("1.3.3")) {
			Bukkit.broadcastMessage("Wrong version - expected 1.3.3, got " + proto);
		}
		String map_name = rootNode.getChildText("name");
		String version = rootNode.getChildText("version");
		String objective = rootNode.getChildText("objective");
		
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
			
			Bukkit.broadcastMessage("Author: " + name + "Contribution: " + contribution);
			
		}

	}
}
