/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import java.sql.*;

/**
 *
 * @author Naf
 */
public class DatabaseConnection {
    private static Connection conn;
    private static final String host_name = "localhost:3306";
    private static final String host_user = "root";
    private static final String host_pass = "";
    private static final String db_name = "stock_new_old";
    private static final String conn_url = "jdbc:mysql://"+host_name+"/"+db_name;
    
    public static Connection getDbConn(){
        try{
            conn =DriverManager.getConnection(conn_url,host_user,host_pass);
            return conn;
        }catch(SQLException e){
            System.out.println("Db Connection Error :" + e.getMessage());
            return null;
        }
    }
    
}
