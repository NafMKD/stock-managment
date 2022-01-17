/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Models.CategoriesModel;
import StockManagment.Models.Items;
import StockManagment.Models.ItemsModel;
import StockManagment.Models.SubCategories;
import StockManagment.Models.SubCategoriesModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Naf
 */
public class UserDashboardController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton navBarLogout;
    @FXML
    private JFXButton navBarCheckOut;
    @FXML
    private JFXButton navBarProfile;
    @FXML
    private JFXButton addItemBtn;
    @FXML
    private ComboBox<String> addItemsSubCat;
    @FXML
    private JFXTextField addItemsName;
    @FXML
    private JFXTextArea addItemsDesc;
    @FXML
    private JFXTextField addItemsQuan;
    @FXML
    private JFXTextField addItemsExp;
    @FXML
    private Label addItemRetMsg;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<ItemsModel> listViewTable;
    @FXML
    private TableColumn<ItemsModel, Integer> listViewColNum;
    @FXML
    private TableColumn<ItemsModel, String> listViewColName;
    @FXML
    private TableColumn<ItemsModel, String> listViewColDesc;
    @FXML
    private TableColumn<ItemsModel, Integer> listViewColQuan;
    @FXML
    private TableColumn<ItemsModel, String> listViewColEd;
    @FXML
    private TableColumn<ItemsModel, String> listViewColDc;
    @FXML
    private JFXButton navBarItem;
    @FXML
    private Label updateItemRetMsg;
    @FXML
    private JFXTextField updateItemId;
    @FXML
    private ComboBox<String> updateOperation;
    @FXML
    private JFXTextField updateQuantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<SubCategoriesModel> sub_cat = SubCategories.gatSubCategories();
            ArrayList<String> arr = new ArrayList();
            for(int i=0;i<sub_cat.size();i++){
                arr.add(sub_cat.get(i).sc_name);
            }
            addItemsSubCat.getItems().addAll(arr);
            String[] str = {"Replace", "Add"};
            updateOperation.getItems().addAll(str);
            showItems();
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }    

    @FXML
    private void buttonActionHandler(ActionEvent event) throws IOException {
        if(event.getSource()==navBarLogout){
            sceneChenger("/StockManagment/Fxml/LogIn.fxml", event);
        }else if(event.getSource()==navBarCheckOut){
            sceneChenger("/StockManagment/Fxml/checkOut.fxml", event);
        }else if(event.getSource()==navBarProfile){
            sceneChenger("/StockManagment/Fxml/userProfile.fxml", event);
        }else if(event.getSource()==navBarItem){
            sceneChenger("/StockManagment/Fxml/userDashboard.fxml", event);
        }
    }

    @FXML
    private void addItem(ActionEvent event) throws SQLException {
       if(InputValidator.checkInt(addItemsQuan.getText())){
           String sub_cat = String.valueOf(addItemsSubCat.getValue()) ;
           String it_name = addItemsName.getText();
           String it_desc = addItemsDesc.getText();
           int it_quan = Integer.valueOf(addItemsQuan.getText());
           String  exp_d = addItemsExp.getText();
           if(addItemsQuan.getText().isEmpty() || sub_cat.equalsIgnoreCase("null") || it_name.isEmpty() || it_desc.isEmpty()){
                addItemRetMsg.setText("All fildes are required Exept Expire date!");
                addItemRetMsg.setStyle("-fx-text-fill : red");
            }else{
               String[] st = it_name.split(" ");
               if(InputValidator.checkString(st)){
                   if(exp_d.isEmpty()){
                        if(it_quan<1){
                            addItemRetMsg.setText("Quantity cannot be less than 1");
                            addItemRetMsg.setStyle("-fx-text-fill : red");
                        }else{
                            boolean b = addItemData(subCategoryIdExtracter(sub_cat),it_name,it_desc,it_quan,exp_d);
                            if(b){
                                showItems();
                                addItemRetMsg.setText("Item Successfuly Registerd!");
                                addItemRetMsg.setStyle("-fx-text-fill : green");
                            }else{
                                addItemRetMsg.setText("Somthing went wrong, please try again!");
                                addItemRetMsg.setStyle("-fx-text-fill : red");
                            }
                        }
                   }else{
                       if(InputValidator.checkFormatString("yyyy-mm-dd", "-", exp_d)){
                           if(InputValidator.checkContainsInt(exp_d, "-", 3)){
                               if(InputValidator.checkMonthYearDay(exp_d,2040,2021)){
                                   if(it_quan<1){
                                        addItemRetMsg.setText("Quantity cannot be less than 1");
                                        addItemRetMsg.setStyle("-fx-text-fill : red");
                                    }else{
                                        boolean b = addItemData(subCategoryIdExtracter(sub_cat),it_name,it_desc,it_quan,exp_d);
                                        if(b){
                                            showItems();
                                            addItemRetMsg.setText("Item Successfuly Registerd!");
                                            addItemRetMsg.setStyle("-fx-text-fill : green");
                                        }else{
                                            addItemRetMsg.setText("Somthing went wrong, please try again!");
                                            addItemRetMsg.setStyle("-fx-text-fill : red");
                                        }
                                    }
                               }else{
                                   addItemRetMsg.setText("Out of Index Input (Birth Date)");
                                   addItemRetMsg.setStyle("-fx-text-fill : red");
                               }
                           }else{
                               addItemRetMsg.setText("Birth Day must be integer");
                               addItemRetMsg.setStyle("-fx-text-fill : red");
                           }
                       }else{
                           addItemRetMsg.setText("Birth Day must be in the form of (yyyy-mm-dd)");
                           addItemRetMsg.setStyle("-fx-text-fill : red");
                       }
                   }
               }else{
                   addItemRetMsg.setText("Invalid Name Format!");
                   addItemRetMsg.setStyle("-fx-text-fill : red");
               }
           }
       }else{
           addItemRetMsg.setText("Invalid Quantity Input!");
           addItemRetMsg.setStyle("-fx-text-fill : red");
       }
    }

    @FXML
    private void listViewSelectionChange(Event event) {
    }
    
    private int subCategoryIdExtracter(String s) throws SQLException{
        ObservableList<SubCategoriesModel> sub_cat = SubCategories.gatSubCategories();
        int ret =-1;
        for(int i=0;i<sub_cat.size();i++){
            if(sub_cat.get(i).sc_name.equalsIgnoreCase(s)){
                ret = sub_cat.get(i).scid;
                break;
            }
        }
        return ret;
    }

    private boolean addItemData(int scid,String item_name,String item_description,int quantity, String expire_date) throws SQLException{
        Items it = new Items();
        boolean x = it.addNewItem(scid, item_name, item_description, quantity, expire_date);
        return x;
    }
    
    private void showItems() throws SQLException{
        ObservableList<ItemsModel> list = Items.getItems();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        listViewColDesc.setCellValueFactory(new PropertyValueFactory<>("item_description"));
        listViewColQuan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        listViewColEd.setCellValueFactory(new PropertyValueFactory<>("expire_date"));
        listViewColDc.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewTable.setItems(list);
    }
    
    private void sceneChenger(String sceneAddress, ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource(sceneAddress));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void updateItem(ActionEvent event) throws SQLException {
        String opration_sel = String.valueOf(updateOperation.getValue());
        if(updateItemId.getText().isEmpty() || updateQuantity.getText().isEmpty() || opration_sel.equalsIgnoreCase("null")){
            updateItemRetMsg.setText("All Fildes are Required!");
            updateItemRetMsg.setStyle("-fx-text-fill : red");
        }else{
            if(InputValidator.checkInt(updateItemId.getText())){
                if(InputValidator.checkInt(updateQuantity.getText())){
                    int itm_id = Integer.valueOf(updateItemId.getText());
                    int quantity = Integer.valueOf(updateQuantity.getText());
                    if(quantity<1){
                        updateItemRetMsg.setText("Quantity cannot be less than 1");
                        updateItemRetMsg.setStyle("-fx-text-fill : red");
                    }else{
                        if(itemVerify(itm_id)){
                            boolean b = updateItemQuantity(itm_id,quantity,opration_sel);
                            if(b){
                                showItems();
                                updateItemRetMsg.setText("Item Updated!");
                                updateItemRetMsg.setStyle("-fx-text-fill : green");
                            }else{
                                updateItemRetMsg.setText("Somethisng went wrong, please try again!");
                                updateItemRetMsg.setStyle("-fx-text-fill : red");
                            }
                        }else{
                            updateItemRetMsg.setText("Item Not Found!");
                            updateItemRetMsg.setStyle("-fx-text-fill : red");
                        }
                    }
                }else{
                    updateItemRetMsg.setText("Invalid Quantity Input!");
                    updateItemRetMsg.setStyle("-fx-text-fill : red");
                }
            }else{
                updateItemRetMsg.setText("Invalid Item Id Input!");
                updateItemRetMsg.setStyle("-fx-text-fill : red");
            }
        }
        
    }
    
    
    private boolean itemVerify(int it) throws SQLException{
        ObservableList<ItemsModel> list_it = Items.getItems(it);
        return list_it.size()==1;
    }
    
    private boolean updateItemQuantity(int id, int quan, String op) throws SQLException{
        Items it =new Items(id);
        boolean rt;
        if(op.equalsIgnoreCase("Replace")){
            rt = it.setQuantityTriger(quan, 0);
        }else if(op.equalsIgnoreCase("Add")){
            rt = it.setQuantityTriger(quan, 2);
        }else{
            rt = false;
        }
        return rt;
    }
}
