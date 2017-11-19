
/**
 * Classe appeler une seul fois pour la cr�ation de la premi�re instance de Hashtag, pour y ajouter le Classeur qui aura �t�
 * rempli apr�s l'import des donn�es de la base de donn�es
 * 
 * @author nicol le 19/11/2017
 * 
 */
public class Undifined {
	
	// Atributs de la classe
	private Hashtag init;
	private static final String Nom = "UNDIFINED";
	private final Classeur classeur;
	
	
	/**
	 * Constructeur � un param�tre permettant de construire le premier hashtag et d'initialiser l'objet static
	 * 
	 * 
	 * @param clas
	 */
	public Undifined(Classeur clas) {
		init = new Hashtag(Nom,0,clas);
		classeur = clas;
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
	 * @param id : l'id de la note � ajouter au Hashtag undifined
	 */
	public void setId(int id) {
		init.AddId(id);
	}
	
	
}
