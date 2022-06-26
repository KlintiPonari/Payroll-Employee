package contractM;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;

import static contractM.SignUp.validateE;


public class Login extends Application
{
    private TextField emailTxt = new TextField();
    private PasswordField passwordTxt = new PasswordField();
    private TextField userTxt = new TextField();
    private Label errorLabel = new Label();
    public static ComboBox<String> languageCB = new ComboBox<>(FXCollections.observableArrayList("AL", "EN"));
    /*****/
    
    public static UserSession userSession;
    
    public static Stage mainStage;
    
    MainProgram LoginSuccessStage = new MainProgram();
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        
        mainStage = primaryStage;
        
        emailTxt.setPromptText("Email");
        passwordTxt.setPromptText("Password");
        userTxt.setPromptText("Email/Username");
        
        
        BorderPane bp = new BorderPane();
        
        
        HBox languageHBox = new HBox();
        
        
        HBox hbLogin = new HBox();
        
        
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(0, 0, 120, 0));
        mainGrid.setAlignment(Pos.CENTER);
        
        GridPane gridPane = new GridPane();
        
        gridPane.setHgap(9);
        gridPane.setVgap(9);
        
        
        Image usernameIcon = new Image("file:Images/icon.png");
        ImageView usernameIconIV = new ImageView(usernameIcon);
        usernameIconIV.setFitWidth(20);
        usernameIconIV.setFitHeight(20);
        
        Image usernameIcon2 = new Image("file:Images/icon3.png");
        ImageView usernameIconIV2 = new ImageView(usernameIcon2);
        usernameIconIV2.setFitWidth(20);
        usernameIconIV2.setFitHeight(20);
        
        Image usernameIcon3 = new Image("file:Images/email.png");
        ImageView usernameIconIV3 = new ImageView(usernameIcon3);
        usernameIconIV3.setFitWidth(20);
        usernameIconIV3.setFitHeight(20);
        
        
        HBox hb2 = new HBox();
        
        Button btnLogin = I18N.buttonForKey("Login");
        btnLogin.setTextFill(Color.rgb(186, 201, 209));
        btnLogin.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48");
        Button btnRegister = I18N.buttonForKey("Register");
        btnRegister.setTextFill(Color.rgb(186, 201, 209));
        btnRegister.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48");
        
        
        hb2.getChildren().addAll(btnLogin, btnRegister);
        hb2.setSpacing(5);
        
        
        btnLogin.setOnAction(e ->
        {
            this.loginUser();
            MainScene.languageCB.setValue(languageCB.getValue());
        });
        
        
        mainGrid.add(hbLogin, 0, 0);
        mainGrid.add(gridPane, 0, 1);
        
        gridPane.add(usernameIconIV, 0, 1);
        gridPane.add(userTxt, 1, 1);
        gridPane.add(usernameIconIV2, 0, 2);
        gridPane.add(passwordTxt, 1, 2);
        gridPane.add(hb2, 1, 3);
        
        userTxt.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                this.loginUser();
        });
        
        passwordTxt.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                this.loginUser();
        });
        
        
        gridPane.setStyle("-fx-background-color: #ffffff  ;\r\n" +
         " -fx-padding: 10 10 10 10;\r\n" +
         " -fx-background-radius: 20;");
        
        
        Text text = new Text("Login");
        text.setTextAlignment(TextAlignment.CENTER);
        
        text.setStyle("-fx-font-family: Pacifico;-fx-font-size:30");
        text.setFill(Color.rgb(196, 206, 212));
        
        
        hbLogin.getChildren().add(text);
        
        hbLogin.setAlignment(Pos.CENTER);
        
        HBox hBoxError = new HBox();
        
        errorLabel.setTextFill(Color.RED);
        
        
        hBoxError.getChildren().add(errorLabel);
        hBoxError.setStyle("-fx-background-color: rgba(239,235,235,0.37); -fx-padding: 20px; -fx-alignment: " +
         "center-right;");
        
        
        languageHBox.getChildren().addAll(I18N.getLabel("languageLabel"), languageCB);
        languageHBox.setStyle("-fx-padding: 20px 20px 0px 0px; -fx-spacing: 4px;");
        languageHBox.setAlignment(Pos.BASELINE_RIGHT);
        languageCB.setValue("EN");
        languageCB.setOnAction(e ->
        {
            I18N.setLocale(new Locale(languageCB.getValue().toLowerCase()));
            errorLabel.setText("");
            errorLabel.setTextFill(Color.RED);
            userTxt.setPromptText(I18N.getLabel("user").getText());
            emailTxt.setPromptText(I18N.getLabel("Email").getText());
            passwordTxt.setPromptText(I18N.getLabel("Password").getText());
            
        });
        
        
        languageCB.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: #000000; " +
         "-fx-background-color: #00000000;");
        
        
        bp.setStyle("-fx-background-color: linear-gradient(to bottom right, #F3DAC6, #018786);");
        
        bp.setCenter(mainGrid);
        bp.setBottom(hBoxError);
        bp.setTop(languageHBox);
        
        
        Scene scene = new Scene(bp, 500, 500);
        
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Qahiri&display=swap");
        
        
        SignUp signup = new SignUp();
        
        Scene scene2 = new Scene(signup, 500, 500);
        
        signup.btnLogIn.setOnAction(e ->
        {
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.setResizable(false);
            languageCB.setValue(SignUp.languageCB.getValue());
        });
        
        scene2.getStylesheets().add("https://fonts.googleapis.com/css2?family=Qahiri&display=swap");
        btnRegister.setOnMouseClicked(e ->
        {
            primaryStage.setScene(scene2);
            primaryStage.setTitle("Sign up");
            primaryStage.setResizable(false);
            SignUp.languageCB.setValue(languageCB.getValue());
            signup.emailTxt.setText("");
            signup.passwordTxt.setText("");
            signup.userTxt.setText("");
            
        });
        
        
        btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("  -fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48; -fx-cursor: hand;"));
        btnLogin.setOnMouseExited(e -> btnLogin.setStyle(" -fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        
        btnRegister.setOnMouseEntered(e -> btnRegister.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        btnRegister.setOnMouseExited(e -> btnRegister.setStyle(" -fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 15px 3px 15px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
    
    
    
     
        
        
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        
        
        primaryStage.show();
        gridPane.requestFocus();
    }
    
    
    private void loginUser()
    {
        
        
        if (userTxt.getText().isEmpty() || passwordTxt.getText().isEmpty())
        {
            errorLabel.setText(I18N.getLabel("Fill").getText());
            errorLabel.setTextFill(Color.RED);
        }
        else
        {
            String query =
             "Select * from managers where username = ? AND upassword = ?";
            String Equery = "Select * from managers where uemail = ? AND upassword = ?";
            try
            {
                
                PreparedStatement preparedStatement = DBConnection.setConnection().prepareStatement(query);
                
                preparedStatement.setString(1, userTxt.getText());
                preparedStatement.setString(2, passwordTxt.getText());
                
                ResultSet result = preparedStatement.executeQuery();
                
                PreparedStatement preparedEStatement = DBConnection.setConnection().prepareStatement(Equery);
                
                preparedEStatement.setString(1, userTxt.getText());
                preparedEStatement.setString(2, passwordTxt.getText());
                
                ResultSet Eresult = preparedEStatement.executeQuery();
                
                if (result.next() || Eresult.next())
                {
                    
                    mainStage.hide();
                    
                    if (validateE(userTxt.getText()))
                    {
                        String user = GetUsername(userTxt.getText());
                        Login.userSession = UserSession.getInstance(user, new Date());
                    }
                    else
                        Login.userSession = UserSession.getInstance(userTxt.getText(), new Date());
                    
                    LoginSuccessStage.createMainStage();
                }
                else
                {
                    errorLabel.setText(I18N.getLabel("email").getText());
                    errorLabel.setTextFill(Color.RED);
                }
                
            }
            catch (SQLException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database problem2");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
                System.exit(0);
            }
            
        }
        
    }
    
    
    private String GetUsername(String email)
    {
        ResultSet rSet;
        
        String Query = "SELECT username FROM managers WHERE uemail = '" + email + "'";
        try
        {
            Statement preparedStatement = DBConnection.setConnection().createStatement();
            rSet = preparedStatement.executeQuery(Query);
        }
        catch (Exception e1)
        {
            rSet = null;
            //e1.printStackTrace(); /** Uncomment this line if there is an error*/
        }
        
        try
        {
            if (rSet.next())
            {
                String user = rSet.getString(1);
                return user;
            }
            return "ERROR";
        }
        catch (SQLException | NullPointerException ignored) { return "ERROR";}
    }
    
    
}


