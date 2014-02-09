package com.vamsi.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLChecker {
	
	public static void main(String ... argv) throws Exception {
		
		File file = new File("/vamsi/test.xml");

		
		InputStream in = new FileInputStream(file);
		 try {
		    String xmlString = IOUtils.toString( in );
		    
		    InputSource inputSource = new InputSource(new StringReader(xmlString));
			Document doc;
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(inputSource);
			
			
			
			Element productElement = (Element) doc.getElementsByTagName("product").item(0);
		    
		    getTagValueForProp(productElement, "property_name", 1);
		    getTagValueForProp(productElement, "property_value", 1);
		    
		 } finally {
		   IOUtils.closeQuietly(in);
		 }
	}
	private static String getTagValueForProp(Element element, String tagName, int j) {

		String value = "";
		
		NodeList list = element.getElementsByTagName(tagName);
		
		System.out.println(list.getLength());
		
		Node node = list.item(j);
		
		System.out.println(node.getNodeName());
		
		
		
		
		
		if (element.getElementsByTagName(tagName).item(j) != null) {
			value = (String) element.getElementsByTagName(tagName).item(j).getFirstChild().getNodeValue();
		}

		System.out.println(value);
		
		return value;
	}
	

}
