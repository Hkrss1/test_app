/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.LoginWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author risha
 */
public class MainLauncher extends Application{

    private static Stage stage;


    @Override
    public void start(Stage pStage) throws Exception {
        this.stage = pStage;
        stage.setTitle("LoginPage");
        Parent loginroot = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        stage.getIcons().add(new Image(this.getClass().getClassLoader().getResource("Images/LogoMain.png").toString()));
        Scene login = new Scene(loginroot);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(login);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return stage;
    }
    
}
