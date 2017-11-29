import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Markdown_Parser {

	public static String rules[][] = {
	
			{"/(#+)(.*)/'","this_header"}, // Headers
			{"/(\\*\\*|__)(.*?)\\1/","<strong>$2</strong>"}, // Strong
			{"/\\n\\*(.*)/","this_ul"}, // ul Lists
			{"/\\n[0-9]+\\.(.*)/","this_ol"}, // ol Lists
			{"/\\n-{5,}/","\\n<hr />"}, // Horizontal Bar
			{"/\\n([^\\n]+)\\n/","this_paragraph"}, // Paragraphs
			{"/<\\/ul>\\s?<ul>/",""}, // fix ul
			{"/<\\/ol>\\s?<ol>/",""} // fix ol
	};
	
	public Markdown_Parser()
	{
		
	}
	
	public String tohtml(String markdown)
	{
		
	}
	public String tomarkdown(String html)
	{
		
	}
}
