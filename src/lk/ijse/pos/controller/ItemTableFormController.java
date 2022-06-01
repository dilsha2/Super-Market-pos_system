package lk.ijse.pos.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.TableControllBO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.view.TM.ItemTM;

import java.sql.SQLException;

public class ItemTableFormController {
    public AnchorPane itemContext;
    public TableView <ItemTM>tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colPrice;
    public TableColumn colQty;

    private final TableControllBO tableControllBO =
            (TableControllBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CUSTOMERCONTROLLER);

    public void initialize() throws SQLException, ClassNotFoundException {

        colCode.setCellValueFactory(new PropertyValueFactory("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory("packSize"));
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));

        setCustomersToTable();

    }

    private void setCustomersToTable() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTM> obList = tableControllBO.loadAllItemTable();
        tblItem.setItems(obList);
    }
}
