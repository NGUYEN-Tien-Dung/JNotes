package storagexml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Note",propOrder= {"Id","Hashtag","titre","text"})
@XmlRootElement(name="Notes")
public class Note {
	private String titre;
    private String text;
    private String Hashtag; // doit être un vector pour mettre autant qu'on veux et pas un seul
    private int Id;  //manque l'auto incrément de id
    
    public void  setId(int Id) {
		this.Id = Id;
	}
    
    public void setHashtag(String Hashtag) {
    	this.Hashtag = Hashtag;
    }
    
    public void setTitre(String titre) {
    	this.titre = titre;
    }
    
    public void setText(String text) {
		this.text = text;
	}
    
}
