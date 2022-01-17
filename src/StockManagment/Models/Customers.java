/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import java.sql.*;
import java.time.LocalDate;
import javafx.collections.*;
/**
 *
 * @author Naf
 */
public class Customers{
    public static Connection conn = null;
    public int cusid;
    public int itemid;	
    public String fname;
    public String lname;
    public String address;
    public String phone;
    public String date_created;
    public Items item;

    public Customers(){}
    public Customers(int cusid) throws SQLException{
        this.cusid=cusid;
        setNewValuesOfData("");
    }
    
    /**
     * 
     * @return 
     */
    public static ObservableList<CustomersModel> getCustomers() throws SQLException{
        ObservableList<CustomersModel> customers = FXCollections.observableArrayList();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            CustomersModel cmrs;
            while(rs.next()){
                cmrs = new CustomersModel(rs.getInt("cusid"), rs.getInt("itemid"), rs.getInt("quantity"), rs.getString("fname"), rs.getString("lname"), rs.getString("address"), rs.getString("phone"), rs.getString("date_created"));
                customers.add(cmrs);
            }
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return customers;
    }
    
    /**
     * Returns all Items
     * @return ObservableList<ItemsModel>
     */
    public static ObservableList<CustomersModel> getCustomers(String interval) throws SQLException{
        LocalDate date = LocalDate.now();  
        ObservableList<CustomersModel> customers = FXCollections.observableArrayList();
        String query;
        if(interval.equalsIgnoreCase("day")){
            query = "SELECT * FROM customers WHERE date_created >= CURRENT_DATE";
        }else if(interval.equalsIgnoreCase("month")){
            String big = date.getYear()+"-"+date.getMonthValue()+"-01";
            query = "SELECT * FROM customers WHERE date_created >= "+big;
        }else if(interval.equalsIgnoreCase("year")){
            String big = date.getYear()+"-01-01";
            query = "SELECT * FROM customers WHERE date_created >= "+big;
        }else{
            query = "SELECT * FROM customers ";
        }
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            CustomersModel cmrs;
            while(rs.next()){
                cmrs = new CustomersModel(rs.getInt("cusid"), rs.getInt("itemid"), rs.getInt("quantity"), rs.getString("fname"), rs.getString("lname"), rs.getString("address"), rs.getString("phone"), rs.getString("date_created"));
                customers.add(cmrs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
         if(conn!=null)
          conn.close();
        }
        return customers;
    }
    
    /**
     * 
     * @param itemid
     * @param fname
     * @param lname
     * @param address
     * @param phone
     * @return 
     */
    public boolean addNewCustomer(int itemid, int quantity,String fname, String lname, String address, String phone) throws SQLException{
        String query = "INSERT INTO customers(itemid,quantity,fname,lname,address,phone) VALUES("+itemid+","+quantity+",'"+fname+"','"+lname+"','"+address+"','"+phone+"')";
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
     * @return 
     */
    public boolean deleteThisCustomer() throws SQLException{
        String query = "DELETE customers WHERE cusid="+this.cusid;
        Statement st;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        }catch(SQLException e){
            //System.out.println("Erroer : "+e.getMessage());
            return false;
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
    /**
     * 
     * @param now 
     */
    private void setNewValuesOfData(String now) throws SQLException{
        String query;
        if(now.equals("NOW")){
            query = "SELECT * FROM customers";
        }else{
            query = "SELECT * FROM customers WHERE cusid="+this.cusid;
        }
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.itemid  = rs.getInt("itemid");
                this.fname  = rs.getString("fname");
                this.lname  = rs.getString("lname");
                this.address  = rs.getString("address");
                this.phone  = rs.getString("phone");
                this.date_created  = rs.getString("date_created");
                item = new Items(this.itemid);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
}
