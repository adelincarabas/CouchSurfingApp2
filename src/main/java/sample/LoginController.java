package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
      /* File brandingFile = new File("Images/itachi-uchiha-705.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);*/

       /* File lockFile = new File("Images/mqdefault.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);*/
    }


    public void loginButtonOnAction(ActionEvent event){

        if(!usernameTextField.getText().isEmpty() && !enterPasswordField.getText().isEmpty()){
           validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction2(ActionEvent event){
        DatabaseConnection register = new DatabaseConnection();
        Connection connectDB = register.getConnection();
        createAccountForm();
    }



    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        DatabaseConnection connectNow2 = new DatabaseConnection();

        Connection connectDB = connectNow.getConnection();
        Connection connectDB2 = connectNow2.getConnection();


        String verifyLogin = " SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText()  + "' AND password =MD5('" + enterPasswordField.getText() +"')";
        String insertFields3 = " UPDATE user_account SET password = MD5('"+enterPasswordField.getText()+ "') WHERE username = '" + usernameTextField.getText() +"'";

        try{
            Statement statement  = connectDB.createStatement();
            Statement statement2  = connectDB2.createStatement();
            statement2.executeUpdate(insertFields3);
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    login1();






                }else{
                    loginMessageLabel.setText("Invalid login, please try again");
                }
                lol();
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public static String text1 = "";
    public static String text2="";
    public void lol() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/databaselol?autoReconnect=true&useSSL=false";
        String dbUsr = "root";
        String dbPass = "!Iloriana12";
        try {
            String sql = "SELECT * FROM user_account where username  = '" + usernameTextField.getText() + "'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUsr, dbPass);
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                String value = rs.getString("password");
                String value2 = rs.getString("account_id");

                text1 =value;
                text2=value2;
            }
            System.out.println(text1);
            System.out.println("id is "+text2);
            smecher.l= Integer.parseInt(text2);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void createAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 613));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void login1(){
        try{

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/firstpage.fxml"));
            Stage firstPage = new Stage();
            firstPage.initStyle(StageStyle.UNDECORATED);
            firstPage.setScene(new Scene(root, 600, 470));
            firstPage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



}
