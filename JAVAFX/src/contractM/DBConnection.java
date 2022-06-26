package contractM;


import java.sql.Connection;
import java.sql.DriverManager;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DBConnection
{

    private static Connection dbConnection;
    
    private static String hostProvider = "localhost";
    private static String DBname = "MenaxhimiDB";
    private static String user = "root";
    private static String password = "pass";
    
    

    public static Connection setConnection()
    {
        try
        {
            String url = "jdbc:mysql://localhost:3306/MenaxhimiDB";
            String user = "root";
            String pass = "pass";
            Class.forName ("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection (url, "root", "pass");
            //Class.forName("com.mysql.cj.jdbc.Driver");

            
            //dbConnection = DriverManager.getConnection(url, user, password);
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database problem1");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage() + " Error: " + ex.getCause());
            alert.showAndWait();
            ex.printStackTrace();
        }

        return dbConnection;
    }

}

