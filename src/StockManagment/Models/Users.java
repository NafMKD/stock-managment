/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import java.sql.*;
import javafx.collections.*;
/**
 *
 * @author Naf
 */
public class Users{
    static Connection conn = null;
    public int eid; 
    public String username;    
    public String password;    
    public String last_login; 
    public String last_logout; 
    public String date_created;
    public Employees employee;

    public Users(){}
    public Users(int eid) throws SQLException{
        this.eid = eid;
        setNewValuesOfData("");
    }
    

    /**
     * 
     * @return 
     */
    public static ObservableList<UsersModel> getUsers() throws SQLException{
        ObservableList<UsersModel> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM users";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            UsersModel us;
            while(rs.next()){
                us = new UsersModel(rs.getInt("eid"),rs.getString("username"),rs.getString("password"),rs.getString("last_login"),rs.getString("last_logout"),rs.getString("date_created"));
                users.add(us);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return users;
    }
    
    /**
     * 
     * @param uid
     * @return 
     */
    public static ObservableList<UsersModel> getUsers(int eid) throws SQLException{
        ObservableList<UsersModel> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM users WHERE eid="+eid;
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            UsersModel us;
            while(rs.next()){
                us = new UsersModel(rs.getInt("eid"),rs.getString("username"),rs.getString("password"),rs.getString("last_login"),rs.getString("last_logout"),rs.getString("date_created"));
                users.add(us);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return users;
    }
    
    /**
     * 
     * @param username
     * @param password
     * @return 
     * @throws java.sql.SQLException 
     */
    public static ObservableList<UsersModel> getUsers(String username, String password) throws SQLException{
        ObservableList<UsersModel> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            UsersModel us;
            while(rs.next()){
                us = new UsersModel(rs.getInt("eid"),rs.getString("username"),rs.getString("password"),rs.getString("last_login"),rs.getString("last_logout"),rs.getString("date_created"));
                users.add(us);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return users;
    }

    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public boolean addNewUser(int eid, String username, String password) throws SQLException{
        String query = "INSERT INTO users(eid, username, password) VALUES("+eid+",'"+username+"', '"+password+"')";
        Statement st;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("NOW");
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }finally{
         if(conn!=null)
          conn.close();
        }
    }

    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public boolean updateThisUser(String username, String password) throws SQLException{
        String query = "UPDATE users SET username='"+username+"', password='"+password+"' WHERE eid="+this.eid;
        Statement st;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("");
            return true;
        }catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
    
    /**
     * Set the values of this object data fields
     */
    private void setNewValuesOfData(String now) throws SQLException{
        String query;
        if(now.equals("NOW")){
            query= "SELECT * FROM users";
        }else{
            query= "SELECT * FROM users WHERE eid="+this.eid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.last_login = rs.getString("last_login");
                this.last_logout = rs.getString("last_logout"); 
                this.date_created = rs.getString("date_created"); 
                this.employee = new Employees(this.eid);
            }
        }catch(SQLException e){
            //System.out.println(e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
}
