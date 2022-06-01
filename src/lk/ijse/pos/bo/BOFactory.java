package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public superBO getBO(BoTypes types) {
        switch (types) {
            case ADDCUSTOMER:
               return new AddNewCustomerImpl();
               case ADDITEM:
                return new AddNewItemImpl();
            case CASHIERDASHBOARD:
                return new CashierDashBoardImpl();
            case CUSTOMERCONTROLLER:
                return new TableControllerImpl();
            case EDITCUSTOMER:
                return new UpdateCustomerImpl();
            case MAKEORDER:
                return new MakeOrderImpl();
            case MODIFYITEM:
                return new ModifyItemImpl();
            case MODIFYORDER:
                return new ModifyOrderImpl();
            case REMOVECUSTOMER:
                return new RemoveCustomerImpl();
            case REMOVEITEM:
                return new RemoveItemImpl();
            case REMOVEORDER:
                return new RemoveOrderImpl();
            case SEARCHCUSTOMER:
                return new SearchCustomerImpl();
            case INCOME:
                return new IncomeControllerBOImpl();
            default:
                return null;
        }
    }

        public enum BoTypes {
            ADDCUSTOMER,ADDITEM,CASHIERDASHBOARD,CUSTOMERCONTROLLER,EDITCUSTOMER,MAKEORDER,MODIFYITEM,MODIFYORDER, REMOVECUSTOMER,REMOVEITEM,REMOVEORDER,SEARCHCUSTOMER,INCOME
        }
}
