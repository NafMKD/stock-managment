/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Models.Categories;
import StockManagment.Models.CategoriesModel;
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
public class SubCategoriesController implements Initializable {

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
    private JFXButton asideBtnSubCategories;
    @FXML
    private JFXButton asideBtnProducts;
    @FXML
    private JFXButton asideBtnRole;
    @FXML
    private JFXButton asideBtnProfile;
    @FXML
    private JFXButton navBarLogout;
    @FXML
    private Label addNewSubCategoryReturnLb;
    @FXML
    private JFXTextField addSubCategoryNameTf1;
    @FXML
    private JFXTextArea addSubCategoryDescTf;
    @FXML
    private Label addSubCategoryNameErrLb;
    @FXML
    private JFXButton addSubCategoryBtn;
    @FXML
    private ComboBox<String> addSubCategorySelectCategory;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<SubCategoriesModel> listViewTable;
    @FXML
    private TableColumn<SubCategoriesModel, Integer> listViewColNum;
    @FXML
    private TableColumn<SubCategoriesModel, String> listViewColName;
    @FXML
    private TableColumn<SubCategoriesModel, String> listViewColDesc;
    @FXML
    private TableColumn<SubCategoriesModel, String> listViewColCat;
    @FXML
    private TableColumn<SubCategoriesModel, String> listViewColdatecr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList<CategoriesModel> cat_list = Categories.getCategories();
            ArrayList<String> arr = new ArrayList();
            for(int i=0;i<cat_list.size();i++){
                arr.add(cat_list.get(i).cat_name);
            }
            addSubCategorySelectCategory.getItems().addAll(arr);
            showSubCategories();
        } catch (SQLException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }    

    @FXML
    private void buttonActionHandler(ActionEvent e) throws IOException {
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

    @FXML
    private void addSubCategory(ActionEvent event) throws SQLException {
        String cats = String.valueOf(addSubCategorySelectCategory.getValue());
        if(addSubCategoryNameTf1.getText().isEmpty() || cats.equalsIgnoreCase("null")){
            addNewSubCategoryReturnLb.setText("All Fields Required Except Description!");
            addNewSubCategoryReturnLb.setStyle("-fx-text-fill : red");
        }else{
            String[] st = addSubCategoryNameTf1.getText().split(" ");
            if(InputValidator.checkString(st)){
                boolean cont = addSubCat(categoryIdExtracter(cats),addSubCategoryNameTf1.getText(),addSubCategoryDescTf.getText());
                if(cont){
                    addNewSubCategoryReturnLb.setText("SubCategory Successfuly Registered!");
                    addNewSubCategoryReturnLb.setStyle("-fx-text-fill : green");
                    showSubCategories();
                }else{
                    addNewSubCategoryReturnLb.setText("Something went wrong, pleas try again!");
                    addNewSubCategoryReturnLb.setStyle("-fx-text-fill : red");
                }
            }else{
                addNewSubCategoryReturnLb.setText("Invalid Name Format!");
                addNewSubCategoryReturnLb.setStyle("-fx-text-fill : red");
            }
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
    
    private int categoryIdExtracter(String s) throws SQLException{
        ObservableList<CategoriesModel> cat = Categories.getCategories();
        int ret =-1;
        for(int i=0;i<cat.size();i++){
            if(cat.get(i).cat_name.equalsIgnoreCase(s)){
                ret = cat.get(i).cid;
                break;
            }
        }
        return ret;
    }
    private boolean addSubCat(int cid,String sc_name, String sc_description) throws SQLException{
        SubCategories sc = new SubCategories();
        boolean b = sc.addNewCategory(cid, sc_name, sc_description);
        return b;
    }
    
    private void showSubCategories() throws SQLException{
        ObservableList<SubCategoriesModel> list = SubCategories.gatSubCategories();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("scid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("sc_name"));
        listViewColDesc.setCellValueFactory(new PropertyValueFactory<>("sc_description"));
        listViewColCat.setCellValueFactory(new PropertyValueFactory<>("cat_name_sc"));
        listViewColdatecr.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewTable.setItems(list);
    }
}
