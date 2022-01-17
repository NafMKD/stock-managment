/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Models;


/**
 *
 * @author Naf
 */
public class RolesModel {
    public int rid;
    public String role_name;
    public String role_description;
    public String date_created;

   
    public RolesModel(int rid, String role_name, String role_description, String date_created) {
        this.rid = rid;
        this.role_name = role_name;
        this.role_description = role_description;
        this.date_created = date_created;
        
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
   
    
}
