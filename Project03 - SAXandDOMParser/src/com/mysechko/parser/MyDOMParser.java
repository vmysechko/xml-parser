package com.mysechko.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


class GunDOM {

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

public class MyDOMParser {

	public static void main(String[] args) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		List<GunDOM> gunsList = new ArrayList<>();
		parser.parse("D:/Java/eclipseworkspace/Project03 - SAXandDOMParser/src/com/mysechko/parser/guns.xml");
		Document gunDoc = parser.getDocument();
		Element gunElement = gunDoc.getDocumentElement();
		
		NodeList list = gunElement.getElementsByTagName("gun");
		
		for(int i = 0; i < list.getLength(); i++){
			GunDOM g = new GunDOM();
			
			Element gun = (Element) list.item(i);
			String gunAttribute = gun.getAttribute("id");
			g.setId(gunAttribute);
			
			Element model = (Element) gun.getElementsByTagName("model").item(0);
			Text valueModel = (Text) model.getFirstChild();
			String stringModel = valueModel.getTextContent();
			g.setModel(stringModel);
			
			Element handy = (Element) gun.getElementsByTagName("handy").item(0);
			Text valueHandy = (Text) handy.getFirstChild();
			String stringHandy = valueHandy.getTextContent();
			g.setHandy(stringHandy);
			
			Element origin = (Element) gun.getElementsByTagName("origin").item(0);
			Text valueOrigin = (Text) origin.getFirstChild();
			String stringOrigin = valueOrigin.getTextContent();
			g.setOrigin(stringOrigin);
			
			g.pr = new GunDOM.Parameters();
			Element parameters = (Element) gun.getElementsByTagName("parameters").item(0);
			Element parameterDistance = (Element) parameters.getElementsByTagName("gunsspace:distance").item(0);
			Text valueDistance = (Text) parameterDistance.getFirstChild();
			String stringDistance = valueDistance.getTextContent();
			g.pr.distance = stringDistance;
			
			Element parameterCharger = (Element) parameters.getElementsByTagName("gunsspace:charger").item(0);
			Text valueCharger = (Text) parameterCharger.getFirstChild();
			String stringCharger = valueCharger.getTextContent();
			
			if(stringCharger.equalsIgnoreCase("yes")){
				g.pr.charger = true;
			} else {
				g.pr.charger = false;
			}
			
			Element parameterOptics = (Element) parameters.getElementsByTagName("gunsspace:optics").item(0);
			Text valueOptics = (Text) parameterOptics.getFirstChild();
			String stringOptics = parameterOptics.getTextContent();
			
			if(stringCharger.equalsIgnoreCase("yes")){
				g.pr.optics = true;
			} else {
				g.pr.optics = false;
			}
			
			Element material = (Element) gun.getElementsByTagName("material").item(0);
			Text valueMaterial = (Text) material.getFirstChild();
			String stringMaterial = valueMaterial.getTextContent();
			g.setMaterial(stringMaterial);
						
			gunsList.add(g);
		}
		
		for(GunDOM temp: gunsList){
			System.out.println("****************");
			System.out.printf("\nGun id: %s", temp.getId());
			System.out.printf("\nGun model: %s", temp.getModel());
			System.out.printf("\nGun handy: %s", temp.getHandy());
			System.out.printf("\nGun origin: %s", temp.getOrigin());
			System.out.printf("\nGun origin: %s", temp.pr.distance);
			System.out.printf("\nGun origin: %s", temp.pr.charger);
			System.out.printf("\nGun origin: %s", temp.pr.optics);
			System.out.printf("\nGun origin: %s\n\n", temp.getMaterial());
		}

	}

}
