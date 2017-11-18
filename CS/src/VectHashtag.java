import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe permet de regrouper tout les hashtag en un seul objet par l'internm�diaire d'un vecteur
 * 
 * @author Nicolas Morisseau
 * @version V 0.1
 *
 */

public class VectHashtag {
	
	// atribut static permet la modification simultan�e de l'atribut pour toute les instances de la classe
	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger ext�rieur (�crit dans une fichier s�parer)
	private Handler fh;
	private static HashMap<String,Hashtag> vector;
	
	/**
	 * Constructeur sans argument
	 */
	public VectHashtag() {
		vector = new HashMap<String,Hashtag>();
		try {
			fh = new FileHandler("Log.log",true);
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
		
	}
	
	/**
	 * Constructeur � un param�tre
	 * 
	 * @param hashtag : Objet hashtag initialisant le vecteur
	 */
	public VectHashtag(Hashtag hashtag) {
		vector = new HashMap<String,Hashtag>();
		vector.put(hashtag.getNom(),hashtag);
		try {
			fh = new FileHandler("Log.log",true);
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
		
	}
	
	/**
	 * Constructeur � param�tre multiple
	 * 
	 * @param v : liste d'objet Hashtag initialisant le vercteur
	 */
	public VectHashtag(Hashtag...v) {
		vector = new HashMap<String,Hashtag>();
		for (int i = 0;i<v.length;i++) {
			vector.put(v[i].getNom(),v[i]);
		}
		try {
			fh = new FileHandler("Log.log",true);
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
		
	}
	
	/**
	 * Accesseur du nom d'un hashtag
	 * 
	 * @param id : position du hashtag dans le vecteur de hashtag
	 * @return Le nom du hashtag � la position id dans le vecteur
	 */
	public Hashtag getHashtag(String name) {
		try {
			return vector.get(name);
		}catch(Exception e) {
			logger.warning("Tentative de r�cup�ration d'un Hashtag inexistant");
		}
		return null;
	}
	
	/**
	 * Supression d'un Hashtag si il n'existe plus � partir de son nom
	 * 
	 * @param name : Nom du Hashtag � suprimmer
	 */
	public void RemoveHashtag(String name) {
		try {
			vector.remove(name);
		}catch (Exception e) {
			logger.warning("Impossible de retirer le hashtag " + name + " du vecteur");
		}
	}
	
	/**
	 * Ajout d'un Hashtag quand il est cr�er
	 * 
	 * @param hashtag : Objet Hashtag � mettre dans le vecteur
	 */
	public void AddHashtag(Hashtag hashtag) {
		try {
			if (!vector.containsKey(hashtag.getNom())) {
				vector.put(hashtag.getNom(), hashtag);  // on ajoute le Hashtag si et seulement si il n'existe pas d�ja dans le vecteur
			}else {
				Hashtag existant = getHashtag(hashtag.getNom()); // M�me que dans le vecteur --> donc la version Fusionn� sera automatiquement dans le vecteur
				existant.Fusion(hashtag);
			}
		}catch(Exception e) {
			logger.warning("Impossible d'ajouter le hashtag " + hashtag.getNom() + " � l'objet VectHashtag");
		}
	}
}
