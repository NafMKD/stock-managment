/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagment.Controllers;

import StockManagment.Main;
import StockManagment.Models.*;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
/**
 * FXML Controller class
 *
 * @author Naf
 */
public class LogInController implements Initializable {
 
    @FXML
    private Button loginBtnSignIn;
    @FXML
    private TextField loginInputUsername;
    @FXML
    private PasswordField loginInputPassword;
    @FXML
    private Label logniErrorLabel; 
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void logInHandler(ActionEvent e) throws IOException, SQLException{
        loginBtnSignIn.setText("Signing you in...");
        if(loginInputUsername.getText().isEmpty()==false && loginInputPassword.getText().isEmpty()==false){
            int check=-1;
            try {
                check = loginUserVerify(loginInputUsername.getText(),loginInputPassword.getText());
            } catch (SQLException ex) {
                System.out.println("Error : "+ex.getMessage());
            }
            if(check==1){
                int rids=-1;
                try {
                    rids = loginAccessVerify(loginInputUsername.getText(),loginInputPassword.getText());
                } catch (SQLException ex) {
                    System.out.println("Error : "+ex.getMessage());
                }
                if(rids == 1){
                    try {
                        root = FXMLLoader.load(getClass().getResource("/StockManagment/Fxml/Dashboard.fxml"));
                        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                        scene = new Scene(root, 1000, 600);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println("Error : "+ex.getMessage());
                    }
                }else if(rids == 2){
                    try {
                        root = FXMLLoader.load(getClass().getResource("/StockManagment/Fxml/userDashboard.fxml"));
                        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                        scene = new Scene(root, 1000, 600);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println("Error : "+ex.getMessage());
                    }
                }else if(rids == -1){
                    logniErrorLabel.setText("Something went wrong, please try again!");
                }else if(rids == -89){
                    logniErrorLabel.setText("Database Connection Error!");
                }
                
            }else{
                logniErrorLabel.setText("Incorect Username or Password!");
                loginBtnSignIn.setText("Sign in");
            }
        }else{
            logniErrorLabel.setText("All fields are required!");
            loginBtnSignIn.setText("Sign in");
        }
    }
    
    
    private int loginUserVerify(String username, String password) throws SQLException{
        try{
            ObservableList<UsersModel> available_user = Users.getUsers(username,password);
            return available_user.size();
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return -89;
        }
    }
    
    private int loginAccessVerify(String username, String password) throws SQLException{
        try{
            ObservableList<UsersModel> available_user = Users.getUsers(username,password);
            int b = loginUserVerify(username, password);
            if(b==1){
                int x = available_user.get(0).eid;
                Main.session_id = x;
                Main.first_session = true;
                Employees vailable_emp = new Employees(x);
                int rid = vailable_emp.rid;
                return rid;
            }else{
                return -1;
            }
        }catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return -89;
        }
    }
    
    
}
