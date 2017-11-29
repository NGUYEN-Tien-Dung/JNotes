/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package note;

// Import permettant entre autres la saisie clavier et la gestion des execptions :
import java.util.Scanner;
import java.util.*;   
import java.lang.Exception;
import java.lang.Throwable;

// Import permettant la gestion des fichiers :
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Outils permettant la gÃ©nÃ©ration de PDF :


// Import permettant de manipuler (lire et Ã©crire des documents XML) :
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Gestion des exception - Note déjà existante :  
class Note_deja_existante extends Exception
{ 
  private String Note_deja_existante()
  {
    String TYPE_ERREUR = "La note que vous essayez de créer existe déjà - Elle ne sera pas créé deux fois";
    System.out.println(TYPE_ERREUR);
    return TYPE_ERREUR;
  }  
}

// Gestion des execptions - Création d'une note vide (sans texte) : 
class Note_Vide extends Exception
{  
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	*/  
	public Note_Vide() {}  
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param message Le message détaillant exception 
	*/  
	public Note_Vide(String message) 
        {  
		super(message); 
	}  
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public Note_Vide(Throwable cause) 
        {  
		super(cause); 
	}  
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public Note_Vide(String message, Throwable cause) 
        {  
		super(message, cause); 
	} 
}
 // Gestion des execpions - Absence de titre dans le constructeur de la note : 
 class Titre_Vide extends Exception
{  
	public Titre_Vide() {}  

	public Titre_Vide(String message) 
        {  
		super(message); 
	}  
 
	public Titre_Vide(Throwable cause) 
        {  
		super(cause); 
	}  

	public Titre_Vide(String message, Throwable cause) 
        {  
		super(message, cause); 
	} 
 }

// Gestion des exeptions - Absence de HASHTAG lors de l'appel du deuxième constructeur :
class HASHTAG_Vide extends Exception
{  
	public HASHTAG_Vide() {}  

	public HASHTAG_Vide(String message) 
        {  
		super(message); 
	}  
 
	public HASHTAG_Vide(Throwable cause) 
        {  
		super(cause); 
	}  

	public HASHTAG_Vide(String message, Throwable cause) 
        {  
		super(message, cause); 
	} 
 }

// Gestion des exception - Hashtag déjà existant :
class Hashtag_daja_existant extends Exception
{ 
  private String Hashtag_daja_existant()
  {
    String TYPE_ERREUR = "Le Hashtag existe déja - Il ne sera pas créé deux fois";
    System.out.println(TYPE_ERREUR);
    return TYPE_ERREUR;
  }  
}

public class Note
{
    // Attribus de la classe note (tout est en private, les accesseur et mutateur sont la pour Ã§a) : 
    private String titre;
    private String text;
    private Vector<String> Hashtag; // doit être un vector pour mettre autant qu'on veux et pas un seul
    private int Id;  //manque l'auto incrément de id
    public String ERREUR_CREATION;
    
    // Savoir si une note est vide - Pour la gestion des execptions - vide si la fonction retoure TRUE:
    private boolean note_vide()
    {
        return text.isEmpty();
    }
    
    // Savoir si un hashtag existé déjà dans le vector HASHTAG :
    // Utile pour ne pas enregistrer deux fois le même HASHTAG :
    private boolean Hashtag_exist_or_not(String HASHTAG)
    {
        int i = 0;
        boolean EXIST = false;
        for(i=0;i<this.Hashtag.size()-1;i++)
        {
            if(this.Hashtag.get(i).equals(HASHTAG))
            {
                EXIST = true;
            }
        }
        return EXIST;
    }
    
    // Suppression des éléments qui présents plusieurs fois dans le même vecteur :
    // Cette fonction est appelée dans : 
    //          - le second constructeur de note
    //          - La fonction modifiant une note déjà créer 
private static Vector<String> Redondense_HASHTAG(Vector<String> VEC_A_TRIER)
    { 
        int i = 0;
        int j = 0;
        int k = 1;
        int cpt = 0;
        String tmp = VEC_A_TRIER.elementAt(0);
        int taille = VEC_A_TRIER.size();
        Vector<String> v = new Vector(1,1);
       
        // Recopie du vecteur pour éviter la modification en cours de VEC_A_TRIER - MARCHE :
        for(i=0;i<VEC_A_TRIER.size();i++)
        {
             v.add(i, VEC_A_TRIER.elementAt(i));
             //System.out.println(v.elementAt(i));
        }
        
        // Boucle de fonctionnement limite : 
        // Gestion du cas ou l'éléments en posistion 0 est présent plusieurs fois :
        for(i=1;i<VEC_A_TRIER.size();i++)
        {
            if(VEC_A_TRIER.elementAt(0).equals(VEC_A_TRIER.elementAt(i)))
            {
                v.removeElementAt(0);
                break;
            }
        }
        
        // La boucle de fonctionnement général.
        // Cette boucle ne gère pas le cas ou l'élément en position 0 
        // dans le vectoeur est répété plusieurs fois : 
        while(k<taille)
        {
            tmp = (String)VEC_A_TRIER.elementAt(k);
            for(j=k;j<VEC_A_TRIER.size();j++)
            {
                if(VEC_A_TRIER.elementAt(j).equals(tmp))
                {
                    cpt++;
                }
            }
            // Si des occurences multiple sont trouvées, on les supprimes : 
            if(cpt>0)
            {
                for(j=0;j<cpt-1;j++)
                {
                     v.remove(tmp);
                }
            }
            k++;
            cpt = 0;
        } 
        return v;
    }
    
    // Constructeur par défaut - Création d'une note à partir de sont TITRE et du TEXTE qu'elle contient :
    // RESTE A FAIRE : Gestion des ID
    public Note(String TITRE, String TEXTE)
    {
        this.Hashtag.setElementAt("UNDEFINE",0); // Par défaut une note est créé avec une HASHTAG à la valeur UNDEFINED 
        this.setId(1); // L'identifiant de la première note est mis a 1 par défaut
        try 
        {
            this.setTexte(TEXTE);
            this.setTitre(TITRE);
            
            // Si texte vide - Execption 
            if(this.text.isEmpty()==true)
            {
                throw new Note_Vide("La Note est vide");
            }
            
            // Si absence de titre - Execption :
            if(this.titre.isEmpty()==true)
            {
                throw new Titre_Vide("Vous avez oublié de donner un titre à votre document");
            }
        }
        // Modification de la variable globale gestion des erreurs :
        // Cette variable est à disposition de ceux qui voudraient implémenter une 
        // Message box avertissant l'utilisateur de sa mauvaise munipulation 
        catch(Note_Vide nv)
        {
            this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "La note est vide";
        }
        catch(Titre_Vide tv)
        {
             this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "La note est vide";
        }
    }
    
    // Ce constructeur permettrat de crée une note à partir :
    //          - De son titre
    //          - Du texte qu'elle contient
    //          - D'un vector de contenant tout les HASHTAG présents dans cette note 
    //              ATENTION : CE N'EST PAS A MOI DE GERER L'OBTENTION DE CE VECTOR
    // RESTE A FAIRE : Gestion des ID 
    public Note(String TITRE, String TEXTE, Vector<String> HASHTAG_IN_NOTE)
    {
        try 
        {
            this.setTexte(TEXTE);
            this.setTitre(TITRE);
            int taille = Redondense_HASHTAG(HASHTAG_IN_NOTE).size();
            this.Hashtag = new Vector<String>(1,taille);
            this.setHastag(Redondense_HASHTAG(HASHTAG_IN_NOTE));
            
            // Si texte vide - Execption 
            if(this.text.isEmpty()==true)
            {
                throw new Note_Vide("La Note est vide");
            }
            
            // Si absence de titre - Execption :
            if(this.titre.isEmpty()==true)
            {
                throw new Titre_Vide("Vous avez oublié de donner un titre à votre document");
            }
            
            // Si le HASHTAG_IN_NOTE vide - Execption :
            if(HASHTAG_IN_NOTE.isEmpty()==true)
            {
                throw new HASHTAG_Vide("Aucun HASHTAG n'est présent dans votre texte");
            }
        }
        // Modification de la variable globale gestion des erreurs :
        // Cette variable est à disposition de ceux qui voudraient implémenter une 
        // Message box avertissant l'utilisateur de sa mauvaise munipulation 
        catch(Note_Vide nv)
        {
            this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "La note est vide";
        }
        catch(Titre_Vide tv)
        {
             this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "La note est vide";
        }
        catch(HASHTAG_Vide hs)
        {
            this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "Aucun HASHTAG présent dans cette note";
        }    
    }
   
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
    public Vector getHastag()
    {
        return this.Hashtag;
    }
    
    // Accesseur pour Id : 
    public int getId()
    {
        return this.Id;
    }
    
    // Mutateur pour Id : 
    // doit être auto incrémentale ! pas de set de l'id de la note
    // incrémenté dans le constructeur
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
    // doit gérer un vector, sinon la on peux avoir que un seul hashtag à la fois .....
    // du coup faire un set et un remove
    // le set ajoutera et le remove enlève (attention au exception, si le hashtag existe déja ou si il n'existe pas etc.)
    private void setHastag(Vector<String> Has)
    {
        int i = 0;
        for(i=0;i<Has.size();i++)
        {
            this.Hashtag.add(Has.elementAt(i));
        }
    }
   
    // Remplissage d'une note - UTILISATION FINALE :
    // MAuvaise utilisation | doit être un constructeur et non une méthode simple
    public void remplir(String TITRE, String TEXT, Vector<String> HASHTAG, int ID)
    {
        this.setHastag(HASHTAG);
        this.setId(ID);
        this.setTexte(TEXT);
        this.setTitre(TITRE);
    }
    
    // CrÃ©ation du dossier dans lequel seront stockÃ©e les notes exportÃ©es :
    private void creation_dossier_sauvegarde()
    {
        
    }
    
    // Export d'une note au format dÃ©sirÃ© (XML, HTML, PDF) : 
    public void export(String export_souhaite)
    {
        // Chemain et nom du rÃ©pertoir oÃ¹ seront stockÃ©s les fichiers exportÃ©s :
       
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
    
    // A dégager, pour  faire des test on met des constructeur qu'on appel dans un void main dans la classe de test
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