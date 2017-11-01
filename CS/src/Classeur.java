import java.util.HashMap;
import org.w3c.dom.Document;

public class Classeur {
	
	private HashMap<Integer,Note> dico; // Note sera une classe d�fini par Guillaume Paupart
	
	/**
	 * constructeur � un parm�tre. Doit �tre appeler apr�s le parsage par la classe import
	 * @param doc Document XML parser pour remplir le classeur
	 */
	Classeur(Document doc){
		// TODO Parcourir le XML parser et remplir le HasMap		
	}
	
	/**
	 * @param id Id de la note voulut
	 * @return le titre de la note
	 */
	public String getTitle(int id) {
		return dico.get(id).getTitle(); // renvoi le string corespondant a la clef id
	}
	
	/**
	 * @param id id de la note
	 * @return la Note voulut
	 */
	public Note getNote(int id) {
		return dico.get(id); // renvoi l'objet Note corespondant � la clef id
	}
}