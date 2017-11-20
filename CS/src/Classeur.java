
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.w3c.dom.Document;


public class Classeur {
	
	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger extérieur (écrit dans une fichier séparer)
	private Handler fh;
	
	private HashMap<Integer,Note> dico; // Note sera une classe défini par Guillaume Paupart
	
	/**
	 * Constructeur à un parmètre. Doit être appeler après le parsage par la classe import
	 * 
	 * @param doc : Document XML parser pour remplir le classeur
	 */
	Classeur(Document doc){
		// construction de l'objet de log (voir avec Chau)
		 try {
				fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour récupérer les utilisations)
				fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
				logger.addHandler(fh);
			} catch (Exception e) {
			}
		// TODO Parcourir le XML parser et remplir le HasMap		
	}
	
	/**
	 * Accesseur du titre d'une note dans le classeur par son id
	 * 
	 * @param id Id de la note voulut
	 * @return Le titre de la note
	 */
	public String getTitle(int id) {
		return dico.get(id).getTitre(); // renvoi le string corespondant a la clef id
	}
	
	/**
	 * Accesseur d'une note dans le classeur par son id
	 * 
	 * @param id id de la note
	 * @return La Note voulut
	 */
	public Note getNote(int id) {
		return dico.get(id); // renvoi l'objet Note corespondant à la clef id
	}
	
	public void setNote(Note note) {
		try {
			dico.put(note.getId(), note);
		}catch(Exception e) {
			logger.warning("la note n°" + note.getId() + " : \"" + note.getTitre() + "\" na pas pu être ajouté au vecteur ");
		}
	}
}