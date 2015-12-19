package com.mysechko.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Gun {

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

}

public class MySAXParser extends DefaultHandler {

	List<Gun> gunsList = new ArrayList<>();

	Gun g;
	int current;

	public List<Gun> getList() {
		return gunsList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parsing!");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End parsing!");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		switch (qName) {
		case "gun": {
			g = new Gun();
			g.setId(attributes.getValue("id"));
			current = 1;
			break;
		}
		case "model": {
			current = 2;
			break;
		}
		case "handy": {
			current = 3;
			break;
		}
		case "origin": {
			current = 4;
			break;
		}
		case "parameters": {
			g.pr = new Gun.Parameters();
			current = 5;
			break;
		}
		case "gunsspace:distance": {
			current = 6;
			break;
		}
		case "gunsspace:charger": {
			current = 7;
			break;
		}
		case "gunsspace:optics": {
			current = 8;
			break;
		}
		case "material": {
			current = 9;
			break;
		}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String message = new String(ch, start, length);

		switch (current) {
		case 2: {
			g.setModel(message);
			break;
		}
		case 3: {
			g.setHandy(message);
			break;
		}
		case 4: {
			g.setOrigin(message);
			break;
		}
		case 6: {
			g.pr.distance = message;
			break;
		}
		case 7: {
			if (message.equalsIgnoreCase("yes")) {
				g.pr.charger = true;
			} else {
				g.pr.charger = false;
			}
			break;
		}
		case 8: {
			if (message.equalsIgnoreCase("yes")) {
				g.pr.optics = true;
			} else {
				g.pr.optics = false;
			}
			break;
		}
		case 9: {
			g.setMaterial(message);
			break;
		}
		}
		current = 0;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "gun": {
			gunsList.add(g);
			break;
		}
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MySAXParser mySAXParser1 = new MySAXParser();
		parser.parse("D:/Java/eclipseworkspace/Project03 - SAXandDOMParser/src/com/mysechko/parser/guns.xml", mySAXParser1);
		List<Gun> newGunsList = mySAXParser1.getList();

		for (Gun tempGun : newGunsList) {
			System.out.println("*****************************************");
			System.out.printf("\nGun id: %s", tempGun.getId());
			System.out.printf("\nGun model: %s", tempGun.getModel());
			System.out.printf("\nGun handy: %s", tempGun.getHandy());
			System.out.printf("\nGun origin: %s", tempGun.getOrigin());
			System.out.printf("\nGun distance: %s", tempGun.getPr().distance);
			System.out.printf("\nGun charger: %s", tempGun.getPr().charger);
			System.out.printf("\nGun optics: %s", tempGun.getPr().optics);
			System.out.printf("\nGun material: %s\n\n", tempGun.getMaterial());
		}
	}

}
