package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "databaselol";
        String databaseUser = "root";
        String databasePassword = "!Iloriana12";
        String url = "jdbc:mysql://localhost:3306/databaselol?autoReconnect=true&useSSL=false";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;

    }


}
