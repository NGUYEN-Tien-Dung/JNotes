import java.io.IOException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author nicol
 *
 */
public class Hashtag {

	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger extérieur (écrit dans une fichier séparer)
	private Handler fh;
	private String Nom;
	private Vector<Integer> IdTab; /* vecteur regroupant les identifiants de toutes
	les Notes comportant ce Hashtag */
	private static Classeur classeur; // classeur comportant toutes les notes et leur ID corespondante
	private static Vector<Hashtag> HashTagTab = new Vector<Hashtag>(); /* Mettre l'attribut en static permet de motifier l'attribut de tout
	les objet à chaque fois que l'attribut d'un objet est modifier	*/
	
	/**
	 * @param NomHashtag : Nom du nouveau Hashtag à créer
	 * @param IdNote : identifiant de la note initialisant ce hashtag (cad la première note
	 * ou ce hashtag apparait)
	 */
	Hashtag (String NomHashtag, int IdNote, Classeur clas){
		Nom = NomHashtag;
		classeur = clas;
		IdTab = new Vector<Integer>(); // déclaration du Vector de int
		IdTab.add(IdNote); // ajout de l'id de la note initiant ce hastag
		HashTagTab.add(this);
		// Création du fichier de log
		 try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour récupérer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (SecurityException e) {
			logger.warning("Erreur fatale de sécurité Hashtag " + NomHashtag + " (Constructeur)");
		} catch (IOException e) {
		}
		logger.info("Hashtag créé : " + this.getNom() + "(" + IdNote + ")");
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
	
	
	public int getNbHashtag() {
		return HashTagTab.size();
	}
	
	/**
	 * Fonction a paramètre multiple de type int
	 * @param v : tous les id a ajouter au vector
	 */
	public void AddId(int...v) {
		try {
		String log = "";
		for (int i=0; i<v.length;i++) {
			IdTab.add(v[i]); // ajout de l'id des Notes au Hashtag
			log = log + v[i] + "\t";
		}
		logger.info("Ajout dans le Hashtag " + this.getNom() + " des notes suivantes : " + log);
		}catch (Exception e) {
			logger.warning("Erreur lors de l'ajout de note dans le Hashtag " + this.getNom()); // warning dans les log si erreur lors de l'ajout
		}
	}
	
	/**
	 * methode permettant de supprimer les note dont le Hashtag ne figure
	 * dans la Note
	 * @param v Id des Notes a supprimer du Hashtag
	 */
	public void RemoveId(int...v) {
		String log = ""; // string pour le fichier log
		for (int i=0;i<v.length;i++) {
			IdTab.remove((Object)v[i]); // caster les int en Object permet d'utiliser la méthode remove(Object o) qui supprime l'objet et non la méthode remove(int i)
										// qui supprime l'objet à l'indice i
			log = log + v[i] + "\t";
		}
		logger.info("Supression dans le Hashtag " + this.getNom() + " des notes suivantes : " + log);
	}
	
	/**
	 * @param Verbose : boolean permettant de définir le type d'affichage Id/Nom
	 * @return un string Verbeux de l'état d'un hashtag pour affichage
	 */
	public String Infos(boolean Verbose) {
		String result = "----------------- INFOS ------------------\n";
		result = result + "Nom : " + this.getNom() + "\n";
		result = result + "Note(s) : ";
		if (Verbose == false) {
			for (int i = 0; i<IdTab.size();i++) {
				result =  result + String.valueOf(IdTab.get(i)) + "\t";
			}
			// si Verbose == true on met les nom des Notes et non leur Id
		}else {
			Vector<String> vect = this.getIdVectVerbose();
			for (int i = 0; i<vect.size();i++) {
				result =  result + vect.get(i) + "\t";
			}
		}
		result = result + "\n------------------------------------------\n";
		return result;
	}
	
	
}
