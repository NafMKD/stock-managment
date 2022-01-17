/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Naf
 */
public class RoleController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton asideBtnDashoard;
    @FXML
    private JFXButton asideBtnEmployees;
    @FXML
    private JFXButton asideBtnCategories;
    @FXML
    private JFXButton asideBtnProducts;
    @FXML
    private JFXButton asideBtnRole;
    @FXML
    private JFXButton asideBtnProfile;
    @FXML
    private JFXButton navBarLogout;
    @FXML
    private Label addNewRoleReturnLb;
    @FXML
    private JFXTextField addRoleNameTf;
    @FXML
    private JFXTextArea addRoleDescTf;
    @FXML
    private Label addRoleNameErrLb;
    @FXML
    private JFXButton addRoleBtn;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<RolesModel> listViewTable;
    @FXML
    private TableColumn<RolesModel, Integer> listViewColNum;
    @FXML
    private TableColumn<RolesModel, String> listViewColName;
    @FXML
    private TableColumn<RolesModel, String> listViewColDescreption;
    @FXML
    private TableColumn<RolesModel, String> listViewColDate;
    @FXML
    private JFXButton asideBtnSubCategories;

    @FXML
    private void buttonActionHandler(ActionEvent e) throws IOException{
        if(e.getSource()==asideBtnDashoard){
            sceneChenger("/StockManagment/Fxml/Dashboard.fxml",e);
        }else if(e.getSource()==asideBtnEmployees){
            sceneChenger("/StockManagment/Fxml/Employees.fxml", e);
        }else if(e.getSource()==asideBtnCategories){
            sceneChenger("/StockManagment/Fxml/Categories.fxml", e);
        }else if(e.getSource()==asideBtnProducts){
            sceneChenger("/StockManagment/Fxml/Products.fxml", e);
        }else if(e.getSource()==asideBtnProfile){
            sceneChenger("/StockManagment/Fxml/Profile.fxml", e);
        }else if(e.getSource()==navBarLogout){
            sceneChenger("/StockManagment/Fxml/LogIn.fxml", e);
        }else if(e.getSource()==asideBtnRole){
            sceneChenger("/StockManagment/Fxml/role.fxml", e);
        }else if(e.getSource()==asideBtnSubCategories){
            sceneChenger("/StockManagment/Fxml/SubCategories.fxml", e);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showRoles();
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }    

    private void sceneChenger(String sceneAddress, ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource(sceneAddress));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addRole(ActionEvent event) throws SQLException {
        if(addRoleNameTf.getText().isEmpty()){
            addRoleNameErrLb.setText("Role Name Cannot be Empty!");
        }else{
            String[] st = addRoleNameTf.getText().split(" ");
            if(InputValidator.checkString(st)){
                Roles role = new Roles();
                boolean cont = role.addNewRole(addRoleNameTf.getText(),addRoleDescTf.getText());
                if(cont){
                    addNewRoleReturnLb.setText("Role Successfuly Registered!");
                    addNewRoleReturnLb.setStyle("-fx-text-fill : green");
                    showRoles();
                }else{
                    addNewRoleReturnLb.setText("Something went wrong, pleas try again!");
                    addNewRoleReturnLb.setStyle("-fx-text-fill : red");
                }
            }else{
                addNewRoleReturnLb.setText("Invalid Name Format!");
                addNewRoleReturnLb.setStyle("-fx-text-fill : red");
            }            
        }
    }

    @FXML
    private void listViewSelectionChange(Event event) throws SQLException {
        showRoles();
    }
    
    private void showRoles() throws SQLException{
        ObservableList<RolesModel> list = Roles.getRoles();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("rid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("role_name"));
        listViewColDescreption.setCellValueFactory(new PropertyValueFactory<>("role_description"));
        listViewColDate.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewTable.setItems(list);
    }
    
}
