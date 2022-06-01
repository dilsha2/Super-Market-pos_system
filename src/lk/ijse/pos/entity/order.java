package lk.ijse.pos.entity;

import java.util.ArrayList;

public class order {
    private String orderId;
    private String date;
    private String custId;
    private ArrayList<orderDetails> items;

    public order(String orderId, String date, String custId, ArrayList<orderDetails> items) {
        this.orderId = orderId;
        this.date = date;
        this.custId = custId;
        this.items = items;
    }

    public order() {
    }

    public order(String orderId, String date, String custId) {
        this.orderId = orderId;
        this.date = date;
        this.custId = custId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", custId='" + custId + '\'' +
                '}';
    }

    public ArrayList<orderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<orderDetails> items) {
        this.items = items;
    }
}
