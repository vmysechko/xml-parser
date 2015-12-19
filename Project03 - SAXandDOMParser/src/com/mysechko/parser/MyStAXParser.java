package com.mysechko.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class GunStAX {

	String id;
	String model;
	String handy;
	String origin;
	Parameters pr;
	String material;

	static class Parameters {
		String distance;
		Boolean charger;
		Boolean optics;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHandy() {
		return handy;
	}

	public void setHandy(String handy) {
		this.handy = handy;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Parameters getPr() {
		return pr;
	}

	public void setPr(Parameters pr) {
		this.pr = pr;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + "\n" + 
				"Model: " + this.getModel() + "\n" + 
				"Handy: " + this.getHandy() + "\n" + 
				"Origin: " + this.origin + "\n" + 
				"Distance: " + this.pr.distance + "\n" + 
				"Charger: " + this.pr.charger + "\n" + 
				"Optics: " + this.pr.optics + "\n" + 
				"Material: " + this.getMaterial() + "\n";
	}
}

public class MyStAXParser {

	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

		List<GunStAX> gunsList = null;
		GunStAX currGun = null;
		String tagContent = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream("com/mysechko/parser/guns.xml"));

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if ("gun".equals(reader.getLocalName())) {
					currGun = new GunStAX();
					currGun.pr = new GunStAX.Parameters();
					currGun.setId(reader.getAttributeValue(0));
				}

				if ("guns".equals(reader.getLocalName())) {
					gunsList = new ArrayList<>();
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				tagContent = reader.getText().trim();
				break;

			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "gun":
					gunsList.add(currGun);
					break;
				case "model":
					currGun.setModel(tagContent);
					break;
				case "handy":
					currGun.setHandy(tagContent);
					break;
				case "origin":
					currGun.setOrigin(tagContent);
					break;
				case "distance":
					currGun.pr.distance = tagContent;
					break;
				case "charger": {
					if (tagContent.equals("yes")) {
						currGun.pr.charger = true;
					} else {
						currGun.pr.charger = false;
					}
					break;
				}
				case "optics": {
					if (tagContent.equals("yes")) {
						currGun.pr.optics = true;
					} else {
						currGun.pr.optics = false;
					}
					break;
				}
				case "material":
					currGun.setMaterial(tagContent);
					break;
				}
				break;

			case XMLStreamConstants.START_DOCUMENT:
				gunsList = new ArrayList();
				break;
			}
		}

		for (GunStAX gun : gunsList) {
			System.out.println(gun.toString());
		}

	}

}
