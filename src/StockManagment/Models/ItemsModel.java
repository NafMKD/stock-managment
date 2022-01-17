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
public class ItemsModel {
    public int itemid;
    public int scid;
    public String item_name;
    public String item_description;
    public int quantity;
    public String expire_date;
    public String date_created;
    public String sub_cat_name;
    public SubCategoriesModel sub_cat;

    public ItemsModel(int itemid, int scid, String item_name, String item_description, int quantity, String expire_date, String date_created) throws SQLException {
        this.itemid = itemid;
        this.scid = scid;
        this.item_name = item_name;
        this.item_description = item_description;
        this.quantity = quantity;
        this.expire_date = expire_date;
        this.date_created = date_created;
        SubCategories sub_cat_temp = new SubCategories(this.scid);
        this.sub_cat = new SubCategoriesModel(sub_cat_temp.scid, sub_cat_temp.cid, sub_cat_temp.sc_name, sub_cat_temp.sc_description, sub_cat_temp.date_created);
        this.sub_cat_name = sub_cat_temp.sc_name;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public SubCategoriesModel getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(SubCategoriesModel sub_cat) {
        this.sub_cat = sub_cat;
    }

    public String getSub_cat_name() {
        return sub_cat_name;
    }

    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }
    
    
}
