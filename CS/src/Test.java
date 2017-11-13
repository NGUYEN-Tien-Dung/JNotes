import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) {
		
		Document doc = null;
		Classeur clas = new Classeur(doc);
		Hashtag tag = new Hashtag("Love",134,clas); // création d'un tag
		VectHashtag vectorH = new VectHashtag(tag); // création du vecteur de hashgtag static
		tag.AddId(12,34,65,46,7,10,28); 
		System.out.println(tag.Infos(false));
		tag.RemoveId(65,7,10);
		System.out.println(tag.Infos(false)); 
		Hashtag tag2 = new Hashtag("Foot",12); // création d'un deuxième tag (plus besoin de clas car il est static)
		vectorH.AddHashtag(tag2);
		Hashtag tag3 = new Hashtag("Foot",23);
		vectorH.AddHashtag(tag3);
		System.out.println(vectorH.getHashtag("Foot").Infos(false));
	}

}
