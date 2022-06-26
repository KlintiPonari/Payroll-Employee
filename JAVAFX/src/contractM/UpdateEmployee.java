package contractM;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class UpdateEmployee extends GridPane
{
    
    TextField empId = new TextField();
    TextField empName = new TextField();
    TextField empSurname = new TextField();
    TextField empBirth = new TextField();
    TextField empEmail = new TextField();
    TextField empContact = new TextField();
    TextField empAddress = new TextField();
    TextField empHours = new TextField();
    TextField empIdConBeg = new TextField();
    TextField empIdConEnd = new TextField();
    ComboBox<String> empDep = new ComboBox<>(FXCollections.observableArrayList(" Computer Engineer",
     " Electrical " + "Engineer", " Computer Scientist"));
    ComboBox<String> empJob = new ComboBox<>(FXCollections.observableArrayList(" Choose Department"));
    
    CheckBox activeChb = I18N.getCheckBox("Married");
    String Gender = "";
    ToggleGroup gender = new ToggleGroup();
    RadioButton femaleButton = I18N.getRadioButton("Female");
    RadioButton maleButton = I18N.getRadioButton("Male");
    RadioButton otherButton = I18N.getRadioButton("Other");
    TextField empNetto = new TextField();
    TextField empBonus = new TextField();
    TextField empDeduct = new TextField();
    TextField empSalary = new TextField();
    Button btUpdate = I18N.buttonForKey("Update");
    Button ClearButton = I18N.buttonForKey("Clear");
    
    
    LocalDate datetimeBirth;
    LocalDate datetimeBegin;
    LocalDate datetimeEnd;
    String datebirthstring = "";
    String dateBeginstring = "";
    String dateEndstring = "";
    
    
    ProgressIndicator progressIndicator = new ProgressIndicator();
    
    static double ii = 0;
    
    
    public UpdateEmployee(MainScene ms)
    {
        super(); this.setup(ms); this.AcKeys(ms);
    }
    
    private void setup(MainScene ms)
    {
        empDep.setOnAction(e ->
        {
            empJob.getItems().clear();
            if (empDep.getValue().equals(" Computer Engineer"))
                empJob.getItems().addAll(" Java Programmer", " .NET Programmer");
            else if (empDep.getValue().equals(" Computer Scientist"))
                empJob.getItems().addAll(" Data Scientist", " AI Development");
            else empJob.getItems().addAll(" Circ. Components", " Circuits");
        });
        
        
        DatePicker Birth_DatePicker = new DatePicker();
        DatePicker Contract_Begin = new DatePicker(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth()
         , (LocalDate.now().getDayOfMonth() + 4 <= LocalDate.now().getMonth().length(LocalDate.now().isLeapYear())) ?
          (LocalDate.now().getDayOfMonth() + 4) :
          ((LocalDate.now().getDayOfMonth() + 4) % LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
        DatePicker Contract_Due = new DatePicker(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
         (LocalDate.now().getDayOfMonth() + 4 <= LocalDate.now().getMonth().length(LocalDate.now().isLeapYear())) ?
          (LocalDate.now().getDayOfMonth() + 4) :
          ((LocalDate.now().getDayOfMonth() + 4) % LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
        
        HBox genderHBox = new HBox();
        
        genderHBox.getChildren().addAll(femaleButton, maleButton, otherButton);
        genderHBox.setSpacing(20);
        femaleButton.setToggleGroup(gender);
        femaleButton.setStyle("-fx-cursor: hand;");
        maleButton.setToggleGroup(gender);
        maleButton.setStyle("-fx-cursor: hand;");
        otherButton.setToggleGroup(gender);
        
        otherButton.setStyle("-fx-cursor: hand;");
        
        
        VBox employeeImageVBox = new VBox();
        
        Image employeeImage = new Image("file:Images/employers.png", 250, 250, true, true);
        ImageView employeeImageView = new ImageView(employeeImage);
        
        
        employeeImageVBox.setStyle("-fx-background-color: transparent;");
        employeeImageVBox.getChildren().addAll(employeeImageView);
        
        ClearButton.setStyle("-fx-background-color: #00000000; -fx-border-width: 1px; -fx-border-style: solid; " +
         "-fx-border-color: black; -fx-cursor: hand; -fx-text-fill: black;");
        ClearButton.setOnMouseEntered(e ->
        {
            ClearButton.setStyle("-fx-background-color: #000000; -fx-border-width: 1px; -fx-border-style: solid; " +
             "-fx-border-color: black; -fx-cursor: hand; -fx-text-fill: aqua;");
        }); ClearButton.setOnMouseExited(e ->
    {
        ClearButton.setStyle("-fx-background-color: #00000000; -fx-border-width: 1px; -fx-border-style: solid; " +
         "-fx-border-color: black; -fx-cursor: hand; -fx-text-fill: black;");
    });
        btUpdate.setStyle("-fx-background-color: #00000000; -fx-border-width: 1px; -fx-border-style: solid; " + "-fx" +
         "-border-color: black; -fx-cursor: hand; -fx-text-fill: black;");
        btUpdate.setOnMouseEntered(e ->
        {
            btUpdate.setStyle("-fx-background-color: #000000; -fx-border-width: 1px; -fx-border-style: solid; " +
             "-fx" + "-border-color: black; -fx-cursor: hand; -fx-text-fill: aqua;");
        }); btUpdate.setOnMouseExited(e ->
    {
        btUpdate.setStyle("-fx-background-color: #00000000; -fx-border-width: 1px; -fx-border-style: solid; " + "-fx" +
         "-border-color: black; -fx-cursor: hand; -fx-text-fill: black;");
    });
        
        this.setPadding(new Insets(100, 0, 0, 100)); this.setHgap(40); this.setVgap(10);
        Text label = I18N.getText("Employee_DetailsU");
        label.setStyle("-fx-font-size:20px; -fx-font-weight: bold; -fx-font-family: " +
         "Verdana;");
        label.setFill(Color.WHITE);
        label.setStroke(Color.BLACK);
        label.setStrokeWidth(1);
        
        
        HBox labelbox = new HBox();
        labelbox.setStyle("-fx-background-color: #b0deeb");
        labelbox.setPadding(new Insets(5, 0, 5, 250));
        labelbox.getChildren().add(label);
        
        
        this.add(labelbox, 0, 0, 4, 1);
        this.add(I18N.getLabel("Employee_ID"), 0, 1); this.add(empId, 1, 1);
        this.add(I18N.getLabel("First_Name"), 0, 2); this.add(empName, 1, 2);
        this.add(I18N.getLabel("Surname"), 0, 3); this.add(empSurname, 1, 3);
        this.add(I18N.getLabel("Date_Of_Birth"), 0, 4); this.add(empBirth, 1, 4);
        this.add(I18N.getLabel("Gender"), 0, 5); this.add(genderHBox, 1, 5);
        this.add(I18N.getLabel("e-mail"), 0, 6); this.add(empEmail, 1, 6); this.add(I18N.getLabel("Contact"), 0, 7);
        this.add(empContact, 1, 7); this.add(I18N.getLabel("Address"), 0, 8); this.add(empAddress, 1, 8);
        this.add(I18N.getLabel("Contract_begin_date"), 0, 9); this.add(empIdConBeg, 1, 9);
        this.add(I18N.getLabel("Contract_due_date"), 0, 10); this.add(empIdConEnd, 1, 10);
        
        this.add(I18N.getLabel("Hour_Work_(per_day)"), 2, 1); this.add(empHours, 3, 1);
        this.add(I18N.getLabel("Department"), 2, 2); this.add(empDep, 3, 2);
        this.add(I18N.getLabel("Job_Title"), 2, 3); this.add(empJob, 3, 3);
        this.add(I18N.getLabel("Status"), 2, 4); this.add(activeChb, 3, 4);
        this.add(I18N.getLabel("Nett_Salary"), 2, 5); this.add(empNetto, 3, 5);
        this.add(I18N.getLabel("Bonus_Payment"), 2, 6); this.add(empBonus, 3, 6);
        this.add(I18N.getLabel("Deduction_Payment"), 2, 7); this.add(empDeduct, 3, 7);
        this.add(I18N.getLabel("Salary"), 2, 8); this.add(empSalary, 3, 8);
        
        empSalary.setDisable(true);
        empName.setDisable(true);
        empSurname.setDisable(true);
        femaleButton.setDisable(true);
        maleButton.setDisable(true);
        otherButton.setDisable(true);
        
        
        empBirth.setDisable(true);
        empEmail.setDisable(true);
        empContact.setDisable(true);
        empIdConEnd.setDisable(true);
        empIdConBeg.setDisable(true);
        empHours.setDisable(true);
        empJob.setDisable(true);
        empDep.setDisable(true);
        empNetto.setDisable(true);
        empBonus.setDisable(true);
        empDeduct.setDisable(true);
        empAddress.setDisable(true);
        
        MainScene.errorLabel.setTextFill(Color.PURPLE);
        MainScene.errorLabel.setText(I18N.getLabel("EmployeeField").getText());
        
        ForceFieldINT();
        
        AutomaticSalary();
        
        empId.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        empName.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        empSurname.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent;");
        empBirth.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; " + "-fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empEmail.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empContact.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empAddress.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent;");
        empHours.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empIdConBeg.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empIdConEnd.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent; -fx-prompt-text-fill: rgba(78,55,55,0.76)");
        empDep.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        
        empJob.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        
        empNetto.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent;");
        empBonus.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        empDeduct.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        empSalary.setStyle(" -fx-border-color: #7100fd; -fx-border-width: 0 0 2 0; -fx-background-color: " +
         "transparent;");
        
        
        this.add(btUpdate, 2, 10); GridPane.setHalignment(btUpdate, HPos.RIGHT); this.add(ClearButton, 3, 10);
        GridPane.setHalignment(btUpdate, HPos.RIGHT);
        
        this.add(employeeImageVBox, 6, 2, 6, 7); this.add(progressIndicator, 12, 11);
        
        
        ClearButton.setOnAction(e -> clear());
        
        
        empId.setOnKeyTyped(e ->
        {
            
            ResultSet rSet;
            String Query = "SELECT COUNT(Employee_id) FROM employees WHERE Employee_id=" + empId.getText();
            try
            {
                Statement preparedStatement = DBConnection.setConnection().createStatement();
                rSet = preparedStatement.executeQuery(Query);
            }
            catch (Exception ignored)
            {
                
                rSet = null;
                clear();
            }
            
            try
            {
                if (rSet.next())
                {
                    
                    if (rSet.getInt(1) == 1)
                    {
                        GetData();
                        
                        MainScene.errorLabel.setText(I18N.getLabel("Found").getText());
                        MainScene.errorLabel.setTextFill(Color.GREEN);
                        empEmail.setDisable(false);
                        empContact.setDisable(false);
                        empIdConEnd.setDisable(false);
                        empIdConBeg.setDisable(false);
                        empHours.setDisable(false);
                        empJob.setDisable(false);
                        empDep.setDisable(false);
                        empNetto.setDisable(false);
                        empBonus.setDisable(false);
                        empDeduct.setDisable(false);
                        empAddress.setDisable(false);
                    }
                    else
                    {
                        
                        MainScene.errorLabel.setText(I18N.getLabel("NotFound").getText());
                        MainScene.errorLabel.setTextFill(Color.RED);
                        empName.setText(""); empSurname.setText(""); empBirth.setText(""); empContact.setText("");
                        empEmail.setText(""); empAddress.setText(""); empIdConBeg.setText(""); empIdConEnd.setText("");
                        empHours.setText(""); empDep.setValue(""); empJob.setValue(""); empBonus.setText("");
                        empDeduct.setText("");
                        empNetto.setText(""); empSalary.setText(""); gender.selectToggle(null);
                        empBirth.setDisable(true);
                        empEmail.setDisable(true);
                        empContact.setDisable(true);
                        empIdConEnd.setDisable(true);
                        empIdConBeg.setDisable(true);
                        empHours.setDisable(true);
                        empJob.setDisable(true);
                        empDep.setDisable(true);
                        empNetto.setDisable(true);
                        empBonus.setDisable(true);
                        empDeduct.setDisable(true);
                        empAddress.setDisable(true);
                    }
                    
                }
                
            }
            catch (Exception ignored)
            {
                MainScene.errorLabel.setTextFill(Color.PURPLE);
                
                MainScene.errorLabel.setText(I18N.getLabel("EmployeeField").getText());
                empBirth.setDisable(true);
                empEmail.setDisable(true);
                empContact.setDisable(true);
                empIdConEnd.setDisable(true);
                empIdConBeg.setDisable(true);
                empHours.setDisable(true);
                empJob.setDisable(true);
                empAddress.setDisable(true);
                empDep.setDisable(true);
                empNetto.setDisable(true);
                empBonus.setDisable(true);
                empDeduct.setDisable(true);
                //ignored.printStackTrace();
            }
        });
        
        
        btUpdate.setOnAction(e ->
        {
            boolean Fill = FillAll();
            if (Fill)
            {
                UpdateEmp();
            }
            else
            {
                MainScene.errorLabel.setText(I18N.getLabel("FillAll").getText());
                MainScene.errorLabel.setTextFill(Color.RED);
            }
        });
        
    }
    
    
    public void UpdateEmp()
    {
        getgender();
        
        String query =
         "UPDATE employees SET Employee_name='" + empName.getText() + "',Employee_surname='" +
          empSurname.getText() + "',Employee_birthday='" + empBirth.getText() + "',Employee_gender='" + Gender + "'," +
          "status='" + activeChb.isSelected() + "',Employee_number='" +
          empContact.getText() + "',Employee_email='" + empEmail.getText() + "',Employee_address='" + empAddress.getText() + "',Employee_hours='" + empHours.getText() + "' " +
          "WHERE Employee_id=" + empId.getText();
        
        String query2 =
         "UPDATE contracts SET Contract_date_begin='" + empIdConBeg.getText() + "',Contract_date_due='" + empIdConEnd.getText() +
          "',job_title='" + empJob.getValue() + "', department='" + empDep.getValue() + "', empSalary='"
          + empSalary.getText() + "' WHERE " + "EmpId=" + empId.getText();
        
        String query3 =
         "UPDATE payment SET " +
          "Employee_netto_salary='" + empNetto.getText() + "',payment_bonus='" + empBonus.getText() +
          "',tax_ammount='" + empDeduct.getText() + "' WHERE empId=" + empId.getText();
        
        
        try
        {
            PreparedStatement preparedStatement = DBConnection.setConnection().prepareStatement(query);
            
            preparedStatement.executeUpdate(query);
            
            PreparedStatement preparedStatement2 = DBConnection.setConnection().prepareStatement(query2);
            
            preparedStatement2.executeUpdate(query2);
            
            PreparedStatement preparedStatement3 = DBConnection.setConnection().prepareStatement(query3);
            
            preparedStatement3.executeUpdate(query3);
            
            
            MainScene.errorLabel.setText(I18N.getLabel("UEmployee").getText());
            MainScene.errorLabel.setTextFill(Color.GREEN);
            
            clear();
            
            ii += 1; ProgressIndicator(ii);
            
            this.setOnMouseClicked(e ->
            {
                ii += -1; ProgressIndicator(ii);
            });
        }
        catch (SQLException e)
        {
            
            MainScene.errorLabel.setText(I18N.getLabel("ParsingError").getText());
            MainScene.errorLabel.setTextFill(Color.RED);
            // e.printStackTrace();
        }
        
    }
    
    
    public void GetData()
    {
        ResultSet rSet;
        
        String query = "SELECT employees.*,contracts.*,payment.* FROM employees JOIN contracts ON " +
         " employees.Employee_id = contracts.EmpID " +
         "JOIN payment ON employees.Employee_id = payment.empID " +
         "WHERE  employees.Employee_id = " + empId.getText();
        
        try
        {
            Statement preparedStatement = DBConnection.setConnection().createStatement();
            rSet = preparedStatement.executeQuery(query);
        }
        catch (Exception e1)
        {
            rSet = null;
            //e1.printStackTrace(); /** Uncomment this line if there is an error
        }
        
        try
        {
            while (rSet.next())
            {
                empName.setText(rSet.getString("Employee_name"));
                empSurname.setText(rSet.getString("Employee_surname"));
                empBirth.setText(String.valueOf(rSet.getDate("Employee_birthday")));
                switch (rSet.getString("Employee_gender"))
                {
                    case "Female" -> gender.selectToggle(femaleButton);
                    case "Male" -> gender.selectToggle(maleButton);
                    case "Other" -> gender.selectToggle(otherButton);
                }
                boolean status = rSet.getBoolean("status");
                activeChb.setSelected(status);
                empContact.setText(rSet.getString("Employee_number"));
                empEmail.setText(rSet.getString("Employee_email"));
                empAddress.setText(rSet.getString("Employee_address"));
                empHours.setText(String.valueOf(rSet.getInt("Employee_hours")));
                
                
                empDep.setValue(rSet.getString("department"));
                empJob.setValue(rSet.getString("job_title"));
                empIdConBeg.setText(String.valueOf(rSet.getDate("Contract_date_begin")));
                empIdConEnd.setText(String.valueOf(rSet.getDate("Contract_date_due")));
                empSalary.setText(rSet.getString("empSalary"));
                empNetto.setText(rSet.getString("Employee_netto_salary"));
                empBonus.setText(rSet.getString("Payment_bonus"));
                empDeduct.setText(rSet.getString("tax_ammount"));
            }
        }
        catch (SQLException | NullPointerException e)
        {
            //e.printStackTrace(); /** Uncomment this line if there is an error with database
        }
        
        
    }
    
    public void getgender()
    {
        if (femaleButton.isSelected())
            Gender = "Female";
        else if (maleButton.isSelected())
            Gender = "Male";
        else if (otherButton.isSelected())
            Gender = "Other";
    }
    
    
    public void clear()
    {
        empId.setText(""); empName.setText(""); empSurname.setText(""); empBirth.setText(""); empContact.setText("");
        empEmail.setText(""); empAddress.setText(""); empIdConBeg.setText(""); empIdConEnd.setText("");
        empHours.setText(""); empDep.setValue(""); empJob.setValue(""); empBonus.setText(""); empDeduct.setText("");
        empNetto.setText(""); empSalary.setText(""); gender.selectToggle(null);
    }
    
    public boolean FillAll()
    {
        return
         !empId.getText().isEmpty() && !empName.getText().isEmpty() && (femaleButton.isSelected() || maleButton.isSelected()
          || otherButton.isSelected()) && !empSurname.getText().isEmpty() && !empBirth.getText().isEmpty() && !empContact.getText().isEmpty()
          && !empEmail.getText().isEmpty() && !empAddress.getText().isEmpty() && !empIdConBeg.getText().isEmpty() && !empIdConEnd.getText().isEmpty()
          && !empHours.getText().isEmpty() && !empDep.getValue().isEmpty() && !empJob.getValue().isEmpty() && !empBonus.getText().isEmpty()
          && !empDeduct.getText().isEmpty() && !empNetto.getText().isEmpty() && !empSalary.getText().isEmpty();
    }
    
    public void ProgressIndicator(double ii)
    {
        progressIndicator.setProgress(ii);
    }
    
    private void ForceFieldINT()
    {
        // force the field to be numeric only
        ForceINT(empBonus, empNetto, empDeduct, empHours);
        
        ForceINT(empContact, empId, empSalary, empHours);
    }
    
    private void ForceINT(TextField empContact, TextField empId, TextField empSalary, TextField empHours)
    {
        ForceIntExtract(empContact, empId);
        
        ForceIntExtract(empHours, empSalary);
        
    }
    
    private void ForceIntExtract(TextField empContact, TextField empId)
    {
        empId.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue.matches("\\d*"))
            {
                empId.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        empContact.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue.matches("\\d*"))
            {
                empContact.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    private void AutomaticSalary()
    {
        
        empNetto.textProperty().addListener((observable, oldValue, newValue) ->
        {
            try
            {
                empSalary.setText(String.valueOf(Integer.parseInt(empBonus.getText()) - Integer.parseInt(empDeduct.getText()) + Integer.parseInt(newValue)));
            }
            catch (NumberFormatException ignored)
            {
            
            }
        });
        
        
        empDeduct.textProperty().addListener((observable, oldValue, newValue) ->
        {
            try
            {
                empSalary.setText(String.valueOf(Integer.parseInt(empBonus.getText()) - Integer.parseInt(newValue) + Integer.parseInt(empNetto.getText())));
            }
            catch (NumberFormatException ignored)
            {
            
            }
        });
        
        
        empBonus.textProperty().addListener((observable, oldValue, newValue) ->
        {
            try
            {
                empSalary.setText(String.valueOf(-Integer.parseInt(empDeduct.getText()) + Integer.parseInt(newValue) + Integer.parseInt(empNetto.getText())));
            }
            catch (NumberFormatException ignored)
            {
            
            }
        });
        
        
    }
    
    //Access from keyboards
    
    private void AcKeys(MainScene scene)
    {
        
        KeyCombination ue = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
        KeyCombination de = new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN);
        
        Runnable rnU = () ->
        {
            boolean Fill = FillAll();
            if (Fill)
            {
                UpdateEmp();
            }
            else
            {
                MainScene.errorLabel.setText(I18N.getLabel("FillAll").getText());
                MainScene.errorLabel.setTextFill(Color.RED);
            }
        };
        
        Runnable rnD = this::clear;
        
        scene.getAccelerators().put(ue, rnU);
        
        scene.getAccelerators().put(de, rnD);
        
        
    }
    
    
}

