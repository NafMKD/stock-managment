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
public class EmployeesModel {
    public int eid;
    public int rid;
    public String fname;
    public String mname;
    public String lname; 
    public String email;
    public String FullName;
    public String sex;
    public String birth_date;
    public String date_created;
    public String role_name_emp;
    public RolesModel role;

    public EmployeesModel(int eid, int rid, String fname, String mname, String lname, String email, String sex, String birth_date, String date_created) throws SQLException {
        this.eid = eid;
        this.rid = rid;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.FullName = fname+" "+mname+" "+lname;
        this.email = email;
        this.sex = sex;
        this.birth_date = birth_date;
        this.date_created = date_created;
        Roles role_temp = new Roles(this.rid);
        this.role = new RolesModel(role_temp.rid, role_temp.role_name, role_temp.role_description, role_temp.date_created);
        this.role_name_emp = role_temp.role_name;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public RolesModel getRole() {
        return role;
    }

    public void setRole(RolesModel role) {
        this.role = role;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getRole_name_emp() {
        return role_name_emp;
    }

    public void setRole_name_emp(String role_name_emp) {
        this.role_name_emp = role_name_emp;
    }
    
    
}
