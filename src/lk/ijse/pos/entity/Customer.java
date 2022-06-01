package lk.ijse.pos.entity;

public class Customer {
    private String CustId;
    private String CustTitle;
    private String CustName;
    private String CustAddress;
    private String city;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String custId, String custTitle, String custName, String custAddress, String city, String province, String postalCode) {
        CustId = custId;
        CustTitle = custTitle;
        CustName = custName;
        CustAddress = custAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public String getCustTitle() {
        return CustTitle;
    }

    public void setCustTitle(String custTitle) {
        CustTitle = custTitle;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustId='" + CustId + '\'' +
                ", CustTitle='" + CustTitle + '\'' +
                ", CustName='" + CustName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
