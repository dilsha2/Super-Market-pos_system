package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.IncomeControllerBO;
import lk.ijse.pos.entity.ItemQtyRates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDashBoardFormController {

    public AnchorPane dashboardContext;
    public NumberAxis y;
    public BarChart barChartItem;
    public CategoryAxis x;

    private final IncomeControllerBO incomeControllerBO=
            (IncomeControllerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.INCOME);

    public void initialize(){
        XYChart.Series set = new XYChart.Series();
        List <ItemQtyRates>itemQtyRates1=new ArrayList<>();

        try {
            itemQtyRates1=incomeControllerBO.getItemCode();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (ItemQtyRates temp:itemQtyRates1
        ) {
            set.getData().add(new XYChart.Data(temp.getItemCode(),temp.getQty()));
        }
        barChartItem.getData().addAll(set);
    }

    public void addNewMemberOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddNewCustomerForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.show();
    }

    public void registerItemOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomersForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemTableForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void removeItemOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeleteItemForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void modifyItemOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/EditItemForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }
}
