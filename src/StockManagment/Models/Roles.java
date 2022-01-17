/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import javafx.collections.*;
import java.sql.*;
import javafx.scene.control.Button;

/**
 *
 * @author Naf
 */
public class Roles {
    static Connection conn =null;
    public int rid;
    public String role_name;
    public String role_description;
    public String date_created;

    public Roles(){}
    public Roles(int rid) throws SQLException{
        this.rid = rid;
        setNewValuesOfData("");
    }
    

    /**
     * Returns all Roles
     * @return ObservableList<RolesModel>
     */
    public static ObservableList<RolesModel> getRoles() throws SQLException{
        ObservableList<RolesModel> roles = FXCollections.observableArrayList();
        String query = "SELECT * FROM role";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RolesModel rol;
            while(rs.next()){
                rol = new RolesModel(rs.getInt("rid"),rs.getString("role_name"),rs.getString("role_description"),rs.getString("date_created"));
                roles.add(rol);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return roles;
    }

    /**
     * 
     * @param role_name
     * @param role_description
     * @return 
     */
    public boolean addNewRole(String role_name, String role_description) throws SQLException{
        String query = "INSERT INTO role(role_name, role_description) VALUES('"+role_name+"', '"+role_description+"')";
        Statement st;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("NOW");
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
     * 
     * @param role_name
     * @param role_description
     * @return 
     */
    public boolean updateThisRole(String role_name, String role_description) throws SQLException{
        String query = "UPDATE role SET role_name='"+role_name+"', role_description='"+role_description+"' WHERE rid="+this.rid;
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
            query= "SELECT * FROM role";
        }else{
            query= "SELECT * FROM role WHERE rid="+this.rid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.role_name = rs.getString("role_name");
                this.role_description = rs.getString("role_description");
                this.date_created = rs.getString("date_created");
            }
        }catch(SQLException e){
            //System.out.println(e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
}
