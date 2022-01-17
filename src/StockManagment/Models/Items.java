/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import static java.lang.Math.abs;
import java.sql.*;
import javafx.collections.*;

/**
 *
 * @author Naf
 */
public class Items{
    static Connection conn = null;
    public int itemid;
    public int scid;
    public String item_name;
    public String item_description;
    public int quantity;
    public String expire_date;
    public String date_created;
    public SubCategories sub_cat;
    
    public Items(){}
    public Items(int itemid) throws SQLException{
        this.itemid = itemid;
        setNewValuesOfData("");
        //System.out.println(this.sub_cat.sc_name);
    }
    
    /**
     * Returns all Items
     * @return ObservableList<ItemsModel>
     */
    public static ObservableList<ItemsModel> getItems() throws SQLException{
        ObservableList<ItemsModel> items = FXCollections.observableArrayList();
        String query = "SELECT * FROM items";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ItemsModel it;
            while(rs.next()){
                it = new ItemsModel(rs.getInt("itemid"),rs.getInt("scid"),rs.getString("item_name"),rs.getString("item_description"),rs.getInt("quantity"),rs.getString("expire_date"),rs.getString("date_created"));
                items.add(it);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return items;
    }
    
    /**
     * Returns all Items
     * @return ObservableList<ItemsModel>
     */
    public static ObservableList<ItemsModel> getItems(int item_id) throws SQLException{
        ObservableList<ItemsModel> items = FXCollections.observableArrayList();
        String query = "SELECT * FROM items WHERE itemid="+item_id;
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ItemsModel it;
            while(rs.next()){
                it = new ItemsModel(rs.getInt("itemid"),rs.getInt("scid"),rs.getString("item_name"),rs.getString("item_description"),rs.getInt("quantity"),rs.getString("expire_date"),rs.getString("date_created"));
                items.add(it);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return items;
    }
    
    
    /**
     * Returns all Items
     * @return ObservableList<ItemsModel>
     */
    public static ObservableList<ItemsModel> getItems(String type) throws SQLException{
        ObservableList<ItemsModel> items = FXCollections.observableArrayList();
        String query = "SELECT * FROM items WHERE quantity="+0;
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ItemsModel it;
            while(rs.next()){
                it = new ItemsModel(rs.getInt("itemid"),rs.getInt("scid"),rs.getString("item_name"),rs.getString("item_description"),rs.getInt("quantity"),rs.getString("expire_date"),rs.getString("date_created"));
                items.add(it);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return items;
    }
    
    /**
     * 
     * @param scid
     * @param item_name
     * @param item_description
     * @param expire_date
     * @param date_created
     * @return 
     */
    public boolean addNewItem(int scid,String item_name,String item_description,int quantity, String expire_date) throws SQLException{
        String query;
        if(expire_date.equalsIgnoreCase("")){
            query = "INSERT INTO items(scid, item_name, item_description, quantity, expire_date) VALUES("+scid+",'"+item_name+"','"+item_description+"',"+quantity+",null)";
        }else{
            query = "INSERT INTO items(scid, item_name, item_description, quantity, expire_date) VALUES("+scid+",'"+item_name+"','"+item_description+"',"+quantity+",'"+expire_date+"')";
        }
                
        Statement st;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("NOW");
            return true;
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
            return false;
        }finally{
         if(conn!=null)
          conn.close();
        }
    }

    /**
     * 
     * @param scid
     * @param item_name
     * @param item_description
     * @param quantity
     * @param expire_date
     * @return 
     */
    public boolean updateThisItem(int scid,String item_name,String item_description,int quantity, String expire_date) throws SQLException{
        String query = "UPDATE Items SET scid="+scid+", item_name="+item_name+", item_description="+item_description+", quantity="+quantity+", expire_date="+expire_date+", WHERE itemid="+this.itemid;
        Statement st;

        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("");
            return true;
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
            return false;
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
    public boolean setQuantityTriger(int quantity, int t) throws SQLException{
        int ins;
        if(t==1)ins = abs(this.quantity-quantity);
        else if(t==2) ins = abs(this.quantity+quantity);
        else ins = quantity;
        String query = "UPDATE Items SET quantity="+ins+" WHERE itemid="+this.itemid;
        Statement st;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            setNewValuesOfData("");
            return true;
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
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
            query= "SELECT * FROM items";
        }else{
            query= "SELECT * FROM items WHERE itemid="+this.itemid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.itemid  = rs.getInt("itemid");
                this.scid  = rs.getInt("scid");
                this.item_name  = rs.getString("item_name");
                this.item_description  = rs.getString("item_description");
                this.quantity  = rs.getInt("quantity");
                this.expire_date  = rs.getString("expire_date");
                this.date_created  = rs.getString("date_created");
                sub_cat = new SubCategories(this.scid);
            }
        }catch(SQLException e){
            //System.out.println(e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
}
