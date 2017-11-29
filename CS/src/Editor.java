import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

public class Editor extends AnchorPane  {

	
	@FXML
	private HTMLEditor htmlEditor;
	@FXML
	private Button showmarkdown;
	@FXML
	private Button showhtml;
	@FXML
	private TextArea markdownEditor;

	private String markdownelements[] = {"#markdownEditor","#showhtml"};
	private String htmlelements[] = {"#htmlEditor","#showmarkdown"};
	public Editor() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
			"/fxml/Editor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		showhtml.setOnAction((event) -> {
			String htmlRoot = "<body><font face=\"Segoe Ui\"> " + markdownEditor.getText() + "</font face></body></html>";
			htmlEditor.setHtmlText(htmlRoot);
			this.delete_components(markdownelements);
			this.show_components(htmlelements);
		});
		showmarkdown.setOnAction((event) -> {
			markdownEditor.setText(htmlEditor.getHtmlText());
			this.delete_components(htmlelements);
			this.show_components(markdownelements);
		});

	}
		

	
	  // Effacer touts les composants ayant le même style
	  private void delete_component(String style)
	  {
		  for (Node _component : this.lookupAll(style))
		  {
			  _component.setVisible(false); _component.setManaged(false);
		  }
	  }
	  // Effacer un seul composant
	  private void delete_component(String style, int order)
	  {
		  int i = 0;
		  for (Node _component : this.lookupAll(style))
		  {
			  if (i == order)
			  {
				  _component.setVisible(false); _component.setManaged(false);
			  }
			  i++;
		  }
	  }
	  // Effacer un tableau de composants 
	  private void delete_components(String[] vals)
	  {
		  for(int i = 0; i < vals.length; i++)
		  {
			  Node _component = this.lookup(vals[i]);
				_component.setVisible(false); _component.setManaged(false);
			  
		  }
	  }
	  
	  private void show_components(String[] vals)
	  {
		  for(int i = 0; i < vals.length; i++)
		  {
			  Node _component = this.lookup(vals[i]);
				_component.setVisible(true); _component.setManaged(true);
			  
		  }
	  }
	  
	  public void moveFromTo(HTMLEditor he, String t, int c, String t2, int c2, int pos)
      {
          Node nCb = new Button(); //just has to be sth.  

          //Copy From:
          int i = 0;
          switch(t)
          {
              case "ToggleButton":
                  for (Node candidate: (he.lookupAll("ToggleButton"))) 
                  {
                    if (candidate instanceof ToggleButton)
                    {
                    	ToggleButton cb = (ToggleButton) candidate;
                        if (i == c)
                        {
                            nCb = cb;
                            break;
                        }
                    }
                    i++;
                  }
                  break;   
              case "ComboBox":
                  for (Node candidate: (he.lookupAll("ComboBox"))) 
                  {
                    if (candidate instanceof ComboBox)
                    {
                    	ComboBox cb = (ComboBox) candidate;
                        if (i == c)
                        {
                            nCb = cb;
                            break;
                        }
                    }
                    i++;
                  }
                  break; 
              case "Button":
                  for (Node candidate: (he.lookupAll("Button"))) 
                  {
                    if (candidate instanceof Button)
                    {
                    	Button cb = (Button) candidate;
                        if (i == c)
                        {
                            nCb = cb;
                            break;
                        }
                    }
                    i++;
                  }
                  break;    
          }
          //Copy To:
          i = 0;
          switch(t2)
          {
              case "ToolBar":
                  for (Node candidate: (he.lookupAll("ToolBar"))) 
                  {
                    if (candidate instanceof ToolBar)
                    {
                        ToolBar cb2 = (ToolBar) candidate;
                        if (i == c2)
                        {
                            cb2.getItems().add(pos,nCb);
                            break;
                        }
                    }
                    i++;
                  }
                  break;    
          }
      }

	  public void render()
	  {
		  	String components[] = {".html-editor-align-right",".html-editor-align-left",".html-editor-align-justify",".html-editor-align-center",".html-editor-numbers"};
		  	this.delete_components(components);
		  	this.delete_component(".separator",4);
		  	this.delete_component(".color-picker");
		  	this.delete_component(".arrow");
		  	this.delete_component(".combo-box",1);
		  	this.delete_component(".combo-box",2);
		  	this.delete_component(".tool-bar",1);
		  	moveFromTo(htmlEditor, "ToggleButton", 6, "ToolBar", 0,4);
		  	moveFromTo(htmlEditor, "ToggleButton", 7, "ToolBar", 0,4);
		  	moveFromTo(htmlEditor, "ToggleButton", 8, "ToolBar", 0,4);
		  	moveFromTo(htmlEditor, "ToggleButton", 9, "ToolBar", 0,4);
		  	moveFromTo(htmlEditor, "Button", 5, "ToolBar", 0,17);
		  	moveFromTo(htmlEditor, "ComboBox", 0, "ToolBar", 0,20);
	  }

}