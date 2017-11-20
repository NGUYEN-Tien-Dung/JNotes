package storagexml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;


import storagexml.Note;

public class JAXBTest{
	
	public static void main(String args[]) throws Exception {
		
		Note note = new Note();
		note.setId(1);
		note.setHashtag("Java, Notes");
		note.setTitre("Java Notes");
		note.setText("Java Notes");
		
		objectToXML(note);
		
		//xmlToObject();
	}
	
	/**
	 * Marshall information into an xml file
	 */
	
	private static void objectToXML(Note note){
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Note.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(note, System.out);
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private static  Note xmlToObject() throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance(Note.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Note note = (Note) unmarshaller.unmarshal(ClassLoader.getSystemResourceAsStream("Note.xml"));
			System.out.println(note);
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
