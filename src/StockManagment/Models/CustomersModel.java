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
public class CustomersModel {
    public int cusid;
    public int itemid;	
    public int quantity;
    public String fname;
    public String lname;
    public String FullName;
    public String address;
    public String phone;
    public String date_created;
    public String itemsName;
    public ItemsModel items; 
    
    public CustomersModel(int cusid, int itemid, int quantity, String fname, String lname, String address, String phone, String date_created) throws SQLException {
        this.cusid = cusid;
        this.itemid = itemid;
        this.quantity = quantity;
        this.fname = fname;
        this.lname = lname;
        this.FullName = fname+" "+lname;
        this.address = address;
        this.phone = phone;
        this.date_created = date_created;
        Items itemsM = new Items(this.itemid);
        this.items = new ItemsModel(itemsM.itemid, itemsM.scid, itemsM.item_name, itemsM.item_description,itemsM.quantity,itemsM.expire_date,itemsM.date_created); 
        this.itemsName = this.items.item_name;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public ItemsModel getItems() {
        return items;
    }

    public void setItems(ItemsModel items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    

    
}
