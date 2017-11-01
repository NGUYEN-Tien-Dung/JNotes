
public class Note {
	
	private int Id;
	private String Title;
	private String Text;
	private String[] Hashtag;
	
	/**
	 * constructeur de Note
	 */
	Note (int Id){
		
	}
	
	/**
	 * @return Id de la note
	 */
	public int getId() {
		return Id;
	}
	
	/**
	 * @return liste des Hashtag compris dans la Note
	 */
	public String[] getHashtag() {
		return Hashtag;
	}
	
	/**
	 * @return titre de la Note
	 */
	public String getTitle() {
		return Title;
	}
}
