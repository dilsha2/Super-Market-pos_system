package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CashierDashBoardBO;
import lk.ijse.pos.dto.CustomerOrderDTO;
import lk.ijse.pos.entity.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CashierDashBoardFormController {
    public AnchorPane cashierDashboardContext;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox cmbCustId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtOrderId;
    public JFXTextField txtDate;
    public TableView <CustomerOrderDTO>tblItem;
    public TableColumn colItem;
    public TableColumn colQty;
    public Label lblTotal;

    private static CashierDashBoardBO cashierDashboardBO=
            (CashierDashBoardBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERDASHBOARD);


    public void initialize() throws SQLException, ClassNotFoundException {
        colItem.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));

        loadDateTime();

        loadCustomerId();

        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData( newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCustomerData(Object newValue) throws SQLException, ClassNotFoundException {
        Customer c1 = cashierDashboardBO.setCustomerData((String) cmbCustId.getValue());
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(c1.getCustName());
            txtAddress.setText(c1.getCustAddress());
        }
        loadorder();
        setItems();
    }

    private void setItems() throws SQLException, ClassNotFoundException {
        double price=0;
        ObservableList<CustomerOrderDTO> obList=cashierDashboardBO.setCustomeOrderItem(txtOrderId.getText());
        for (CustomerOrderDTO temp:obList) {
            price+=temp.getPrice();
        }
        lblTotal.setText(String.valueOf(price));
        tblItem.setItems(obList);
    }

    private void loadorder() throws SQLException, ClassNotFoundException {
        ResultSet rst=cashierDashboardBO.getCustomerOrder((String) cmbCustId.getValue());
        if(rst.next()){
            txtOrderId.setText(rst.getString(1));
            txtDate.setText(rst.getString(2));
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        List<String> customerIds = cashierDashboardBO.loadCustomerIds();
        cmbCustId.getItems().addAll(customerIds);
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void makeOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/makeOrderOnAction.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierDashboardContext.getChildren().clear();
        cashierDashboardContext.getChildren().add(load);
    }

    public void modifyOrderOnAction(ActionEvent actionEvent) throws IOException {
        cashierDashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyOrderForm.fxml"));
        cashierDashboardContext.getChildren().add(parent);
    }

    public void removeOrderOnAction(ActionEvent actionEvent) throws IOException {
        cashierDashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeleteOrderForm.fxml"));
        cashierDashboardContext.getChildren().add(parent);
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        cashierDashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddNewCustomerForm.fxml"));
        cashierDashboardContext.getChildren().add(parent);
    }

    public void customersOnAction(ActionEvent actionEvent) throws IOException {
        cashierDashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomersForm.fxml"));
        cashierDashboardContext.getChildren().add(parent);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) cashierDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierDashBoardForm.fxml"))));
    }

    public void orderTableOnAction(ActionEvent actionEvent) throws IOException {
        cashierDashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/orderDetailsForm.fxml"));
        cashierDashboardContext.getChildren().add(parent);
    }


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cashierDashboardContext.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.show();
    }
}
