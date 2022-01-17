/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Main;
import StockManagment.Models.Employees;
import StockManagment.Models.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Naf
 */
public class UserProfileController implements Initializable {

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
    private Label profEmpId;
    @FXML
    private Label profEmpName;
    @FXML
    private Label profEmpEmail;
    @FXML
    private Label profEmpSex;
    @FXML
    private Label ProfEmpRol;
    @FXML
    private Label PprofEmpBD;
    @FXML
    private Label profEmpRD;
    @FXML
    private JFXPasswordField profChngOldPastf;
    @FXML
    private JFXPasswordField profChngNewPastf;
    @FXML
    private FontAwesomeIconView profChngPassBtn;
    @FXML
    private Label profChngPassRetMsg;
    @FXML
    private JFXTextField profChngUserName;
    @FXML
    private FontAwesomeIconView profChngPassBtn1;
    @FXML
    private Label profChngUserNameRetMsg;
    private int SESSION_ID = Main.session_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showUserInfromation();
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
    
    private void sceneChenger(String sceneAddress, ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource(sceneAddress));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chengePassword(ActionEvent event) throws SQLException {
        String old = profChngOldPastf.getText();
        String newp = profChngNewPastf.getText();
        if(old.isEmpty() || newp.isEmpty()){
            profChngPassRetMsg.setText("All Fields are Required!");
            profChngPassRetMsg.setStyle("-fx-text-fill : red");
        }else{
            if(newp.length()>4){
                if(oldPasCheck(this.SESSION_ID, old)){
                    if(oldPasCheck(this.SESSION_ID, newp)){
                        profChngPassRetMsg.setText("New password cannot be similar to new password!");
                        profChngPassRetMsg.setStyle("-fx-text-fill : red");
                    }else{
                        boolean b = changePasswordMet(this.SESSION_ID,newp);
                        if(b){
                            profChngPassRetMsg.setText("Password changend successfuly!");
                            profChngPassRetMsg.setStyle("-fx-text-fill : green");
                        }else{
                            profChngPassRetMsg.setText("Something went wrong, please try again!");
                            profChngPassRetMsg.setStyle("-fx-text-fill : red");
                        }
                    }
                }else{
                    profChngPassRetMsg.setText("Old Password Not Correct!");
                    profChngPassRetMsg.setStyle("-fx-text-fill : red");
                }
            }else{
                profChngPassRetMsg.setText("New Password must be atleast 5 char.!");
                profChngPassRetMsg.setStyle("-fx-text-fill : red");
            }
        }
        
    }

    @FXML
    private void chengeUserName(ActionEvent event) throws SQLException {
        String chang_name = profChngUserName.getText();
        if(chang_name.isEmpty()){
            profChngUserNameRetMsg.setText("All Fields are Required!");
            profChngUserNameRetMsg.setStyle("-fx-text-fill : red");
        }else{
            boolean b= changeUserNameMet(this.SESSION_ID,chang_name);
            if(b){
                profChngUserNameRetMsg.setText("Username changend successfuly!");
                profChngUserNameRetMsg.setStyle("-fx-text-fill : green");
            }else{
                profChngUserNameRetMsg.setText("Something went wrong, please try again!");
                profChngUserNameRetMsg.setStyle("-fx-text-fill : red");
            }
        }
    }

    private void showUserInfromation() throws SQLException{
        Employees emp_info = new Employees(this.SESSION_ID);
        String emp_full = emp_info.fname+" "+emp_info.mname+" "+emp_info.lname;
        profEmpId.setText(String.valueOf(emp_info.eid));
        profEmpName.setText(emp_full);
        profEmpEmail.setText(emp_info.email);
        profEmpSex.setText(emp_info.sex);
        ProfEmpRol.setText(emp_info.role.role_name);
        PprofEmpBD.setText(emp_info.birth_date);
        profEmpRD.setText(emp_info.date_created);
    }
    
    private boolean changeUserNameMet(int eid, String username) throws SQLException{
        Users usr_info = new Users(eid);
        boolean x = usr_info.updateThisUser(username, usr_info.password);
        return x;
    }
    
    private boolean oldPasCheck(int eid, String password) throws SQLException{
       Users usr_info = new Users(eid); 
       boolean x = usr_info.password.equals(password);
       return x;
    }
    
    private boolean changePasswordMet(int eid, String password) throws SQLException{
        Users usr_info = new Users(eid);
        boolean x = usr_info.updateThisUser(usr_info.username, password);
        return x;
    }
}
