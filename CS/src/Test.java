import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) {
		
		Document doc = null;
		Classeur clas = new Classeur(doc);
		Hashtag tag = new Hashtag("Love",134,clas);
		tag.AddId(12,34,65,46,7,10,28);
		System.out.println(tag.Infos(false));
	}

}
