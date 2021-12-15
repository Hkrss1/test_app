/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ChatWindow;
import Client.LoginWindow.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Client.messages.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author risha
 */
public class ChatController {


    public Label usernameLabel;
    public ListView messageList;
    public ListView onlineUserList;
    public ComboBox statusSelector;
    public Label userCount;
    public TextArea messageField;
    public Button sendBtn;


    public ChatController() {

    }

    public void sendBtnClicked(MouseEvent mouseEvent) throws IOException {

        String msg = this.messageField.getText();
        if (!this.messageField.getText().isEmpty()) {
            Listener.send(msg);
            this.messageField.clear();
        }

    }

    public void setOnlineLabel(String usercount) {
        Platform.runLater(() -> {
            this.userCount.setText(usercount);
        });
    }

    public void setUserList(Message msg) {

        Platform.runLater(() -> {
            ObservableList<User> users = FXCollections.observableList(msg.getUsers());
            this.onlineUserList.setItems(users);
            this.setOnlineLabel(String.valueOf(msg.getUserlist().size()));
        });

    }


    public void addToChat(Message message) {
        Label messageLabel = new Label();
        messageLabel.setText(message.getName() + ": " + message.getMessage());
        messageList.getItems().add(messageLabel);
        Task<HBox> othersMessages = new Task<HBox>() {
            public HBox call() throws Exception {
                Label bl6 = new Label();
                bl6.setText(message.getName() + ": " + message.getMessage());
                bl6.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.WHITE, (CornerRadii) null, (Insets) null)}));
                HBox x = new HBox();
                x.getChildren().addAll(new Node[]{bl6});
                ChatController.this.setOnlineLabel(Integer.toString(message.getOnlineCount()));
                return x;
            }
        };
        othersMessages.setOnSucceeded((event) -> {
            this.messageList.getItems().add(othersMessages.getValue());
        });
        Task<HBox> yourMessages = new Task<HBox>() {
            public HBox call() throws Exception {

                Label bl6 = new Label();

                bl6.setText(message.getMessage());
                bl6.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.LIGHTGREEN, (CornerRadii) null, (Insets) null)}));
                HBox x = new HBox();
                x.setMaxWidth(ChatController.this.messageList.getWidth() - 20.0D);
                x.setAlignment(Pos.TOP_RIGHT);
                x.getChildren().addAll(new Node[]{bl6});
                ChatController.this.setOnlineLabel(Integer.toString(message.getOnlineCount()));
                return x;
            }
        };
        yourMessages.setOnSucceeded((event) -> {
            this.messageList.getItems().add(yourMessages.getValue());
        });
        Thread t2;
        if (message.getName().equals(this.usernameLabel.getText())) {
            t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
            t2 = new Thread(othersMessages);
            t2.setDaemon(true);
            t2.start();
        }
    }


    public synchronized void addAsServer(final Message msg) {
        Task<HBox> task = new Task<HBox>() {
            public HBox call() throws Exception {
                Label bl6 = new Label();
                bl6.setText(msg.getMessage());
                bl6.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.ALICEBLUE, (CornerRadii) null, (Insets) null)}));
                HBox x = new HBox();
                x.setAlignment(Pos.CENTER);
                x.getChildren().addAll(new Node[]{bl6});
                return x;
            }
        };
        task.setOnSucceeded((event) -> {
            this.messageList.getItems().add(task.getValue());
        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }


}
