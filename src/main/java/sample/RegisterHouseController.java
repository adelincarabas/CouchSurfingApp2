package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.*;

public class RegisterHouseController {
    @FXML
    private TextArea done1text;
    @FXML
    private Button refresh2;
    @FXML
    private Button open;




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
            String sql= "SELECT * FROM user_account where account_id = "+smecher.l;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (dbUrl,dbUsr,dbPass);
            st = conn.createStatement();
            rs = st.executeQuery(sql);


            while(rs.next()){
                value = rs.getString("firstname");
                value2 = rs.getString("lastname");
                value3 = rs.getString("username");


            }
            text="First Name:\n"+value+"\n\nLast Name:\n"+value2+"\n\nUsername:\n"+value3;


            done1text.setText(text);

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




    public void refreshBT2(ActionEvent event){
        lol2();
    }

    public void lol2(){

        try {
            search();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openOnAction(ActionEvent event){
        Stage stage = (Stage) open.getScene().getWindow();
        stage.close();

    }


}
