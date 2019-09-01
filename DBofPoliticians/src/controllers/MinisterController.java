package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static system.DB.getDB;


public class MinisterController implements Initializable {

    //Variables
    ArrayList<ParliamentMember> members;
    File selectedFile;
    String url;

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

    public MinisterController(Stage primaryStage, Dimension screenSize){

    }

    //Run on initializing the dashboard
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        members = new ParliamentFetcher().getParliamentFromDB();
        showMembers();

    }

    //Observable for the parliamentMembers
    public ObservableList<ParliamentMember> observableForPM(){
        ObservableList<ParliamentMember> parliamentMembers = FXCollections.observableArrayList(members);
        return parliamentMembers;
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
