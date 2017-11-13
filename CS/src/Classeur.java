import java.util.HashMap;
import org.w3c.dom.Document;

public class Classeur {
	
	private HashMap<Integer,Note> dico; // Note sera une classe défini par Guillaume Paupart
	
	/**
	 * Constructeur à un parmètre. Doit être appeler après le parsage par la classe import
	 * 
	 * @param doc : Document XML parser pour remplir le classeur
	 */
	Classeur(Document doc){
		// TODO Parcourir le XML parser et remplir le HasMap		
	}
	
	/**
	 * Accesseur du titre d'une note dans le classeur par son id
	 * 
	 * @param id : Id de la note voulut
	 * @return Le titre de la note
	 */
	public String getTitle(int id) {
		return dico.get(id).getTitle(); // renvoi le string corespondant a la clef id
	}
	
	/**
	 * Accesseur d'une note dans le classeur par son id
	 * 
	 * @param id : id de la note
	 * @return La Note voulut
	 */
	public Note getNote(int id) {
		return dico.get(id); // renvoi l'objet Note corespondant à la clef id
	}
}