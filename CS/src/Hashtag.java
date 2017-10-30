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
	
	/**
	 * 
	 * @param NomHashtag : Nom du nouveau Hashtag � cr�er
	 * @param IdNote : identifiant de la note initialisant ce hashtag (cad la premi�re note
	 * ou ce hashtag apparait)
	 */
	Hashtag (String NomHashtag, int IdNote){
		Nom = NomHashtag;
		IdTab = new Vector<Integer>(); // d�claration du Vector de int
		IdTab.add(IdNote); // ajout de l'id de la note initiant ce hastag
	}

	/**
	 * Accesseur de Nom
	 * @return le nom du Hashtag
	 */
	public String GetNom() {
		return Nom;
	}
	
	/**
	 * Accesseur de IdTab
	 * @return le vecteur des id de notes ayant de Hashtag
	 */
	public Vector<Integer> GetIdVect(){
		return IdTab;
	}
	
	
	public Vector<String> GetIdVectVerbose(){
		Vector<String> result = new Vector<String>();
		// TODO Faire la conversion de vector<Integer> en Vector<String>
		
		return result;
	}
	
	/**
	 * 
	 * @return Nombre de Note comportant ce Hashtag
	 */
	public int GetSize() {
		return IdTab.size();
	}
	
	/**
	 * Fonction a param�tre multiple de type int
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
