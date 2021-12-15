/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.LoginWindow;

import Client.messages.*;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Client.ChatWindow.ChatController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author risha
 */
public class LoginController {


    public Button loginBtn;
    public TextField usernameField;
    public TextField portField;
    public TextField ipField;
    public Message msg;
    public ImageView closeBtn;
    String name;
    String ip;
    String port;
    Status status;
    int count;
    ArrayList<User> users;
    ArrayList<User> list;


    //new stage
    private Stage stage;
    private Scene scene;
    private Parent root;



    //setter and getters
    public void setName(String name) {
        this.name = name;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public void setPort(String port){
        this.port = port;
    }
    public String getName() {
        return this.name;
    }
    public String getIp() {
        return this.ip;
    }
    public String getPort(){
        return this.port;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return this.status;
    }



    //constructor

    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }



    //Events
    public void onClickLoginBtn(MouseEvent mouseEvent) throws Exception {

        setName(usernameField.getText());
        setIp(ipField.getText());
        setPort(portField.getText());
        setStatus(Status.ONLINE);

        loadChat();


    }

    public void onExitBtnClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }



    public void loadChat() throws Exception{

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ChatView.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChatController chat = loader.getController();


            chat.usernameLabel.setText(getName());

            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        });

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ChatView.fxml"));
//        root = loader.load();
//
//
//        ChatController chat = loader.getController();
//        chat.usernameLabel.setText(getName());
//
//        stage = new Stage();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();


    }



}
    
    
    
    

