package contractM;


import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Help
{
    
    public static void about()
    {
        Tab newtab = new Tab();
        TabPane tbpane = new TabPane();
        
        Stage helpAboutStage = new Stage();
        newtab.setOnSelectionChanged(e ->
        {
            if (newtab.isSelected())
            {
                
                Tab tab = new Tab("Help");
                
                WebView browser = new WebView();
                
                WebEngine webEngine = browser.getEngine();
                
                String url = "";
                if (MainScene.languageCB.getValue().equals("EN"))
                    url = Help.class.getResource("HTML/info.html").toExternalForm();
                else if (MainScene.languageCB.getValue().equals("AL"))
                    url = Help.class.getResource("HTML/info-AL.html").toExternalForm();
                
                
                
                
                webEngine.load(url);
                
                tab.setContent(browser);
                
                tbpane.getTabs().add(
                 tbpane.getTabs().size() - 1, tab);
                
                
                tbpane.getSelectionModel().select(
                 tbpane.getTabs().size() - 2);
            }
        });
        
        tbpane.getTabs().add(newtab);
        
        Scene sc = new Scene(tbpane, 1000, 1000);
        
        helpAboutStage.setTitle("Help - About");
        helpAboutStage.setScene(sc);
        
        helpAboutStage.show();
        
    }
}
