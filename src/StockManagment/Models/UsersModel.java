/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;

import java.sql.SQLException;

/**
 *
 * @author Naf
 */
public class UsersModel {
    public int eid; 
    public String username;    
    public String password;    
    public String last_login; 
    public String last_logout; 
    public String date_created;
    public String emp_name_user;
    public EmployeesModel emp;

    public UsersModel(int eid, String username, String password, String last_login, String last_logout, String date_created) throws SQLException {
        this.eid = eid;
        this.username = username;
        this.password = password;
        this.last_login = last_login;
        this.last_logout = last_logout;
        this.date_created = date_created;
        Employees emp_temp = new Employees(this.eid);
        this.emp = new EmployeesModel(emp_temp.eid, emp_temp.rid, emp_temp.fname, emp_temp.mname, emp_temp.lname, emp_temp.email, emp_temp.sex, emp_temp.birth_date, emp_temp.date_created);
        this.emp_name_user = emp_temp.fname+" "+emp_temp.mname+" "+emp_temp.lname;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getLast_logout() {
        return last_logout;
    }

    public void setLast_logout(String last_logout) {
        this.last_logout = last_logout;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
    
    public EmployeesModel getEmp() {
        return emp;
    }

    public void setEmp(EmployeesModel emp) {
        this.emp = emp;
    }

    public String getEmp_name_user() {
        return emp_name_user;
    }

    public void setEmp_name_user(String emp_name_user) {
        this.emp_name_user = emp_name_user;
    }
    
}
