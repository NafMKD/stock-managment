/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Main;
import StockManagment.Models.*;
import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Naf
 */
public class DashboardController implements Initializable {

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton asideBtnRole;
    @FXML
    private TableView<CustomersModel> tableViewDay;
    @FXML
    private TableColumn<CustomersModel, Integer> dashDayNum;
    @FXML
    private TableColumn<CustomersModel, String> dashDayItName;
    @FXML
    private TableColumn<CustomersModel, String> dashDayCusName;
    @FXML
    private TableColumn<CustomersModel, Integer> dashDayQuan;
    @FXML
    private TableColumn<CustomersModel, String> dashDayAddr;
    @FXML
    private TableColumn<CustomersModel, String> dashDayPhone;
    @FXML
    private TableColumn<CustomersModel, String> dashDayDate;
    @FXML
    private TableView<CustomersModel> tableViewMonth;
    @FXML
    private TableColumn<CustomersModel, Integer> dashMonthNum;
    @FXML
    private TableColumn<CustomersModel, String> dashMonthItName;
    @FXML
    private TableColumn<CustomersModel, String> dashMonthCusName;
    @FXML
    private TableColumn<CustomersModel, Integer> dashMonthQuan;
    @FXML
    private TableColumn<CustomersModel, String> dashMonthAddr;
    @FXML
    private TableColumn<CustomersModel, String> dashMonthPhone;
    @FXML
    private TableColumn<CustomersModel, String> dashMonthDate;
    @FXML
    private TableView<CustomersModel> tableViewYear;
    @FXML
    private TableColumn<CustomersModel, Integer> dashYearNum;
    @FXML
    private TableColumn<CustomersModel, String> dashYearItName;
    @FXML
    private TableColumn<CustomersModel, String> dashYearCusName;
    @FXML
    private TableColumn<CustomersModel, Integer> dashYearQuan;
    @FXML
    private TableColumn<CustomersModel, String> dashYearAddr;
    @FXML
    private TableColumn<CustomersModel, String> dashYearPhone;
    @FXML
    private TableColumn<CustomersModel, String> dashYearDate;
    @FXML
    private Label dashCountEmp;
    @FXML
    private Label dashCountCat;
    @FXML
    private Label dashCountSubCat;
    @FXML
    private Label dashCountItem;
    @FXML
    private Label dashCountRole;
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
            ObservableList<CategoriesModel> count_cat = Categories.getCategories();
            dashCountCat.setText(String.valueOf(count_cat.size()));
            ObservableList<EmployeesModel> count_emp = Employees.getEmployees();
            dashCountEmp.setText(String.valueOf(count_emp.size()));
            ObservableList<ItemsModel> count_itm = Items.getItems();
            dashCountItem.setText(String.valueOf(count_itm.size()));
            ObservableList<RolesModel> count_rol = Roles.getRoles();
            dashCountRole.setText(String.valueOf(count_rol.size()));
            ObservableList<SubCategoriesModel> count_sc = SubCategories.gatSubCategories();
            dashCountSubCat.setText(String.valueOf(count_sc.size()));
            showDayReport();
            showMonthReport();
            showYearReport();
            if(Main.first_session){
                Main.first_session = false;
                ObservableList<ItemsModel> ch= Items.getItems("");
                if(ch.size()>0){
                    ArrayList<String> arr = new ArrayList();
                    ArrayList<Integer> arrInt = new ArrayList();
                    for(int i=0;i<ch.size();i++){
                    arr.add(ch.get(i).item_name);
                    arrInt.add(ch.get(i).itemid);
                    }
                    AlertMaker.alertItemOutofStcok(arr,arrInt);
                }
            }
            
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
    
    private void showDayReport() throws SQLException{
        ObservableList<CustomersModel> list = Customers.getCustomers("day");
        dashDayNum.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        dashDayItName.setCellValueFactory(new PropertyValueFactory<>("itemsName"));
        dashDayCusName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        dashDayQuan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dashDayAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        dashDayPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dashDayDate.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        tableViewDay.setItems(list);
    }

    private void showMonthReport() throws SQLException{
        ObservableList<CustomersModel> list = Customers.getCustomers("month");
        dashMonthNum.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        dashMonthItName.setCellValueFactory(new PropertyValueFactory<>("itemsName"));
        dashMonthCusName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        dashMonthQuan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dashMonthAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        dashMonthPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dashMonthDate.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        tableViewMonth.setItems(list);
    }

    private void showYearReport() throws SQLException{
        ObservableList<CustomersModel> list = Customers.getCustomers("year");
        dashYearNum.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        dashYearItName.setCellValueFactory(new PropertyValueFactory<>("itemsName"));
        dashYearCusName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        dashYearQuan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dashYearAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        dashYearPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dashYearDate.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        tableViewYear.setItems(list);
    }
}
