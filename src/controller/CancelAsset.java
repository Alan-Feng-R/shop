package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AssetSelling;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import viewModel.SourceDataViewModel;
import viewModel.TargetDataViewModel;
import viewModel.TargetDataViewModel1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CancelAsset implements Initializable {
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn remainCol;
    @FXML
    public TableColumn updateTimeCol;
    @FXML
    public TableColumn usernameCol;
    @FXML
    public TableColumn sellCol;
    @FXML
    public TableColumn stateCol;
    @FXML
    public TableView tableview;
    @FXML
    public Label companyName;
    @FXML
    public Button confirm;
    @FXML
    public Button back;


    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();
    private TargetDataViewModel1 viewModel1 = TargetDataViewModel1.getInstance();
    private SourceDataViewModel viewModel2 = SourceDataViewModel.getInstance();
    /**
     * 弹框
     */
    public void alert(String title, String content, String header, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
//        Company company = new CompanyDaoImpl().findByName(user.getCompanyName());
        companyName.setText(user.getCompanyName());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("AssetName"));
        remainCol.setCellValueFactory(new PropertyValueFactory<>("RemainNum"));
        updateTimeCol.setCellValueFactory(new PropertyValueFactory<>("uploadTime"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        sellCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        List<AssetSelling> list = new AssetDaoImpl().findByCompany(user.getCompanyName());
        ObservableList<AssetSelling> data = FXCollections.observableArrayList();
        for (AssetSelling assetSelling : list) {
            data.add(assetSelling);
        }
        tableview.setItems(data);
    }

    public void confirm(ActionEvent actionEvent) throws IOException {
        AssetSelling assetSelling = (AssetSelling) tableview.getSelectionModel().getSelectedItem();
        viewModel2.setAssetSelling(assetSelling);
        viewModel2.setTargetData3();
        if (assetSelling==null){
            alert("ERROR", "Please select the purchase price!", null, Alert.AlertType.ERROR);
            return;
        }
        viewModel2.setName1(assetSelling.getSellingPrice());
        viewModel2.setName2(assetSelling.getRemainNum());
        viewModel2.setTargetData1();
        viewModel2.setTargetData2();
        Parent root = FXMLLoader.load(getClass().getResource("../view/choose.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) confirm.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mycount.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
