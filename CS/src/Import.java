
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class Import {
	private String Path; // location of register file
	
	/*
	 * Constructeur de Import
	 */
	Import(String p) {
		Path = p;
	}
	
	/*
	 * Accesseur du chemin du fichier d'enregistrement
	 * 
	 * @return Path
	 */
	public String GetPath() {
		return Path;
	}
	
	public Document run() {
		Document doc = null;
		File xmlFile = new File(Path);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
