
package client.controller;

import client.dataloader.DataLoader;
import client.model.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView tableview;

    private DataLoader dataLoader = new DataLoader();
    private ObservableList<Contract> contractsData;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<Contract, Integer> numberCol = new TableColumn("Номер");
        TableColumn<Contract, Calendar> createdDateCol = new TableColumn("Дата создания");
        TableColumn<Contract, Calendar> lastUpdateDateCol = new TableColumn("Дата последнего обновления");
        TableColumn<Contract, CheckBox> actionCol = new TableColumn("Активность");


        tableview.getColumns().addAll(numberCol, createdDateCol, lastUpdateDateCol, actionCol);

        try {
            contractsData = dataLoader.getData();
        } catch (Exception e) {
            e.printStackTrace();
            contractsData = FXCollections.observableArrayList();
        }


        numberCol.setCellValueFactory(
                new PropertyValueFactory<Contract, Integer>("number")
        );
        createdDateCol.setCellValueFactory(
                new PropertyValueFactory<Contract, Calendar>("createdDate")
        );
        lastUpdateDateCol.setCellValueFactory(
                new PropertyValueFactory<Contract, Calendar>("lastUpdatedDate")
        );

        actionCol.setCellValueFactory(
                new PropertyValueFactory<Contract, CheckBox>("select")
        );

        tableview.setItems(contractsData);

    }

}



