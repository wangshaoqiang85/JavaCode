package config;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigDemo {
	
	public static void readConfig() throws Exception {
		
		SAXReader reader = new SAXReader();//xml¶ÁÈ¡Æ÷
		Document doc = reader.read("config/cfg.xml");
		Element game = doc.getRootElement();
		Element frame = game.element("frame");
		List<Element> layers = frame.elements("layer");
		for(Element layer : layers) {
			System.out.print(layer.attributeValue("className")+(","));
			System.out.println(layer.attributeValue("x"));
		}
			
		String str = frame.attributeValue("width");
		System.out.println(str);
		
	}
/*
	public static void main(String args[]) throws Exception {
		
		readConfig();
	}
	*/
}
