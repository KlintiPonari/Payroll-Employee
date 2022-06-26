package contractM;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class HomeContent extends BorderPane
{
    
    
    public static  Button review1_label2 = new Button("ADD EMPLOYEES");
    public static  Button review1_label3 = new Button("VIEW EMPLOYEES");
    public static Button review1_label4 = new Button("UPDATE EMPLOYEES");
    public static Button homeImageButton = new Button("Info");
    
    
    public HomeContent(Stage primaryStage, MainScene mainScene)
    {
        super();
        this.setup(primaryStage, mainScene);
    }
    
    private void setup(Stage primaryStage, MainScene mainScene)
    {
        
        Image usernameIcon = new Image("file:Images/welcome.png");
        ImageView usernameIconIV = new ImageView(usernameIcon);
        usernameIconIV.setFitWidth(500);
        usernameIconIV.setFitHeight(200);
        
        Text homeImageDescription = I18N.getText("Welcometext");
        
       
        
        
        homeImageDescription.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        homeImageDescription.setStyle("-fx-fill: linear-gradient(to bottom right, #003f3f, #048080);");
        homeImageButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #003f3f, #00ffff); " +
         "-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: black; -fx-cursor: hand;");
        homeImageButton.setOnMouseEntered(e ->
        {
            homeImageButton.setStyle("-fx-background-color: #000000; -fx-border-width: 1px; -fx-border-style: solid; " +
             "-fx-border-color: black; -fx-cursor: hand; -fx-text-fill: aqua;");
        });
        homeImageButton.setOnMouseExited(e ->
        {
            homeImageButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #003f3f, #00ffff); " +
             "-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: black; -fx-cursor: hand; " +
             "-fx-text-fill: black;");
        });
        homeImageButton.setOnAction(e -> Help.about());
        
        
        VBox leftVbox = new VBox();
        
        
        leftVbox.setSpacing(70);
        
        leftVbox.getChildren().addAll(usernameIconIV, homeImageDescription, homeImageButton);
        
        
        leftVbox.setPadding(new Insets(40, 0, 0, 100));
        
        
        var rightVbox = new VBox();
        rightVbox.setStyle("-fx-background-color: #b0deeb;");
        
        rightVbox.setSpacing(10);
        
        VBox Review1 = new VBox();
        Review1.setStyle("-fx-background-color: #9607f9; -fx-padding: 10px; -fx-background-radius: 5;");
        
        Image stars = new Image("file:Images/Stars1.png");
        ImageView starsview = new ImageView(stars);
        
        
        Label review1_label = new Label("500+ OFFICIAL USERS");
        review1_label.setTextFill(Color.WHITE);
        review1_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        review1_label.setPadding(new Insets(40, 0, 0, 0));
        Label review1_label1 = new Label("MANAGE EMPLOYEES");
        review1_label1.setTextFill(Color.WHITE);
        review1_label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        
        Review1.getChildren().addAll(starsview, review1_label, review1_label1);
    
    
        MainScene.errorLabel.setText("");
        
        
        
        
        VBox Review2 = new VBox();
        Review2.setStyle("-fx-background-color: #fd0627; -fx-padding: 10px; -fx-background-radius: 5;");
        Image stars2 = new Image("file:Images/stars2.png");
        ImageView stars2view = new ImageView(stars2);
        
        Label review2_label = new Label("TOP TIER INTERACTIVE");
        review2_label.setTextFill(Color.WHITE);
        review2_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        review2_label.setPadding(new Insets(40, 0, 0, 0));
        Label review2_label1 = new Label("MULTI LANGUAGE");
        review2_label1.setTextFill(Color.WHITE);
        review2_label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        Review2.getChildren().addAll(stars2view, review2_label, review2_label1);
        
        
        HBox ButtonBox = new HBox();
        ButtonBox.setSpacing(20);
        ButtonBox.setPadding(new Insets(90, 0, 0, 0));
        ButtonBox.setAlignment(Pos.CENTER);
        
        Button Right = new Button();
        Image rightarrow = new Image("file:Images/rightarrow.png");
        ImageView rightarrview = new ImageView(rightarrow);
        rightarrview.setFitHeight(33);
        rightarrview.setFitWidth(33);
        Right.setGraphic(rightarrview);
        Right.setPadding(Insets.EMPTY);
        Right.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-border-style: solid; " +
         "-fx-border-color: black; -fx-cursor: hand; -fx-padding: 15px");
        
        
        Button Left = new Button();
        Image leftarrow = new Image("file:Images/leftarrow.png");
        ImageView leftarrview = new ImageView(leftarrow);
        leftarrview.setFitHeight(33);
        leftarrview.setFitWidth(33);
        Left.setGraphic(leftarrview);
        Left.setPadding(Insets.EMPTY);
        Left.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-border-style: solid; " +
         "-fx-border-color: black; -fx-cursor: hand; -fx-padding: 15px");
        
        ButtonBox.getChildren().addAll(Left, Right);
        
        
    
        
        Image diagram = new Image("file:Images/Diagram.png");
        ImageView diagramView = new ImageView(diagram);
        diagramView.setFitWidth(197);
        diagramView.setFitHeight(270);
        diagramView.setTranslateX(100);
        diagramView.setTranslateY(-30);
        
        
        rightVbox.getChildren().addAll(Review1, Review2, ButtonBox);
        
        rightVbox.setPadding(new Insets(100, 120, 0, 180));
    
        
        AtomicInteger i = new AtomicInteger();
    
       
        review1_label2.setTextFill(Color.WHITE);
        review1_label2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        review1_label2.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 2px 0 0 0");
        Label starsview1 = new Label("SERVICES THAT WE OFFER ");
        starsview1.setStyle("-fx-opacity: 0.9; -fx-text-fill: white; -fx-font-style: italic;" +
         "-fx-font-family: 'monospace'; -fx-font-size: 11px");
      
        review1_label3.setTextFill(Color.WHITE);
        review1_label3.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        starsview1.setPadding(new Insets(0, 0, 40, 0));
        review1_label3.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 2px 0 0 0");
    
        review1_label4.setTextFill(Color.WHITE);
        review1_label4.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        review1_label4.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 2px 0 0 0");
      
        Right.setOnAction(e->
        {
            if(i.get() == 0)
            {
                Review1.getChildren().removeAll(starsview, review1_label, review1_label1);
                Review1.getChildren().setAll(starsview1, review1_label3, review1_label4, review1_label2);
                i.addAndGet(1);
            }
        });
    
        Left.setOnAction(e->
        {
            if(i.get() == 1)
            {
                Review1.getChildren().removeAll(starsview1, review1_label3, review1_label4,review1_label2);
                Review1.getChildren().setAll(starsview, review1_label, review1_label1);
                i.addAndGet(-1);
            }
        });
        this.setLeft(leftVbox);
        this.setRight(rightVbox);
        this.setCenter(diagramView);
    }
}
