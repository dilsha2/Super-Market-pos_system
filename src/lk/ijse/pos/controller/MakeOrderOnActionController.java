package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.view.TM.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MakeOrderOnActionController {
    public AnchorPane orderContext;
    public Label lblOrderID;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox <String>cmbCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtaddress;
    public JFXTextField txtCity;
    public JFXComboBox <String>cmbItemID;
    public JFXTextField txtDiscription;
    public JFXTextField txtQTYOnHand;
    public JFXTextField txtUnitPrice;
    public TextField txtQTY;
    public TextField txtDiscount;
    public TableView <CartTM>tblOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTotal;
    private final OrderBO makeOrderBO= (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.MAKEORDER);

    int cartSelectedRowForRemove = -1;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        setOrderId();


        try {
            loadCustomerIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadItemIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemIds(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        cmbItemID.getItems().addAll(makeOrderBO.loadItemIds());
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        cmbCustomerID.getItems().addAll(makeOrderBO.loadCustomerIds());
    }

    private void setCustomerData(String customerData) throws SQLException, ClassNotFoundException {
        Customer c1 = makeOrderBO.setCustomerData((String) cmbCustomerID.getValue());
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(c1.getCustName());
            txtName.setText(c1.getCustName());
            txtaddress.setText(c1.getCustAddress());
            txtCity.setText(c1.getCity());
        }
    }

    private void setItemIds(String itemId) throws SQLException, ClassNotFoundException {
        Item i1 = makeOrderBO.setItemData((String) cmbItemID.getValue());
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDiscription.setText(i1.getDescription());
            txtQTYOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setOrderId() {
        try {
            lblOrderID.setText(makeOrderBO.generateNewOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDateAndTime() {
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

    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblOrder.refresh();
        }
    }

    ObservableList<CartTM> obList= FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        String discription=txtDiscription.getText();
        int qtyOnHand=Integer.parseInt(txtQTYOnHand.getText());
        double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        int QTYForCustomer=Integer.parseInt(txtQTY.getText());
        double discount=Double.parseDouble(txtDiscount.getText());
        double z=(unitPrice*QTYForCustomer);
        Double total=z-((z/100)*discount);

        if (qtyOnHand<QTYForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTM tm=new CartTM((String) cmbItemID.getValue(),discription,QTYForCustomer,unitPrice,
                discount,
                total);
        int rowNumber=isExists(tm);

        if (rowNumber==-1){// new Add
            obList.add(tm);
        }else{
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getItemCode(),
                    temp.getDiscription(),
                    temp.getQTY()+QTYForCustomer,
                    unitPrice,
                    discount,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblOrder.setItems(obList);
        calculateCost();
        quantityChange();
    }

    private void quantityChange() {
        int value = Integer.parseInt(txtQTYOnHand.getText());
        if (!txtQTY.getText().equals("") & (value > 0)) {
            int q = Integer.parseInt(txtQTY.getText());
            int q2 = Integer.parseInt(txtQTYOnHand.getText());
            int result = q2 - q;

            if (result <= 0) {
                new Alert(Alert.AlertType.WARNING, "Out Of Stock...!").show();
            } else {
                txtQTYOnHand.setText(String.valueOf(result));
            }
        }
    }

    public void ConfirmOrderOnAction(ActionEvent actionEvent) throws IOException {
        ArrayList<OrderDetailsDTO> items=new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList) {
            total+=tempTm.getTotal();
            items.add(new OrderDetailsDTO(tempTm.getItemCode(),tempTm.getQTY(),tempTm.getDiscount(),tempTm.getTotal()));
        }
        OrderDTO order=new OrderDTO(lblOrderID.getText(),lblDate.getText(),cmbCustomerID.getValue(),items);
        if (makeOrderBO.placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        URL resource = getClass().getResource("../view/OrderCompleteForm.fxml");
        Parent load = FXMLLoader.load(resource);
        orderContext.getChildren().clear();
        orderContext.getChildren().add(load);

    }

    private int isExists(CartTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }
    
    void calculateCost(){
        double ttl=0;
        for (CartTM tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");

    }
}
