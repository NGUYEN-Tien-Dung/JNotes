

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 * 
 * @author Tien Dung NGUYEN
 *
 */

public class DomHelper {
	public static Document getDocument(String pathToFile) {
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(pathToFile);
		} catch (Exception e) {
			return document = null;
		}
		return document;
	}
	
	public static String getXMLContent(Document doc) throws TransformerException {
		String result = null;
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer transformer = tff.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			DOMSource source = new DOMSource();
			transformer.transform(source, streamResult);
			result = stringWriter.toString();
		} catch (TransformerConfigurationException tfce) {
			result = null;
		}
		return result;
	}
	
	public static void  saveXMLContent(Document doc, String pathToFile) throws TransformerException {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer transformer = tff.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource();
			StreamResult streamResult = new StreamResult(pathToFile);
			transformer.transform(source, streamResult);
		} catch (TransformerConfigurationException tfce) {
			System.out.print(tfce.getMessage());
		}
	}
	
}






















