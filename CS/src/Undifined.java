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
public class Undifined extends Hashtag{
	
	// Atributs de la classe
	private static final String Nom = "UNDIFINED";
	
	/**
	 * Constructeur à un paramètre permettant de construire le premier hashtag et d'initialiser l'objet static
	 * 
	 * @param clas
	 */
	
	Undifined(Classeur clas) {
		super(Nom,0,clas);
		try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour récupérer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (Exception e) {
		}
		logger.info("Création du hashtag undifined et initialisation du classeur static");
	}
}
