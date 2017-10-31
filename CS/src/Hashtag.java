import java.util.Vector;

/**
 * 
 * @author nicol
 *
 */
public class Hashtag {
	
	
	private String Nom;
	private Vector<Integer> IdTab; /* vecteur regroupant les identifiants de toutes
	les Notes comportant ce Hashtag */
	private Classeur classeur; // classeur comportant toutes les note et leur ID corespondante
	
	/**
	 * 
	 * @param NomHashtag : Nom du nouveau Hashtag à créer
	 * @param IdNote : identifiant de la note initialisant ce hashtag (cad la première note
	 * ou ce hashtag apparait)
	 */
	Hashtag (String NomHashtag, int IdNote){
		Nom = NomHashtag;
		IdTab = new Vector<Integer>(); // déclaration du Vector de int
		IdTab.add(IdNote); // ajout de l'id de la note initiant ce hastag
	}

	/**
	 * Accesseur de Nom
	 * @return le nom du Hashtag
	 */
	public String getNom() {
		return Nom;
	}
	
	/**
	 * Accesseur de IdTab
	 * @return le vecteur des id de notes ayant de Hashtag
	 */
	public Vector<Integer> getIdVect(){
		return IdTab;
	}
	
	/**
	 * 
	 * @return vecteur des titre de notes comportant de hashtag
	 */
	public Vector<String> getIdVectVerbose(){
		// vecteur des résultats verbeux des notes comportant ce hashtag
		Vector<String> result = new Vector<String>();
		for (int i=0;i<IdTab.size();i++) {
			result.add(classeur.getTitle(i));	
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return Nombre de Note comportant ce Hashtag
	 */
	public int getSize() {
		return IdTab.size();
	}
	
	/**
	 * Fonction a paramètre multiple de type int
	 * @param v : tous les id a ajouter au vector
	 */
	public void AddId(int...v) {
		for (int i=0; i<v.length;i++) {
			IdTab.add(v[i]); // ajout de l'id des Notes au Hashtag
		}
	}
	
	/**
	 * methode permettant de supprimer les note dont le Hashtag ne figure
	 * dans la Note
	 * @param v Id des Notes a supprimer du Hashtag
	 */
	public void RemoveId(int...v) {
		for (int i=0;i<v.length;i++) {
			IdTab.remove(v[i]);
		}
	}
		
}
