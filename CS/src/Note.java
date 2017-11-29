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

// Outils permettant la génération de PDF :


// Import permettant de manipuler (lire et écrire des documents XML) :
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

// Gestion des exception - Note d�j� existante :  
class Note_deja_existante extends Exception
{ 
  private String Note_deja_existante()
  {
    String TYPE_ERREUR = "La note que vous essayez de cr�er existe d�j� - Elle ne sera pas cr�� deux fois";
    System.out.println(TYPE_ERREUR);
    return TYPE_ERREUR;
  }  
}

// Gestion des execptions - Cr�ation d'une note vide (sans texte) : 
class Note_Vide extends Exception
{  
	/** 
	* Cr�e une nouvelle instance de NombreNonValideException 
	*/  
	public Note_Vide() {}  
	/** 
	* Cr�e une nouvelle instance de NombreNonValideException 
	* @param message Le message d�taillant exception 
	*/  
	public Note_Vide(String message) 
        {  
		super(message); 
	}  
	/** 
	* Cr�e une nouvelle instance de NombreNonValideException 
	* @param cause L'exception � l'origine de cette exception 
	*/  
	public Note_Vide(Throwable cause) 
        {  
		super(cause); 
	}  
	/** 
	* Cr�e une nouvelle instance de NombreNonValideException 
	* @param message Le message d�taillant exception 
	* @param cause L'exception � l'origine de cette exception 
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

// Gestion des exeptions - Absence de HASHTAG lors de l'appel du deuxi�me constructeur :
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

// Gestion des exception - Hashtag d�j� existant :
class Hashtag_daja_existant extends Exception
{ 
  private String Hashtag_daja_existant()
  {
    String TYPE_ERREUR = "Le Hashtag existe d�ja - Il ne sera pas cr�� deux fois";
    System.out.println(TYPE_ERREUR);
    return TYPE_ERREUR;
  }  
}

public class Note
{
    // Attribus de la classe note (tout est en private, les accesseur et mutateur sont la pour ça) : 
    private String titre;
    private String text;
    private Vector<String> Hashtag; // doit �tre un vector pour mettre autant qu'on veux et pas un seul
    private int Id;  //manque l'auto incr�ment de id
    public String ERREUR_CREATION;
    
    // Savoir si une note est vide - Pour la gestion des execptions - vide si la fonction retoure TRUE:
    private boolean note_vide()
    {
        return text.isEmpty();
    }
    
    // Savoir si un hashtag exist� d�j� dans le vector HASHTAG :
    // Utile pour ne pas enregistrer deux fois le m�me HASHTAG :
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
    
    // Suppression des �l�ments qui pr�sents plusieurs fois dans le m�me vecteur :
    // Cette fonction est appel�e dans : 
    //          - le second constructeur de note
    //          - La fonction modifiant une note d�j� cr�er 
private static Vector<String> Redondense_HASHTAG(Vector<String> VEC_A_TRIER)
    { 
        int i = 0;
        int j = 0;
        int k = 1;
        int cpt = 0;
        String tmp = VEC_A_TRIER.elementAt(0);
        int taille = VEC_A_TRIER.size();
        Vector<String> v = new Vector(1,1);
       
        // Recopie du vecteur pour �viter la modification en cours de VEC_A_TRIER - MARCHE :
        for(i=0;i<VEC_A_TRIER.size();i++)
        {
             v.add(i, VEC_A_TRIER.elementAt(i));
             //System.out.println(v.elementAt(i));
        }
        
        // Boucle de fonctionnement limite : 
        // Gestion du cas ou l'�l�ments en posistion 0 est pr�sent plusieurs fois :
        for(i=1;i<VEC_A_TRIER.size();i++)
        {
            if(VEC_A_TRIER.elementAt(0).equals(VEC_A_TRIER.elementAt(i)))
            {
                v.removeElementAt(0);
                break;
            }
        }
        
        // La boucle de fonctionnement g�n�ral.
        // Cette boucle ne g�re pas le cas ou l'�l�ment en position 0 
        // dans le vectoeur est r�p�t� plusieurs fois : 
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
            // Si des occurences multiple sont trouv�es, on les supprimes : 
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
    
    // Constructeur par d�faut - Cr�ation d'une note � partir de sont TITRE et du TEXTE qu'elle contient :
    // RESTE A FAIRE : Gestion des ID
    public Note(String TITRE, String TEXTE)
    {
        this.Hashtag.setElementAt("UNDEFINE",0); // Par d�faut une note est cr�� avec une HASHTAG � la valeur UNDEFINED 
        this.setId(1); // L'identifiant de la premi�re note est mis a 1 par d�faut
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
                throw new Titre_Vide("Vous avez oubli� de donner un titre � votre document");
            }
        }
        // Modification de la variable globale gestion des erreurs :
        // Cette variable est � disposition de ceux qui voudraient impl�menter une 
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
    
    // Ce constructeur permettrat de cr�e une note � partir :
    //          - De son titre
    //          - Du texte qu'elle contient
    //          - D'un vector de contenant tout les HASHTAG pr�sents dans cette note 
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
                throw new Titre_Vide("Vous avez oubli� de donner un titre � votre document");
            }
            
            // Si le HASHTAG_IN_NOTE vide - Execption :
            if(HASHTAG_IN_NOTE.isEmpty()==true)
            {
                throw new HASHTAG_Vide("Aucun HASHTAG n'est pr�sent dans votre texte");
            }
        }
        // Modification de la variable globale gestion des erreurs :
        // Cette variable est � disposition de ceux qui voudraient impl�menter une 
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
            this.ERREUR_CREATION = this.ERREUR_CREATION + " " + "Aucun HASHTAG pr�sent dans cette note";
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
    private void setHastag(Vector<String> Has)
    {
        int i = 0;
        for(i=0;i<Has.size();i++)
        {
            this.Hashtag.add(Has.elementAt(i));
        }
    }
   
    // Remplissage d'une note - UTILISATION FINALE :
    // MAuvaise utilisation | doit �tre un constructeur et non une m�thode simple
    public void remplir(String TITRE, String TEXT, Vector<String> HASHTAG, int ID)
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