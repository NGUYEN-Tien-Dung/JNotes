import java.util.Vector;

/**
 * Classe permet de regrouper tout les hashtag en un seul objet par l'internmédiaire d'un vecteur
 * 
 * @author Nicolas Morisseau
 * @version V 0.1
 *
 */

public class VectHashtag {
	
	// atribut static permet la modification simultanée de l'atribut pour toute les instances de la classe
	private static Vector<Hashtag> vector;
	
	/**
	 * Constructeur sans argument
	 */
	public VectHashtag() {
		vector = new Vector<Hashtag>();
	}
	
	/**
	 * Constructeur à un paramètre
	 * 
	 * @param hashtag : Objet hashtag initialisant le vecteur
	 */
	public VectHashtag(Hashtag hashtag) {
		vector = new Vector<Hashtag>();
		vector.add(hashtag);
	}
	
	/**
	 * Constructeur à paramètre multiple
	 * 
	 * @param v : liste d'objet Hashtag initialisant le vercteur
	 */
	public VectHashtag(Hashtag...v) {
		vector = new Vector<Hashtag>();
		for (int i = 0;i<v.length;i++) {
			vector.add(v[i]);
		}
	}

}
