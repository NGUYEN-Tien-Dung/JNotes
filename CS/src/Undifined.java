import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe appeler une seul fois pour la cr�ation de la premi�re instance de Hashtag, pour y ajouter le Classeur qui aura �t�
 * rempli apr�s l'import des donn�es de la base de donn�es
 * 
 * @author nicol le 19/11/2017
 * 
 */
public class Undifined {
	
	// Atributs de la classe
	protected static Logger logger = Logger.getLogger("Log"); // Atribut permettant l'utilisation de logger ext�rieur (�crit dans une fichier s�parer)
	private Handler fh;
	private Hashtag init;
	private static final String Nom = "UNDIFINED";
	
	
	/**
	 * Constructeur � un param�tre permettant de construire le premier hashtag et d'initialiser l'objet static
	 * 
	 * 
	 * @param clas
	 */
	
	public Undifined(Classeur clas) {
		init = new Hashtag(Nom,0,clas);
		try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (Exception e) {
		}
		logger.info("Cr�ation du hashtag undifined et initialisation du classeur static");
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
	 * @param id l'id de la note � ajouter au Hashtag undifined
	 */
	public void setId(int id) {
		init.AddId(id);
	}
	
	
}
