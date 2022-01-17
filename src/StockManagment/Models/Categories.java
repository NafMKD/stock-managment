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
public class Categories {
    static Connection conn = null;
    public int cid;	
    public String cat_name;
    public String cat_description;
    public int status;
    public String date_created;

    
    public Categories(){}
    public Categories(int cid) throws SQLException{
        this.cid = cid;
        setNewValuesOfData("");
    }
    
    /**
     * Returns all categories
     * @return ObservableList<Categories>
     */
    public static ObservableList<CategoriesModel> getCategories() throws SQLException{
        ObservableList<CategoriesModel> categories = FXCollections.observableArrayList();
        String query = "SELECT * FROM categories";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            CategoriesModel cat;
            while(rs.next()){
                cat = new CategoriesModel(rs.getInt("cid"),rs.getString("cat_name"),rs.getString("cat_description"),rs.getInt("status"),rs.getString("date_created"));
                categories.add(cat);
            }
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return categories;
    }
        
    /**
     * Add New Category
     * @param cat_name_ins = category name to be inserted to db
     * @param cat_description_ins = category description to be inserted to db
     * @return boolean value
     */
    public boolean addNewCategory(String cat_name_ins, String cat_description_ins) throws SQLException{
        String query = "INSERT INTO categories(cat_name,cat_description) VALUES('"+cat_name_ins+"', '"+cat_description_ins+"')";
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
     * Update Category
     * @param cat_name_upd = changed category name
     * @param cat_description_upd = changed category description
     * @return 
     */
    public boolean updateThisCategory(String cat_name_upd, String cat_description_upd) throws SQLException{
        String query = "UPDATE categories SET cat_name='"+cat_name_upd+"', cat_description='"+cat_description_upd+"' WHERE cid="+this.cid;
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
     * Toggle Status of The Category
     * @return 
     */
    public boolean toggleStatusThisCategory() throws SQLException{
        String query;
        if(this.status==1){
            query= "UPDATE categories SET status="+0+" WHERE cid="+this.cid;
        }else{
            query= "UPDATE categories SET status="+1+" WHERE cid="+this.cid;
        }
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
            query= "SELECT * FROM categories";
        }else{
            query= "SELECT * FROM categories WHERE cid="+this.cid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.cid = rs.getInt("cid");
                this.cat_name = rs.getString("cat_name");
                this.cat_description = rs.getString("cat_description");
                this.status = rs.getInt("status");
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
