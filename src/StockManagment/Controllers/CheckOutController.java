/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Models.Customers;
import StockManagment.Models.CustomersModel;
import StockManagment.Models.Items;
import StockManagment.Models.ItemsModel;
import com.jfoenix.controls.JFXButton;
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
public class CheckOutController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton navBarLogout;
    @FXML
    private JFXButton navBarItem;
    @FXML
    private JFXButton navBarCheckOut;
    @FXML
    private JFXButton navBarProfile;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<CustomersModel> listViewTable;
    @FXML
    private TableColumn<CustomersModel, Integer> listViewColNum;
    @FXML
    private TableColumn<CustomersModel, String> listViewColName;
    @FXML
    private TableColumn<CustomersModel, Integer> listViewColQuan;
    @FXML
    private TableColumn<CustomersModel, String> listViewColDc;
    @FXML
    private JFXTextField addCheckOutItId;
    @FXML
    private JFXTextField addCheckOutItQuan;
    @FXML
    private JFXTextField addCheckOutCusName;
    @FXML
    private JFXTextField addCheckOutCusAdd;
    @FXML
    private JFXTextField addCheckOutCussPhone;
    @FXML
    private Label addCheckOutRetMsg;
    @FXML
    private TableColumn<CustomersModel, String> listViewColItName;
    @FXML
    private TableColumn<CustomersModel, String> listViewColAdd;
    @FXML
    private TableColumn<CustomersModel, String> listViewColPhone;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showCheckOuts();
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
    private void listViewSelectionChange(Event event) {
    }
    
    private void sceneChenger(String sceneAddress, ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource(sceneAddress));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addCheckoutBtn(ActionEvent event) throws SQLException {
        if(InputValidator.checkInt(addCheckOutItQuan.getText())){
            int quantity = Integer.valueOf(addCheckOutItQuan.getText());
            if(InputValidator.checkInt(addCheckOutItId.getText())){
                String name = addCheckOutCusName.getText();
                String address = addCheckOutCusAdd.getText();
                int itemId = Integer.valueOf(addCheckOutItId.getText());
                if(InputValidator.checkInt(addCheckOutCussPhone.getText())){
                    int phone = Integer.valueOf(addCheckOutCussPhone.getText());
                    if(name.isEmpty() || address.isEmpty()){
                        addCheckOutRetMsg.setText("All Fielsd Required!");
                        addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                    }else{
                        String[] st = name.split(" ");
                        if(InputValidator.checkString(st)){
                            if(InputValidator.checkFormatString(addCheckOutCussPhone.getText()," ","0912345678")){
                                if(itemVerify(itemId)){
                                    if(itemQuantityVerify(itemId,quantity)){
                                        if(st.length==2){
                                            if(quantity<1){
                                                addCheckOutRetMsg.setText("Quantity cannot be less than 1!");
                                                addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                                            }else{
                                                boolean b = addCustomer(itemId,quantity,st[0],st[1],address,phone);
                                                if(b){
                                                    showCheckOuts();
                                                    addCheckOutRetMsg.setText("Check Out Successfuly Registerd!");
                                                    addCheckOutRetMsg.setStyle("-fx-text-fill : green");
                                                }else{
                                                    addCheckOutRetMsg.setText("Somthing went wrong, please try again!");
                                                    addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                                                }
                                            }
                                        }else{
                                            addCheckOutRetMsg.setText("Name Must contain Father Name!");
                                            addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                                        }
                                    }else{
                                        addCheckOutRetMsg.setText("Insuficient Item Quantity in Stock!");
                                        addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                                    }
                                }else{
                                    addCheckOutRetMsg.setText("Item Not Found!");
                                    addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                                }
                            }else{
                                addCheckOutRetMsg.setText("Phone must be in '0912345678' Format!");
                                addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                            }
                        }else{
                            addCheckOutRetMsg.setText("Invalid Name Format!");
                            addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                        }
                    }
                }else{
                    addCheckOutRetMsg.setText("Invalid Phone Input!");
                    addCheckOutRetMsg.setStyle("-fx-text-fill : red");
                }
            }else{
                addCheckOutRetMsg.setText("Invalid Item Id Input!");
                addCheckOutRetMsg.setStyle("-fx-text-fill : red");
            }
        }else{
            addCheckOutRetMsg.setText("Invalid Quantity Input!");
            addCheckOutRetMsg.setStyle("-fx-text-fill : red");
        }
    }
    
    private boolean itemVerify(int it) throws SQLException{
        ObservableList<ItemsModel> list_it = Items.getItems(it);
        return list_it.size()==1;
    }
    
    private boolean itemQuantityVerify(int it, int quan) throws SQLException{
        ObservableList<ItemsModel> list_it = Items.getItems(it);
        return list_it.get(0).quantity >= quan;
    }
    
    private boolean addCustomer(int itemid, int quan,String fname, String lname, String address, int phone) throws SQLException{
        String conv = String.valueOf(phone);
        Customers cu = new Customers();
        boolean ret = cu.addNewCustomer(itemid, quan, fname, lname, address, conv);
        if(ret){
            Items it = new Items(itemid);
            boolean ed =it.setQuantityTriger(quan,1);
            if(it.quantity==0){
                ArrayList<String> s = new ArrayList();
                s.add(it.item_name);
                AlertMaker.alertItemOutofStcok(s);
            }
            return ed;
        }else{
            return false;
        }
    }
    
    private void showCheckOuts() throws SQLException{
        ObservableList<CustomersModel> list = Customers.getCustomers();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        listViewColQuan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        listViewColDc.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewColItName.setCellValueFactory(new PropertyValueFactory<>("itemsName"));
        listViewColAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        listViewColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        listViewTable.setItems(list);
    }
    
    
}
