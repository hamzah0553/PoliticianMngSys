package controllers;

import javafx.beans.binding.Bindings;
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
import javafx.scene.input.MouseEvent;
import models.ExcelReader;
import models.ParliamentFetcher;
import models.ParliamentMember;

import java.awt.*;
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


public class ParliamentController implements Initializable {

    //Variables
    ArrayList<ParliamentMember> members;
    File selectedFile;
    String url;

    private Stage primaryStage;
    private Dimension screenSize;

    //fxml items
    @FXML
    private TableView<ParliamentMember> table;
    @FXML
    private TableColumn<ParliamentMember, String> name;
    @FXML
    private TableColumn<ParliamentMember, String> party;
    @FXML
    private TableColumn<ParliamentMember, String> email;
    @FXML
    private TableColumn<ParliamentMember, String> telephone;
    @FXML
    private TableColumn<ParliamentMember, String> workPhone;
    @FXML
    private TableColumn<ParliamentMember, String> constituency;


    @FXML
    private Button excelReaderBtn;
    @FXML
    private Button selectionNav;


    public ParliamentController(Stage primaryStage, Dimension screenSize){
        this.primaryStage = primaryStage;
        this.screenSize = screenSize;

    }

    //Run on initializing the dashboard
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        excelReaderBtn.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            selectedFile = chooser.showOpenDialog(new Stage());
            url = selectedFile.getAbsolutePath();
            createExcelReader();
            if (members.isEmpty()){
                members = new ParliamentFetcher().getParliamentFromDB();
            }
            showMembers();
        });
        selectionNav.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/selection.fxml"));
            loader.setController(new SelectionController(primaryStage, screenSize));
            try {
                Parent root = loader.load();
                primaryStage.setScene(new Scene(root, screenSize.width, screenSize.height));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        members = new ParliamentFetcher().getParliamentFromDB();
        showMembers();

        //selectionNav.addMouseListener(MouseEvent.MOUSE_CLICKED, new EventHa);

    }

    //Observable for the parliamentMembers
    public ObservableList<ParliamentMember> observableForPM(){
        ObservableList<ParliamentMember> parliamentMembers = FXCollections.observableArrayList(members);
        return parliamentMembers;
    }

    public void createExcelReader() {
        try {
            ExcelReader excelReader = new ExcelReader(url, 0);
        }
        catch (IOException e){
            System.err.println(e.getCause() + e.getMessage());
        }
    }

    public void showMembers() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        party.setCellValueFactory(new PropertyValueFactory<>("party"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        workPhone.setCellValueFactory(new PropertyValueFactory<>("workPhone"));
        constituency.setCellValueFactory(new PropertyValueFactory<>("constituency"));
        table.setItems(observableForPM());
    }



}
