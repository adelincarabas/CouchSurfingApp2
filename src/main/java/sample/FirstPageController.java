package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class FirstPageController implements Initializable  {
    public int i,j=0;
    @FXML
    private TextArea textFirst;
    @FXML
    private Button button2;
    @FXML
    private TextField textwant;
    @FXML
    private Button search;
    @FXML
    private Button nextButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button profile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

  public void refreshButtonOnAction(ActionEvent event){
     lol();
    }

    public void exitButtonOnAction(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void lol(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String text="";
        String dbUrl = "jdbc:mysql://localhost:3306/databaselol?autoReconnect=true&useSSL=false";
        String dbUsr = "root";
        String dbPass = "!Iloriana12";
        try{
            String sql= "SELECT adress FROM house";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl,dbUsr,dbPass);
            st = conn.createStatement();
            rs = st.executeQuery(sql);


            while(rs.next()){
                String value = rs.getString("adress");
                j++;
                String lob = "Option number "+j;
                text = text+lob+"\n";
                text = text+value+"\n";
                text = text+"\n"+"\n"+"\n"+"\n"+"\n"+"\n";


            }
            textFirst.setText(text);

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

    public void searchBT(ActionEvent event){
      lol2();
    }

   public void lol2(){

       try {
           System.out.println(textwant.getText());
          smecher.k=Integer.parseInt(textwant.getText());
           System.out.println(smecher.k);
       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public void nextButtonOnAction(ActionEvent event){

           try{

               Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/done.fxml"));
               Stage registerStage = new Stage();
               registerStage.initStyle(StageStyle.UNDECORATED);
               registerStage.setScene(new Scene(root, 600, 400));
               registerStage.show();

           }catch(Exception e){
               e.printStackTrace();
               e.getCause();
           }


   }


    public void profileOnAction(ActionEvent actionEvent) {
        try{

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/registerhouse.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 470));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
