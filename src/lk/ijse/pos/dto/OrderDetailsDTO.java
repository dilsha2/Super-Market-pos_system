package lk.ijse.pos.dto;

public class OrderDetailsDTO {
    private String orderID;
    private String itemCode;
    private int orderQTY;
    private double discont;
    private double total;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderID, String itemCode, int orderQTY, double discont, double total) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.discont = discont;
        this.total = total;
    }
    public OrderDetailsDTO(String itemCode, int orderQTY, double discont, double total) {
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.discont = discont;
        this.total = total;
    }
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getDiscont() {
        return discont;
    }

    public void setDiscont(double discont) {
        this.discont = discont;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQTY=" + orderQTY +
                ", discont=" + discont +
                ", total=" + total +
                '}';
    }
}
