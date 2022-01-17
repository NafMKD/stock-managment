package StockManagment;

import StockManagment.Models.Employees;
import StockManagment.Models.Users;
import StockManagment.Models.UsersModel;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Main extends Application{
    public static int session_id;
    public static boolean first_session;
    
    @Override
    public void start(Stage stage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("Fxml/LogIn.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        Image icon = new Image("img/sLogo.png");
        stage.getIcons().add(icon);
        stage.setTitle("Stock Managment System");
        stage.setScene(scene);
        stage.show();*/
        AnchorPane anch = new AnchorPane();
        anch.setPrefHeight(600);
        anch.setPrefWidth(1000);
        anch.setStyle("-fx-background-color:#e9ecef;");
        
        //ch1
        VBox vb1 = new VBox();
        vb1.setLayoutX(320);
        vb1.setLayoutY(150);
        vb1.setPrefHeight(300);
        vb1.setPrefWidth(360);
        vb1.setStyle("-fx-background-color:#fff;");
        anch.getChildren().add(vb1);
        
        //ch11
        Label l1 = new Label();
        l1.setText("sign in to start your session");
        l1.setStyle("-fx-text-alignment:center;-fx-text-fill:#666;-fx-font-size:17;");
        vb1.setMargin(l1, new Insets(20,0,10,70));
        vb1.getChildren().add(l1);
        
        
        //ch12
        Label l2=new Label();
        l2.setAlignment(Pos.BASELINE_CENTER);
        l2.setPrefHeight(33);
        l2.setPrefWidth(360);
        l2.setTextFill(Color.RED);
        l2.setStyle("-fx-font-size:18;");
        vb1.setMargin(l2, new Insets(0,0,20,0));
        vb1.getChildren().add(l2);
        
        //ch13
        HBox hb1=new HBox();
        hb1.setPrefHeight(40);
        hb1.setPrefWidth(360);
        vb1.getChildren().add(hb1);
         
        //ch131
        TextField tf1 = new TextField();
        tf1.setPrefHeight(40);
        tf1.setPrefWidth(282);
        tf1.setPromptText("Username...");
        hb1.setMargin(tf1,new Insets(0,0,0,20));
        hb1.getChildren().add(tf1);
        vb1.setMargin(hb1,new Insets(0,0,10,0)); 
        
        //ch132
        FontAwesomeIconView f1 = new FontAwesomeIconView();
        f1.setGlyphName("USER");
        f1.setOpacity(0.39);
        f1.setStyle("-fx-font-size:38;-fx-text-fill:#fff;");
        hb1.setMargin(f1, new Insets(0,0,0,10));
        hb1.getChildren().add(f1);
        
        //ch14
        HBox hb2 = new HBox();
        hb2.setPrefHeight(40);
        hb2.setPrefWidth(360);
        hb2.setLayoutX(10);
        hb2.setLayoutY(66);
        vb1.getChildren().add(hb2);
        
        //ch141
        PasswordField tp1 = new PasswordField();
        tp1.setPrefHeight(40);
        tp1.setPrefWidth(282);
        tp1.setPromptText("Password...");
        hb2.setMargin(tp1,new Insets(5,0,0,20));
        hb2.getChildren().add(tp1);
        
        //ch142
        FontAwesomeIconView f2 = new FontAwesomeIconView();
        f2.setGlyphName("LOCK");
        f2.setOpacity(0.39);
        f2.setStyle("-fx-font-size:38;-fx-text-fill:#fff;");
        hb2.setMargin(f2, new Insets(0,0,0,10));
        hb2.getChildren().add(f2);
        
        //ch15
        Button  btn1 = new Button();
        btn1.setMnemonicParsing(false);
        btn1.setText("Sign In");
        btn1.setPrefHeight(38);
        btn1.setPrefWidth(178);
        btn1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                Scene scene;
                Parent root;
                if(tf1.getText().isEmpty()==false && tp1.getText().isEmpty()==false){
                    int check=-1;
                    try {
                        check = loginUserVerify(tf1.getText(),tp1.getText());
                    } catch (SQLException ex) {
                        System.out.println("Error : "+ex.getMessage());
                    }
                    if(check==1){
                        int rids=-1;
                        try {
                            rids = loginAccessVerify(tf1.getText(),tp1.getText());
                        } catch (SQLException ex) {
                            System.out.println("Error : "+ex.getMessage());
                        }
                        if(rids == 1){
                            try {
                                root = FXMLLoader.load(getClass().getResource("/StockManagment/Fxml/Dashboard.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root, 1000, 600);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                System.out.println("Error : "+ex.getMessage());
                            }
                        }else if(rids == 2){
                            try {
                                root = FXMLLoader.load(getClass().getResource("/StockManagment/Fxml/userDashboard.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root, 1000, 600);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                System.out.println("Error : "+ex.getMessage());
                            }
                        }else if(rids == -1){
                            l2.setText("Something went wrong, please try again!");
                        }else if(rids == -89){
                            l2.setText("Database Connection Error!");
                        }

                    }else if(check==-1){
                        l2.setText("Something went wrong, please try again!");
                    }else if(check==0){
                        l2.setText("Incorect Username or Password!");
                    }else if(check==-89){
                        l2.setText("Database Connection Error!");
                    }
                }else{
                    l2.setText("All fields are required!");
                }
            }
            
        });
        btn1.setStyle("-fx-background-color:#007bff;-fx-text-fill:#fff;-fx-cursor: hand;-fx-font-size:17;");
        vb1.setMargin(btn1,new Insets(20,0,0,150));
        vb1.getChildren().add(btn1);
        
        /*ch151
        FontAwesomeIconView f3 = new FontAwesomeIconView();
        f3.setGlyphName("SIGN_IN");
        f3.setStyle("-fx-font-size:17;");
        f3.setFill(Color.WHITE);
        btn1.setGraphic(f3);
        btn1.setGraphicTextGap(10);*/     
        
        //ch2
        Label l3 = new Label();
        l3.setLayoutX(230);
        l3.setLayoutY(67);
        l3.setStyle("-fx-font-size:45");
        l3.setText("Stock Management System");
        anch.getChildren().add(l3);
        
        //scene init
        Scene scene = new Scene(anch,1000,600);
        stage.setScene(scene);
        stage.setResizable(false);
        Image icon = new Image("img/sLogo.png");
        stage.getIcons().add(icon);
        stage.setTitle("Stock Management System");
        stage.show();
    }


    public static void main(String[] args) {launch(args);}

    private int loginUserVerify(String username, String password) throws SQLException{
        try{
            ObservableList<UsersModel> available_user = Users.getUsers(username,password);
            return available_user.size();
        }catch(SQLException e){
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
        }catch(SQLException e){
            System.out.println("Error : "+e.getMessage());
            return -89;
        }
    }
}
