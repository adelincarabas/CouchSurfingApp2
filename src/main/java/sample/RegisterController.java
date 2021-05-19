package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.IntPredicate;

public class RegisterController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private CheckBox userbox;
    @FXML
    private CheckBox hostbox;




    public void initialize(URL url2, ResourceBundle resourceBundle){
       /* File logoFile = new File("Images/download.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);*/
    }
    public void registerButtonOnAction(ActionEvent event){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            if(isValid(setPasswordField.getText())) {
                confirmPasswordLabel.setText("");
                registerUser();
            }
            else{
                confirmPasswordLabel.setText("Password does not have a higher case letter or a lower case letter or a number");
            }


        }else{
            confirmPasswordLabel.setText("Password does not match");
        }

    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String firstname2 = firstnameTextField.getText();
        String lastname2 = lastnameTextField.getText();

        if(userbox.isSelected()) {
            String insertFields = "INSERT INTO user_account(lastname, firstname, username, password) VALUES('";
            String insertValues = firstname2 + "','" + lastname2 + "','" + username + "','" + password + "')";
            String insertToRegister = insertFields + insertValues;


            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);

                registrationMessageLabel.setText("User has been registered succesfully");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }

        if(hostbox.isSelected()) {
            String insertFields2 = "INSERT INTO host_account(lastname2, firstname2, username, password) VALUES('";
            String insertValues2 = firstname2 + "','" + lastname2 + "','" + username + "','" + password + "')";
            String insertToRegister2 = insertFields2 + insertValues2;


            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister2);

                registrationMessageLabel.setText("User has been registered succesfully");
                createHostForm();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }

    }

    private boolean containsUpperCase(String value) {
        return contains(value, i -> Character.isLetter(i) && Character.isUpperCase(i));
    }
    private boolean containsNumber(String value) {
        return contains(value, Character::isDigit);
    }
    private boolean containsLowerCase(String value) {
        return contains(value, i -> Character.isLetter(i) && Character.isLowerCase(i));
    }
    private boolean contains(String value, IntPredicate predicate) {
        return value.chars().anyMatch(predicate);
    }
    public boolean isValid(String value) {
        return containsUpperCase(value)&&containsLowerCase(value)&&containsNumber(value);
    }

    public void createHostForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/host.fxml"));
            Stage hosthouse = new Stage();
            hosthouse.initStyle(StageStyle.UNDECORATED);
            hosthouse.setScene(new Scene(root, 600, 544));
            hosthouse.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
