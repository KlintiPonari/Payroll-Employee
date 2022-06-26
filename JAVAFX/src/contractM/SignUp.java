package contractM;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends BorderPane
{
    public TextField emailTxt = new TextField();
    public PasswordField passwordTxt = new PasswordField();
    public TextField userTxt = new TextField();
    Button btnSignUp = I18N.buttonForKey("Register");
    Button btnLogIn = I18N.buttonForKey("Login");
    Label errorLabel = new Label();
    public static ComboBox<String> languageCB = new ComboBox<String>(FXCollections.observableArrayList("AL", "EN"));
    
    
    public SignUp()
    {
        userTxt.setPromptText(I18N.getLabel("useronly").getText());
        emailTxt.setPromptText(I18N.getLabel("Email").getText());
        passwordTxt.setPromptText(I18N.getLabel("Password").getText());
        
        
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
        
        Text textSignUp = new Text("Sign Up");
        textSignUp.setTextAlignment(TextAlignment.CENTER);
        
        textSignUp.setStyle("-fx-font-family: Pacifico;-fx-font-size:30");
        textSignUp.setFill(Color.rgb(196, 206, 212));
        
        
        HBox languageHBox = new HBox();
        
        
        btnSignUp.setTextFill(Color.rgb(186, 201, 209));
        btnSignUp.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48");
        
        
        btnLogIn.setTextFill(Color.rgb(186, 201, 209));
        btnLogIn.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48");
        
        
        btnLogIn.setOnMouseEntered(e -> btnLogIn.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        btnLogIn.setOnMouseExited(e -> btnLogIn.setStyle(" -fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        
        btnSignUp.setOnMouseEntered(e -> btnSignUp.setStyle("-fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        btnSignUp.setOnMouseExited(e -> btnSignUp.setStyle(" -fx-background-radius: 30, 30, 29, 28;\r\n" +
         "    -fx-padding: 3px 10px 3px 10px;\r\n" +
         "    -fx-background-color: #2C3E48;-fx-cursor: hand;"));
        
        HBox hbSignUp = new HBox();
        
        hbSignUp.getChildren().add(textSignUp);
        
        hbSignUp.setAlignment(Pos.CENTER);
        
        
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(0, 0, 120, 0));
        mainGrid.setAlignment(Pos.CENTER);
        
        GridPane gridPaneSU = new GridPane();
        
        gridPaneSU.setHgap(10);
        gridPaneSU.setVgap(10);
        
        HBox hbSignUp2 = new HBox();
        hbSignUp2.setPadding(new Insets(5, 30, 10, 5));
        hbSignUp2.getChildren().addAll(btnSignUp, btnLogIn);
        hbSignUp2.setSpacing(5);
        
        mainGrid.add(hbSignUp, 0, 0);
        mainGrid.add(gridPaneSU, 0, 1);
        gridPaneSU.add(usernameIconIV, 0, 0);
        gridPaneSU.add(userTxt, 1, 0);
        gridPaneSU.add(usernameIconIV3, 0, 1);
        gridPaneSU.add(emailTxt, 1, 1);
        gridPaneSU.add(usernameIconIV2, 0, 2);
        gridPaneSU.add(passwordTxt, 1, 2);
        gridPaneSU.add(hbSignUp2, 1, 3);
        
        gridPaneSU.setStyle("-fx-background-color: #ffffff  ;\r\n" +
         " -fx-padding: 20 10 10 10;\r\n" +
         " -fx-background-radius: 20;");
        
        /* Treat Errors */
        HBox hBoxError = new HBox();
        
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(new Font("Arial", 15));
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
            userTxt.setPromptText(I18N.getLabel("useronly").getText());
            emailTxt.setPromptText(I18N.getLabel("Email").getText());
            passwordTxt.setPromptText(I18N.getLabel("Password").getText());
        });
        languageCB.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-border-color: #000000; " +
         "-fx-background-color: #00000000;");
        
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #F3DAC6, #018786);");
        //this.setStyle("-fx-background-color:#2B4857;");
        this.setTop(languageHBox);
        this.setCenter(mainGrid);
        this.setBottom(hBoxError);
        btnSignUp.setOnAction(e -> this.SignUpUser());
        
        userTxt.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                this.SignUpUser();
        });
        
        emailTxt.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                this.SignUpUser();
        });
        
        passwordTxt.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                this.SignUpUser();
        });
        
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
     Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    
    private static final Pattern USER_NAME = Pattern.compile("^([A-Z])+([\\w]{3,})+$", Pattern.CASE_INSENSITIVE);
    
    private static final Pattern USER_PASSWORD = Pattern.compile("^(?=.*[0-9]).{6,15}$");
    
    public static boolean validateE(String emailStr)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    private static boolean validateUN(String usernameStr)
    {
        Matcher matcher = USER_NAME.matcher(usernameStr);
        return matcher.find();
    }
    
    private static boolean validatePSW(String passStr)
    {
        Matcher matcher = USER_PASSWORD.matcher(passStr);
        return matcher.find();
    }
    
    private void SignUpUser()
    {
        if (emailTxt.getText().isEmpty() || userTxt.getText().isEmpty() || passwordTxt.getText().isEmpty())
        {
            errorLabel.setText(I18N.getLabel("Fill").getText());
            errorLabel.setTextFill(Color.RED);
        }
        else if (!validateUN(userTxt.getText()))
        {
            errorLabel.setText(I18N.getLabel("UnotValid").getText());
            errorLabel.setTextFill(Color.RED);
            
        }
        else if (!validateE(emailTxt.getText()))
        {
            errorLabel.setText(I18N.getLabel("EnotValid").getText());
            errorLabel.setTextFill(Color.RED);
        }
        else if (!validatePSW(passwordTxt.getText()))
        {
            errorLabel.setText(I18N.getLabel("PnotValid").getText());
            errorLabel.setTextFill(Color.RED);
        }
        else
        {
            
            String query1 =
             "Insert into managers values ('" + userTxt.getText() + "','" + emailTxt.getText() + "','" + passwordTxt.getText() + "')";
            try
            {
                
                PreparedStatement st = DBConnection.setConnection().prepareStatement("select * from managers where " +
                 "username = ?");
                PreparedStatement ps = DBConnection.setConnection().prepareStatement("select * from managers where " +
                 "uemail = ?");
                st.setString(1, userTxt.getText());
                ps.setString(1, emailTxt.getText());
                ResultSet r1 = st.executeQuery();
                ResultSet r2 = ps.executeQuery();
                if (r1.next())
                {
                    errorLabel.setText(I18N.getLabel("Userexists").getText());
                    errorLabel.setTextFill(Color.RED);
                }
                else if (r2.next())
                {
                    errorLabel.setText(I18N.getLabel("Emailexists").getText());
                    errorLabel.setTextFill(Color.RED);
                }
                else
                {
                    
                    errorLabel.setText(I18N.getLabel("UserCreated").getText());
                    errorLabel.setTextFill(Color.GREEN);
                    Statement statement = DBConnection.setConnection().createStatement();
                    statement.executeUpdate(query1);
                    
                   
                    MainProgram signup = new MainProgram();
                    Login.userSession = UserSession.getInstance(userTxt.getText(), new Date());
                    signup.createMainStage();
                    emailTxt.setText("");
                    passwordTxt.setText("");
                    userTxt.setText("");
                    Login.mainStage.close();
                    
                    
                }
            }
            catch (SQLException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database problem2");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                System.exit(0);
            }
        }
        
        
    }
    
}
