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
public class SubCategoriesModel {
    public int scid;
    public int cid;
    public String sc_name;
    public String sc_description;
    public String date_created;
    public String cat_name_sc;
    public CategoriesModel cat;

    public SubCategoriesModel(int scid, int cid, String sc_name, String sc_description, String date_created) throws SQLException {
        this.scid = scid;
        this.cid = cid;
        this.sc_name = sc_name;
        this.sc_description = sc_description;
        this.date_created = date_created;
        Categories cat_temp= new Categories(this.cid);
        this.cat = new CategoriesModel(cat_temp.cid, cat_temp.cat_name, cat_temp.cat_description, cat_temp.status, cat_temp.date_created);
        this.cat_name_sc = cat_temp.cat_name;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSc_name() {
        return sc_name;
    }

    public void setSc_name(String sc_name) {
        this.sc_name = sc_name;
    }

    public String getSc_description() {
        return sc_description;
    }

    public void setSc_description(String sc_description) {
        this.sc_description = sc_description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public CategoriesModel getCat() {
        return cat;
    }

    public void setCat(CategoriesModel cat) {
        this.cat = cat;
    }

    public String getCat_name_sc() {
        return cat_name_sc;
    }

    public void setCat_name_sc(String cat_name_sc) {
        this.cat_name_sc = cat_name_sc;
    }
    
    
}
