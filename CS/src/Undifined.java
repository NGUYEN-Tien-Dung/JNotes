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
public class Undifined extends Hashtag{
	
	// Atributs de la classe
	private static final String Nom = "UNDIFINED";
	
	/**
	 * Constructeur � un param�tre permettant de construire le premier hashtag et d'initialiser l'objet static
	 * 
	 * @param clas
	 */
	
	Undifined(Classeur clas) {
		super(Nom,0,clas);
		try {
			fh = new FileHandler("Log.log",true); // cosntructeur du fichier de log (utile pour r�cup�rer les utilisations)
			fh.setFormatter(new SimpleFormatter()); // format du ficher de log --> text (pas de XML)
			logger.addHandler(fh);
		} catch (Exception e) {
		}
		logger.info("Cr�ation du hashtag undifined et initialisation du classeur static");
	}
}
