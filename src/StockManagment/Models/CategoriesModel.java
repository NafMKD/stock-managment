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
public class CategoriesModel {
    public int cid;	
    public String cat_name;
    public String cat_description;
    public int status;
    public String stat_name;
    public String date_created;

    public CategoriesModel(int cid, String cat_name, String cat_description, int status, String date_created) {
        this.cid = cid;
        this.cat_name = cat_name;
        this.cat_description = cat_description;
        this.status = status;
        this.date_created = date_created;
        if(this.status==1){
            this.stat_name = "Active";
        }else{
            this.stat_name = "Deactive";
        }
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }
    
    
}
