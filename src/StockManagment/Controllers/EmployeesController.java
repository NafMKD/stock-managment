/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Models.Employees;
import StockManagment.Models.EmployeesModel;
import StockManagment.Models.Roles;
import StockManagment.Models.RolesModel;
import StockManagment.Models.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
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
public class EmployeesController implements Initializable {

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
    private JFXTextField addEmployeeFullName;
    @FXML
    private JFXTextField addEmployeeEmail;
    @FXML
    private JFXTextField addEmployeeBd;
    @FXML
    private JFXButton addEmployeeBtn;
    @FXML
    private Tab llistViewBtnView;
    @FXML
    private TableView<EmployeesModel> listViewTable;
    @FXML
    private TableColumn<EmployeesModel, Integer> listViewColNum;
    @FXML
    private ComboBox<String> addEmployeeRoleSelect;
    @FXML
    private ComboBox<String> addEmployeeSex;
    @FXML
    private Label addEmployeRetMsg;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColName;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColEmail;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColGen;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColBd;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColRd;
    @FXML
    private TableColumn<EmployeesModel, String> listViewColRole;
    @FXML
    private Label addEmployeUserName;
    @FXML
    private Label addEmployeePass;
    @FXML
    private Label addEmployeRetSucMsg;
    @FXML
    private JFXTabPane addEmployeeTabPane;
    @FXML
    private JFXButton asideBtnSubCategories;
    @FXML
    private Tab selectionTabUsInfo;
    @FXML
    private JFXButton addEmployeUserInfoRelod;
    

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
            ObservableList<RolesModel> roles = Roles.getRoles();
            ArrayList<String> arr = new ArrayList();
            for(int i=0;i<roles.size();i++){
                arr.add(roles.get(i).role_name);
            }
            addEmployeeRoleSelect.getItems().addAll(arr);
            String[] gen = {"M","F"};
            addEmployeeSex.getItems().addAll(gen);
            showEmployee();
            selectionTabUsInfo.setDisable(true);
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
    private void addEmployee(ActionEvent event) throws SQLException {
        String[] Name = addEmployeeFullName.getText().split(" ");
        String email = addEmployeeEmail.getText();
        String birth_day = addEmployeeBd.getText();
        String gender = String.valueOf(addEmployeeSex.getValue());
        String role = String.valueOf(addEmployeeRoleSelect.getValue());
        if(addEmployeeFullName.getText().isEmpty() || email.isEmpty() || birth_day.isEmpty()){
            addEmployeRetMsg.setText("All Fields Are Required!");
            addEmployeRetMsg.setStyle("-fx-text-fill : red");
        }else{
            if(gender.equalsIgnoreCase("null") || role.equalsIgnoreCase("null")){
                addEmployeRetMsg.setText("All Fields Are Required!");
                addEmployeRetMsg.setStyle("-fx-text-fill : red");
            }else{
                if(InputValidator.checkFormatString("yyyy-mm-dd", "-", birth_day)){
                    if(InputValidator.checkContainsInt(birth_day, "-", 3)){
                        if(InputValidator.checkMonthYearDay(birth_day,2020,1900)){
                            if(Name.length==3){
                                if(InputValidator.checkString(Name)){
                                    int role_id = roleIdExtractor(role);
                                    Users u = addEmployeeData(role_id, Name[0], Name[1], Name[2], email, gender, birth_day);
                                    addEmployeUserName.setText(u.username);
                                    addEmployeePass.setText(u.password);
                                    addEmployeRetSucMsg.setText("Employee Successfuly Registered!");
                                    addEmployeRetMsg.setStyle("-fx-text-fill : green");
                                    addEmployeeTabPane.getSelectionModel().select(1);
                                    selectionTabUsInfo.setDisable(false);
                                    showEmployee();
                                }else{
                                    addEmployeRetMsg.setText("Invalid Name Fomat");
                                    addEmployeRetMsg.setStyle("-fx-text-fill : red");
                                }
                                
                            }else{
                                addEmployeRetMsg.setText("Full Name Must contain Father & G.Father Name ");
                                addEmployeRetMsg.setStyle("-fx-text-fill : red");
                            }
                        }else{
                            addEmployeRetMsg.setText("Out of Index Input (Birth Date)");
                            addEmployeRetMsg.setStyle("-fx-text-fill : red");
                        }
                    }else{
                        addEmployeRetMsg.setText("Birth Day must be integer");
                        addEmployeRetMsg.setStyle("-fx-text-fill : red");
                    }
                }else{
                    addEmployeRetMsg.setText("Birth Day must be in the form of (yyyy-mm-dd)");
                    addEmployeRetMsg.setStyle("-fx-text-fill : red");
                }
            }
        }
    }
    
    private int roleIdExtractor(String s) throws SQLException{
        ObservableList<RolesModel> roles = Roles.getRoles();
        int roleid = -1;
        for(int i=0;i<roles.size();i++){
            if(roles.get(i).role_name.equalsIgnoreCase(s)){
                roleid = roles.get(i).rid;
                break;
            }
        }
        return roleid;
    }

    @FXML
    private void listViewSelectionChange(Event event) {
    }
    
    private void showEmployee() throws SQLException{
        ObservableList<EmployeesModel> list = Employees.getEmployees();
        listViewColNum.setCellValueFactory(new PropertyValueFactory<>("eid"));
        listViewColName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        listViewColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        listViewColBd.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        listViewColRd.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        listViewColGen.setCellValueFactory(new PropertyValueFactory<>("sex"));
        listViewColRole.setCellValueFactory(new PropertyValueFactory<>("role_name_emp"));
        listViewTable.setItems(list);
    }
    
    private Users addEmployeeData(int rid,String fname,String mname,String lname,String emai,String sex,String birth_date) throws SQLException{
        Employees emp = new Employees();
        Random rand_pass = new Random();
        int new_pass = Math.abs(rand_pass.nextInt(99));
        int new_pass2 = Math.abs(rand_pass.nextInt(99));
        emp.addNewEmploye(rid, fname, mname, lname, emai, sex, birth_date);
        String username = fname+"."+mname+String.valueOf(new_pass);
        String password = fname.charAt(0)+String.valueOf(new_pass)+mname.charAt(0)+String.valueOf(new_pass2)+lname.charAt(0);
        Users us = new Users();
        boolean c =us.addNewUser(emp.eid,username, password);
        return us;
    }

    @FXML
    private void reloadPage(ActionEvent event) {
        addEmployeeTabPane.getSelectionModel().select(0);
        selectionTabUsInfo.setDisable(true);
    }
}
