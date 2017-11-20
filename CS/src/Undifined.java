import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe appeler une seul fois pour la création de la première instance de Hashtag, pour y ajouter le Classeur qui aura été
 * rempli après l'import des données de la base de données
 * 
 * @author nicol le 19/11/2017
 * 
 */
public class Undifined {
	
	// Atributs de la classe
	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger extérieur (écrit dans une fichier séparer)
	private Handler fh;
	private Hashtag init;
	private static final String Nom = "UNDIFINED";
	
	
	/**
	 * Constructeur à un paramètre permettant de construire le premier hashtag et d'initialiser l'objet static
	 * 
	 * 
	 * @param clas
	 */
	
	public Undifined(Classeur clas) {
		init = new Hashtag(Nom,0,clas);
		try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour récupérer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (Exception e) {
		}
		logger.info("Création du hashtag undifined et initialisation du classeur static");
	}
	
	/**
	 * Accesseur du Hashtag undifined
	 * @return l'objet Hashtag Undifined
	 */
	public Hashtag getHashtag() {
		return init;
	}
	
	/**
	 * Mutateur de Id par appel du mutateur de la classe Hashtag
	 * @param id l'id de la note à ajouter au Hashtag undifined
	 */
	public void setId(int id) {
		init.AddId(id);
	}
	
	
}
