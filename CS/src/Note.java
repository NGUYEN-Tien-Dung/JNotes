
// Import permettant entre autres la saisie clavier :
import java.util.Scanner;
import java.util.*;   

// Import permettant la gestion des fichiers :


// Outils permettant la génération de PDF :


// Import permettant de manipuler (lire et écrire des documents XML) :


// les imports on ne les �crit pas avant, il se mette au fur et a mesur du code par ajout des packages trouver dans le code par le compilateur! c'est plus propre

public class Note
{
    // Attribus de la classe note (tout est en private, les accesseur et mutateur sont la pour ça) : 
    private String titre;
    private String text;
    private String Hashtag; // doit �tre un vector pour mettre autant qu'on veux et pas un seul
    private int Id;  //manque l'auto incr�ment de id
    
    
    //ConstructeurS ?!?
    
    // Accesseur pour text :
    public String getText()
    {
        return this.text;
    }
    
    // Accesseur pour titre :
    public String getTitre()
    {
        return this.titre;
    }
    
    // Accesseur pour Hastag : 
    public String getHastag()
    {
        return this.Hashtag;
    }
    
    // Accesseur pour Id : 
    public int getId()
    {
        return this.Id;
    }
    
    // Mutateur pour Id : 
    // doit �tre auto incr�mentale ! pas de set de l'id de la note
    // incr�ment� dans le constructeur
    private void setId(int Identifiant)
    {
        this.Id = Identifiant;
    }
    
    // Mutateur pour Text : 
    private void setTexte(String Text_de_la_note)
    {
        this.text = Text_de_la_note;
    }
    
    // Mutateur pour titre :
    private void setTitre(String Titre_Note)
    {
        this.titre = Titre_Note;
    }
    
    // Mutateur pour Hastag :
    // doit g�rer un vector, sinon la on peux avoir que un seul hashtag � la fois .....
    // du coup faire un set et un remove
    // le set ajoutera et le remove enl�ve (attention au exception, si le hashtag existe d�ja ou si il n'existe pas etc.)
    private void setHastag(String Has)
    {
        this.Hashtag = Has;
    }
   
    // Remplissage d'une note - UTILISATION FINALE :
    // MAuvaise utilisation | doit �tre un constructeur et non une m�thode simple
    public void remplir(String TITRE, String TEXT, String HASHTAG, int ID)
    {
        this.setHastag(HASHTAG);
        this.setId(ID);
        this.setTexte(TEXT);
        this.setTitre(TITRE);
    }
    
    // Création du dossier dans lequel seront stockée les notes exportées :
    private void creation_dossier_sauvegarde()
    {
        
    }
    
    // Export d'une note au format désiré (XML, HTML, PDF) : 
    public void export(String export_souhaite)
    {
        // Chemain et nom du répertoir où seront stockés les fichiers exportés :
       
        if (export_souhaite == "PDF")
        {
            
        }
        if (export_souhaite == "XML")
        {
                    
        }
        if (export_souhaite == "HTML")
        {
            
        }
    }
    
    // A d�gager, pour  faire des test on met des constructeur qu'on appel dans un void main dans la classe de test
    public void remplir_TESTS()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le titre de la note :\n");
        String TITRE = sc.nextLine();
        System.out.println("Entrer le text de la note :\n");
        String TEXT = sc.nextLine();
        System.out.println("Entrer le Hastag de la note :\n");
        String HASHTAG = sc.nextLine();
        System.out.println("Entrer l'identifiant de la note :\n");
        int ID = sc.nextInt();    
        this.setHastag(HASHTAG);
        this.setId(ID);
        this.setTexte(TEXT);
        this.setTitre(TITRE);
    }
    
    // Affichage d'une note sur console d'application - UNIQUEMENT POUR LES TESTS :
    public void affiche() 
    {
        System.out.println("Le titre de la note est : \n");
        System.out.println(this.titre);
        System.out.println("Le text de la note est : \n");
        System.out.println(this.text);
        System.out.println("L'identifiant de la note est : \n");
        System.out.println(this.Id);
        System.out.println("Le Hastag de la note est : \n");
        System.out.println(this.Hashtag);
    }   
}

