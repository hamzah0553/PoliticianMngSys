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
import models.*;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SelectionController implements Initializable {

    //Variables
    public ArrayList<Selection> selections = new ArrayList<>();
    File selectedFile;
    String url;

    //fxml items
    @FXML
    private TableView<Selection> table;
    @FXML
    private TableColumn<Selection, String> name;
    @FXML
    private TableColumn<Selection, String> party;
    @FXML
    private TableColumn<Selection, String> constituency;
    @FXML
    private TableColumn<Selection, Integer> telephone;
    @FXML
    private TableColumn<Selection, String> email;
    @FXML
    private TableColumn<Selection, String> selection;


    @FXML
    private Button excelReaderBtn;

    public SelectionController(Stage primaryStage, Dimension screenSize){

    }

    //Run on initializing the dashboard
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        excelReaderBtn.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            selectedFile = chooser.showOpenDialog(new Stage());
            url = selectedFile.getAbsolutePath();
            createExcelReader();
            if (selections.isEmpty()){
                selections = new SelectionFetcher().getSelectionsFromDb();
            }
            showMembers();
        });
        selections = new SelectionFetcher().getSelectionsFromDb();
        showMembers();

    }

    //Observable for the parliamentMembers
    public ObservableList<Selection> observableForPM(){
        ObservableList<Selection> observableList = FXCollections.observableArrayList(selections);
        return observableList;
    }

    public void createExcelReader() {
        try {
            ExcelReader excelReader = new ExcelReader(url, 1);
        }
        catch (IOException e){
            System.err.println(e.getCause() + e.getMessage());
        }
    }

    public void showMembers() {
        name.setCellValueFactory(new PropertyValueFactory<>("politician"));
        party.setCellValueFactory(new PropertyValueFactory<>("party"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        selection.setCellValueFactory(new PropertyValueFactory<>("selection"));
        constituency.setCellValueFactory(new PropertyValueFactory<>("constituency"));
        table.setItems(observableForPM());
    }



}
