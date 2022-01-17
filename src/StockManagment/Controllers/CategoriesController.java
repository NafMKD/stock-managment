/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Main;
import StockManagment.Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Naf
 */
public class CategoriesController implements Initializable {

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
    private JFXButton asideBtnProfile;
    @FXML
    private JFXButton navBarLogout;
    @FXML
    private JFXButton asideBtnRole;
    @FXML
    private JFXTextField addCategoryNameTf;
    @FXML
    private JFXTextArea addCategoryDescTf;
    @FXML
    private JFXButton addCategoryBtn;
    @FXML
    private Label addNewCategoryReturnLb;
    @FXML
    private Label addCategoryNameErrLb;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<CategoriesModel> listViewTable;
    @FXML
    private TableColumn<CategoriesModel, Integer> listViewColNum;
    @FXML
    private TableColumn<CategoriesModel, String> listViewColName;
    @FXML
    private TableColumn<CategoriesModel, String> listViewColDesc;
    @FXML
    private TableColumn<CategoriesModel, Integer> listViewColstst;
    @FXML
    private TableColumn<CategoriesModel, String> listViewColdatecr;
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
            showCategories();
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
    private void addCategory(ActionEvent event) throws IOException, SQLException {
        if(addCategoryNameTf.getText().isEmpty()){
            addCategoryNameErrLb.setText("Category Name Cannot be Empty!");
            addNewCategoryReturnLb.setStyle("-fx-text-fill : red");
        }else{
            String[] st = addCategoryNameTf.getText().split(" ");
            if(InputValidator.checkString(st)){
                Categories cat = new Categories();
                boolean cont = cat.addNewCategory(addCategoryNameTf.getText(),addCategoryDescTf.getText());
                if(cont){
                    addNewCategoryReturnLb.setText("Category Successfuly Registered!");
                    addNewCategoryReturnLb.setStyle("-fx-text-fill : green");
                    showCategories();
                }else{
                    addNewCategoryReturnLb.setText("Something went wrong, pleas try again!");
                    addNewCategoryReturnLb.setStyle("-fx-text-fill : red");
                }
            }else{
                addNewCategoryReturnLb.setText("Invalid Name Format!");
                addNewCategoryReturnLb.setStyle("-fx-text-fill : red");
            }
        }
    }

    @FXML
    private void listViewSelectionChange(Event event) {
        //showCategories();
    }
    
    private void showCategories() throws SQLException{
        ObservableList<CategoriesModel> list = Categories.getCategories();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("cid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("cat_name"));
        listViewColDesc.setCellValueFactory(new PropertyValueFactory<>("cat_description"));
        listViewColstst.setCellValueFactory(new PropertyValueFactory<>("stat_name"));
        listViewColdatecr.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewTable.setItems(list);
    }
    
    
    
}
