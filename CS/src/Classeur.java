import java.util.HashMap;
import org.w3c.dom.Document;

public class Classeur {
	
	private HashMap<Integer,Note> dico; // Note sera une classe défini par Guillaume Paupart
	
	Classeur(Document doc){
		// TODO Parcourir le XML parser et remplir le HasMap		
	}
	
	public String getTitle(int id) {
		return dico.get(id).getTitle(); // renvoi le string corespondant a la clef id
	}
	
	public Note getNote(int id) {
		return dico.get(id); // renvoi l'objet Note corespondant à la clef id
	}
}