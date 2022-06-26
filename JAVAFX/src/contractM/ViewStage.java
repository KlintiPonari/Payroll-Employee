package contractM;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewStage extends Stage
{

    private Scene scene;

    public ViewStage()
    {
        super();

    }

    public void setup()
    {
        this.scene = new Scene(new ViewAll(), 720, 480);

        this.setX((Screen.getPrimary().getVisualBounds().getWidth() - 720) / 2);
        this.setY((Screen.getPrimary().getVisualBounds().getHeight() - 480) / 2); this.setTitle("Employees Data");
        this.setScene(scene); this.sizeToScene();
    }

}
