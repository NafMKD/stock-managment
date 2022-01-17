/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Naf
 */
public class AlertMaker {
    
    public static void alertItemOutofStcok(ArrayList<String> name){
        Alert al = new Alert(AlertType.WARNING);
        al.setTitle("Alert");
        al.setHeaderText("Item out of Stock");
        String s ="";
        for(int i=0;i<name.size();i++){
            s+="-> "+name.get(i)+"\n";
       }
        if(name.size()>1){
            al.setContentText(
                    "Be aware that this items are out of stock!\n"
                    +s
            );
        }else{
            al.setContentText(
                "Be aware that this item is out of stock!\n"
                +s
            ); 
        }
        al.show();
    }
    
    public static void alertItemOutofStcok(ArrayList<String> name,ArrayList<Integer> in){
        Alert al = new Alert(AlertType.WARNING);
        al.setTitle("Alert");
        al.setHeaderText("Item out of Stock");
        String s ="";
        for(int i=0;i<name.size();i++){
            s+="-> "+name.get(i)+" (ID: "+in.get(i)+")\n";
        }
        if(name.size()>1){
            al.setContentText(
                    "Be aware that this items are out of stock!\n"
                    +s
            );
        }else{
            al.setContentText(
                "Be aware that this item is out of stock!\n"
                +s
            ); 
        }
        al.show();
    }
    
    
            
}
