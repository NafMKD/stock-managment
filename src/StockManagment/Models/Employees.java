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
public class Employees{
    static Connection conn = null;
    public int eid;
    public int rid;
    public String fname;
    public String mname;
    public String lname; 
    public String email;
    public String sex;
    public String birth_date;
    public String date_created;
    public Roles role;
    
    public Employees(){}
    public Employees(int eid) throws SQLException{
        this.eid = eid;
        setNewValuesOfData("");
        this.role = new Roles(this.rid);
    }
    
    /**
     * 
     * @return 
     */
    public static ObservableList<EmployeesModel> getEmployees() throws SQLException{
        ObservableList<EmployeesModel> employee = FXCollections.observableArrayList();
        String query = "SELECT * FROM employee";
        Statement st;
        ResultSet rs;
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            EmployeesModel em;
            while(rs.next()){
                em = new EmployeesModel(rs.getInt("eid"),rs.getInt("rid"),rs.getString("fname"),rs.getString("mname"),rs.getString("lname"),rs.getString("email"),rs.getString("sex"),rs.getString("birth_date"),rs.getString("date_created"));
                employee.add(em);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
        return employee;
    }
    
    /**
     * 
     * @param rid
     * @param fname
     * @param mname
     * @param lname
     * @param emai
     * @param sex
     * @param birth_date
     * @return 
     */
    public boolean addNewEmploye(int rid,String fname,String mname,String lname,String emai,String sex,String birth_date) throws SQLException{
        String query = "INSERT INTO employee(rid, fname, mname, lname, email, sex, birth_date) VALUES("+rid+",'"+fname+"','"+mname+"','"+lname+"','"+emai+"','"+sex+"','"+birth_date+"')";
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
     * @param rid
     * @param fname
     * @param mname
     * @param lname
     * @param emai
     * @param sex
     * @param birth_date
     * @return 
     */
    public boolean updateThisEmployee(int rid,String fname,String mname,String lname,String emai,String sex,String birth_date) throws SQLException{
        String query = "UPDATE employee SET rid="+rid+", fname="+fname+", mname="+mname+", lname="+lname+", email="+emai+", sex="+sex+", birth_date="+birth_date+" WHERE eid="+this.eid;
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
     * 
     * @param now 
     */
    private void setNewValuesOfData(String now) throws SQLException{
        String query;
        if(now.equals("NOW")){
            query = "SELECT * FROM employee";
        }else{
            query = "SELECT * FROM employee WHERE eid="+this.eid;
        }
        Statement st;
        ResultSet rs;
        
        try{
            conn = DatabaseConnection.getDbConn();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.eid = rs.getInt("eid");
                this.rid = rs.getInt("rid");
                this.fname = rs.getString("fname");
                this.mname = rs.getString("mname");
                this.lname = rs.getString("lname");
                this.email = rs.getString("email");
                this.sex = rs.getString("sex");
                this.birth_date = rs.getString("birth_date");
                this.date_created = rs.getString("date_created");
                this.role = new Roles(this.rid);
            }
        }catch(SQLException e){
            //System.out.println("Error : "+e.getMessage());
        }finally{
         if(conn!=null)
          conn.close();
        }
    }
    
}
