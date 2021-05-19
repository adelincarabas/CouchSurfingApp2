package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoneController {
    @FXML
    private TextArea donetext;
    @FXML
    private Button refresh;
    @FXML
    private Button back;



    public void search(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String text="";
        String value="";
        String value2="";
        String value3="";
        String value4="";
        int i;
        i=smecher.k;
        String dbUrl = "jdbc:mysql://localhost:3306/databaselol?autoReconnect=true&useSSL=false";
        String dbUsr = "root";
        String dbPass = "!Iloriana12";
        try{
            String sql= "SELECT * FROM house where id = "+i;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (dbUrl,dbUsr,dbPass);
            st = conn.createStatement();
            rs = st.executeQuery(sql);


            while(rs.next()){
                value = rs.getString("adress");
                value2 = rs.getString("description");
                value3 = rs.getString("why");
                value4 = rs.getString("local1");

            }
            text="Adress of the house:\n"+value+"\nTelephone Number:\n"+value2+"\nAbout me :\n"+value3+"\nPictures:\n"+value4;


            donetext.setText(text);

        }catch(Exception e){
            e.printStackTrace();
        }

    }




    public void refreshBT(ActionEvent event){
        lol2();
    }

    public void lol2(){

        try {
            System.out.println(smecher.k);
            search();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent event){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }


}