package system;

import controllers.DKController;
import controllers.EUController;
import controllers.ParliamentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    private Stage primaryStage;
    private Dimension screenSize;

    public Controller(Stage primaryStage, Dimension dimension){
        this.primaryStage = primaryStage;
        this.screenSize = dimension;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        dkFlag.setFitHeight(screenSize.getHeight() / 3);
        euFlag.setFitHeight(screenSize.getHeight() / 3);
    }

    @FXML
    private ImageView dkFlag;
    @FXML
    private ImageView euFlag;
    @FXML
    private GridPane pane;
    @FXML
    public void changeSceneToDk(){
        try {
            System.out.println("running?");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/parliament.fxml"));
            loader.setController(new ParliamentController(primaryStage, screenSize));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
            System.out.println("scene changed to dk");
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    @FXML
    public void changeSceneToEu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/euDashboard.fxml"));
            loader.setController(new EUController(primaryStage, screenSize));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
            System.out.println("scene changed to eu");
        }
        catch (IOException e){
            System.out.println("something went wrong");
        }

    }


}
