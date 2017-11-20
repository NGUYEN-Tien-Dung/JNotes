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

	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger ext�rieur (�crit dans une fichier s�parer)
	private Handler fh;
	private String Nom;
	private Vector<Integer> IdTab; /* vecteur regroupant les identifiants de toutes
	les Notes comportant ce Hashtag */
	private static Classeur classeur; // classeur comportant toutes les notes et leur ID corespondante
	
	
	/**
	 * Constructeur du PREMIER HashTag (permet l'initialisation de l'objet static classeur pour tout les Hashtag)
	 * Protected permet de ne l'appeler qu'une fois dans une autre classe et ne plus y avoir acc�s ensuite.
	 * Sera appeler par la cr�ation du hashtag UNDIFINED qui est le tag par d�faut !
	 * 
	 * @param NomHashtag : Nom du nouveau Hashtag � cr�er
	 * @param IdNote identifiant de la note initialisant ce hashtag (cad la premi�re note
	 * ou ce hashtag apparait)
	 */
	// TODO Mettre cette appel de constructeur dans une class sp�cial pour l'initialisation des HashTag
	protected Hashtag (String NomHashtag, int IdNote, Classeur clas){
		Nom = NomHashtag;
		classeur = clas;
		IdTab = new Vector<Integer>(); // d�claration du Vector de int
		IdTab.add(IdNote); // ajout de l'id de la note initiant ce hastag
		// Cr�ation du fichier de log
		 try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (Exception e) {
		}
		logger.info("Hashtag d'initialisation UNDIFINED r�aliser avec succ�s");
	}
	
	/**
	 * Constructeur r�gulier de HashTag
	 * 
	 * @param NomHashtag
	 * @param IdNote
	 */
	public Hashtag (String NomHashtag, int IdNote) {
		Nom = NomHashtag;
		IdTab = new Vector<Integer>(); // d�claration du Vector de int
		IdTab.add(IdNote); // ajout de l'id de la note initiant ce hastag
		// Cr�ation du fichier de log
		 try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (SecurityException e) {
			logger.warning("Security exception " + NomHashtag + " (Constructeur)");
		} catch (IOException e) {
		}
		logger.info("Hashtag cr�� : " + this.getNom() + "(" + IdNote + ")");
	}
	

	/**
	 * Accesseur de Nom
	 * 
	 * @return le nom du Hashtag
	 */
	public String getNom() {
		return Nom;
	}
	
	/**
	 * Accesseur de IdTab
	 * 
	 * @return le vecteur des id de notes ayant de Hashtag
	 */
	public Vector<Integer> getIdVect(){
		return IdTab;
	}
	
	/**
	 * accesseur de IdTab en mode verbeux = retour des titres des note et non des id
	 * 
	 * @return vecteur des titre de notes comportant de hashtag
	 */
	public Vector<String> getIdVectVerbose(){
		// vecteur des r�sultats verbeux des notes comportant ce hashtag
		Vector<String> result = new Vector<String>();
		for (int i=0;i<IdTab.size();i++) {
			result.add(classeur.getTitle(i));	
		}
		
		return result;
	}
	
	/**
	 * M�thode donnant le nm�ro de la Note � une certaine ponsitin dans le vecteur d'Id
	 * 
	 * @param position Position dans le vecteur
	 * @return le num�ro de la note
	 */
	public int getIdNote(int position) {
		try {
			return IdTab.get(position);
		}catch(Exception e) {
			logger.warning("Tentative de lecture en dehors de la taille du vecteur d'Id d'un Hashtag");
			return -1;
		}
	}
	
	/**
	 * Accesseur d'une note par son Id
	 * 
	 * @param id id de la note � retourner
	 * @return la note corespondant � l'id demander (l'id doit �tre dans 
	 */
	public Note getNote(int id) {
		if (IdTab.contains(id)==false) {
			logger.warning("tentative d'acc�s � un ID non compris dans le hashtag courant (#" + Nom + ")");
			return null;
		}else {
			return classeur.getNote(id);
		}
	}
	
	/**
	 * Accesseur du nombre de note comprenant ce Hashtag
	 * 
	 * @return Nombre de Note comportant ce Hashtag
	 */
	public int getSize() {
		return IdTab.size();
	}
	
	/**
	 * M�thode a param�tre multiple de type int
	 * 
	 * @param v tous les id a ajouter au vector
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
	 * methode permettant de supprimer les note dont le Hashtag ne figure dans la Note
	 * 
	 * @param v Id des Notes a supprimer du Hashtag
	 */
	public void RemoveId(int...v) {
		String log = ""; // string pour le fichier log
		for (int i=0;i<v.length;i++) {
			IdTab.remove((Object)v[i]); // caster les int en Object permet d'utiliser la m�thode remove(Object o) qui supprime l'objet et non la m�thode remove(int i)
										// qui supprime l'objet � l'indice i
			log = log + v[i] + "\t";
		}
		logger.info("Supression dans le Hashtag " + this.getNom() + " des notes suivantes : " + log);
	}
	
	/**
	 * Permet l'affichage des infos dans la console (pour le d�veloppement) 
	 * 
	 * @param Verbose boolean permettant de d�finir le type d'affichage Id/Nom
	 * @return un string Verbeux de l'�tat d'un hashtag pour affichage
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
	
	/**
	 * M�thode permettant la fusion de deux hashtag ayant le m�me nom si l'on essaye d'ajouter a VectHashtag un hashtag existant.
	 * Cette m�thode garde les �l�ments de l'objet courant et ajout, le cas �ch�ant, les �l�ments du Hashtag � fusionner
	 * 
	 * @param ToFusion le Hashtag � fusionner avec le Hashtag courant
	 */
	public void Fusion(Hashtag ToFusion) {
		try {
			for (int i=0;i<ToFusion.getSize();i++) {
				if (!IdTab.contains(ToFusion.getIdNote(i)) && ToFusion.getIdNote(i)!=-1) {
					IdTab.addElement(ToFusion.getIdNote(i));
				}
			}
		}catch(Exception e) {
			logger.warning("Erreur lors de la fusion du hashtag " + Nom);
		}
	}
}
