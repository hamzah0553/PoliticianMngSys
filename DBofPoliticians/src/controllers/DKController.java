package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.ExcelReader;
import models.ParliamentFetcher;
import models.ParliamentMember;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static system.DB.getDB;


public class DKController implements Initializable {

    Stage primaryStage;
    Dimension screenSize;

    @FXML
    Button parliamentBtn;
    @FXML
    Button selectionBtn;
    @FXML
    Button ministerBtn;
    @FXML
    Button spokesmanBtn;




    public DKController(Stage primaryStage, Dimension screenSize){
        this.primaryStage = primaryStage;
        this.screenSize = screenSize;
    }

    //Run on initializing the dashboard
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parliamentBtn.setOnAction(event ->{
            try {
                System.out.println("running?");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/parliament.fxml"));
                loader.setController(new ParliamentController(primaryStage, screenSize));
                Parent root = loader.load();
                primaryStage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
                System.out.println("scene changed to parliament");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });

        selectionBtn.setOnAction(event ->{
            try {
                System.out.println("running?");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/selection.fxml"));
                loader.setController(new SelectionController(primaryStage, screenSize));
                Parent root = loader.load();
                primaryStage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
                System.out.println("scene changed to selection");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });


    }

}
