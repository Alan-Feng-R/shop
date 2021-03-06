package controller;

import dao.impl.CompanyDaoImpl;
import domain.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class Create {
    @FXML
    public TextField cname;
    @FXML
    public TextField name;

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

    public void confirm(ActionEvent actionEvent) {
        if (!cname.getText().equals(name.getText())){
            alert("ERROR", "Company input is inconsistent, please re-enter!", null, Alert.AlertType.ERROR);
            return;
        }
        List<Company> list = new CompanyDaoImpl().findAll();
        for (Company company : list) {
            if (name.getText().equals(company.getCompanyName())){
                alert("ERROR", "The company already exists, please re-enter!", null, Alert.AlertType.ERROR);
                return;
            }
        }
        new CompanyDaoImpl().insertCompany(name.getText());
        alert("SUCCESS", "registration success!", null, Alert.AlertType.INFORMATION);


    }
}
