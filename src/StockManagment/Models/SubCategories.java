/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import javafx.collections.*;
import java.sql.*;
/**
 *
 * @author Naf
 */
public class SubCategories{
    static Connection conn = null;
    public int scid;
    public int cid;
    public String sc_name;
    public String sc_description;
    public String date_created;
    public Categories category;

    public SubCategories(){}
    public SubCategories(int scid) throws SQLException{
        this.scid = scid;
        setNewValuesOfData("");
    }
    
    
    /**
     * 
     * @return 
     */
    public static ObservableList<SubCategoriesModel> gatSubCategories() throws SQLException{
        ObservableList<SubCategoriesModel> sub_categories = FXCollections.observableArrayList();
        String query = "SELECT * FROM sub_categories";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SubCategoriesModel sc;
            while(rs.next()){
                sc = new SubCategoriesModel(rs.getInt("scid"),rs.getInt("cid"),rs.getString("sc_name"),rs.getString("sc_description"),rs.getString("date_created"));
                sub_categories.add(sc);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return sub_categories;
    }

    /**
     * 
     * @param cid
     * @param sc_name
     * @param sc_description
     * @return 
     */
    public boolean addNewCategory(int cid,String sc_name, String sc_description) throws SQLException{
        String query = "INSERT INTO sub_categories(cid,sc_name,sc_description) VALUES("+cid+",'"+sc_name+"', '"+sc_description+"')";
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
     * @param cid
     * @param sc_name
     * @param sc_description
     * @return 
     */
    public boolean updateThisSubCategory(int cid,String sc_name, String sc_description) throws SQLException{
        String query = "UPDATE sub_categories SET cid='"+cid+"',sc_name='"+sc_name+"', sc_description='"+sc_description+"' WHERE scid="+this.scid;
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
            query= "SELECT * FROM sub_categories";
        }else{
            query= "SELECT * FROM sub_categories WHERE scid="+this.scid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.cid = rs.getInt("cid");
                this.sc_name = rs.getString("sc_name");
                this.sc_description = rs.getString("sc_description");
                this.date_created = rs.getString("date_created");
                category = new Categories(this.cid);
            }
        }catch(SQLException e){
            //System.out.println(e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }  

}
