package system;

import java.sql.*;

public class DB {
    private static DB db = null;

    private DB(){

    }

    public static DB getDB(){
        if (db == null){
            db = new DB();
        }
        return db;
    }

    public Connection getConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost/PoliticianMngSys";
            String user = "root";
            String password = "";
            return  DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println("Error Code: " +
                    ((SQLException) e).getErrorCode());
        }
        return null;
    }

}