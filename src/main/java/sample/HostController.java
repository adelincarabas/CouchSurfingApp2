package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Statement;


public class HostController{
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
    private Label Label4;
    @FXML
    private TextArea Area1;
    @FXML
    private TextArea Area2;
    @FXML
    private TextArea Area3;
    @FXML
    private TextArea Area4;
    @FXML
    private Button finish;
    @FXML
    private Button cancel;
    @FXML
    private Label registrationMessageLabel2;


    public void cancelButtonOnAction2(ActionEvent event){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void finishButtonOnAction(ActionEvent event){
        DatabaseConnection register = new DatabaseConnection();
        Connection connectDB = register.getConnection();
        String adress = Area1.getText();
        String description = Area2.getText();
        String why = Area3.getText();
        String local1 = Area4.getText();


        String insertFields = "INSERT INTO house(adress, description, why, local1) VALUES('";
        String insertValues = adress + "','" + description + "','" + why + "','" + local1 + "')";
        String insertToRegister = insertFields + insertValues;


        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel2.setText("Success");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }


        login1();
       Stage stage = (Stage) finish.getScene().getWindow();
        stage.close();
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
