

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Tien Dung NGUYEN
 *
 */

/**
 * 
 * This is the class that create all actions on XML file.
 * Actions on XML file : Add a new empty element <Note> </Note> when user create new note without content
 * 						 Update note's content when user change some things on a note such as title, text, hashtag
 * 						 Delete note's content when user delete a Note or some parts of Note (title, text, hastag)
 * 						 Load note's content from XML file to  textArea and textField of UI
 */
public class NotesDOM extends Main{
	
	
	
	/**
	 * This method is invoked when user clicks on context menu's element New Note
	 * It is called on event listener method
	 * Method parameter idNode is retrieved from a instance variable of application Main class named idNote
	 * Format of idNote : String idNote = 'N'+ String.valueof(idCount)
	 * 					  int idCount : Main class instance variable for counting number of note created
	 * @param idNote
	 */
	public static void Add(String idNote) {
		Document document;
		try {
			document = DomHelper.getDocument("src/main/resouces/Notes.xml"); // ??? How to set path of xml file
			Element Notes = document.getDocumentElement();
			Element Note = document.createElement("Note");
			
			//create id
			Element id = document.createElement("id");
			id.appendChild(document.createTextNode(idNote));
			Note.appendChild(id);
			
			//Create title 
			Element title = document.createElement("Title");
			Note.appendChild(title);
			
			//create content
			Element text = document.createElement("Text");
			Note.appendChild(text);
			
			// Create hashtag
			Element hashtag = document.createElement("Tags");
			Note.appendChild(hashtag);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is invoked when changes on Note's content were detected
	 * User can change Note's content, Title of Note
	 * User can change by Deleting or Adding Hashtags
	 * @param id
	 * @param title
	 * @param text
	 * @param hashtag
	 */
	public static void update(String id, String title, String text, String hashtag) {
		Document document;
		try {
            document = DomHelper.getDocument("src/main/resouces/Notes.xml");
            NodeList nl = document.getElementsByTagName("student");
            for (int i = 0; i < nl.getLength(); i++) {
                Element Note = (Element) nl.item(i);
                if (Note.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                    Note.getElementsByTagName("Title").item(0).setTextContent(Title);
                    Note.getElementsByTagName("Text").item(0).setTextContent(Text);
                    Note.getElementsByTagName("Tag").item(0).setTextContent(Tag);
                }
            }
            //Write to file
            DomHelper.saveXMLContent(document, "src/main/resouces/Notes.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/*
	 *  This method is invoked when user clicks on context menu's element Delete Note
	 */
	public static void delete(String id) {
		Document document;
		try {
			document = DomHelper.getDocument("src/main/resouces/Notes.xml");
			NodeList nodeList =  document.getElementsByTagName("Note");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element note = (Element)nodeList.item(i);
				if (note.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
					note.getParentNode().removeChild(note);
				}
			}
			//write to file
			DomHelper.saveXMLContent(document, "src/main/resouces/Notes.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is invoked when user clicks on a specific Note Label to see the content of Note
	 */
	public static void loadNotes() {
		
	}
}
