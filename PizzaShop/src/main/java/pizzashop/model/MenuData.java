package pizzashop.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MenuData {
    private String menuItem;
    private Integer quantity;
    private Double price;

    public MenuData(String mItem, Integer mQuantity, Double mPrice) {
        this.menuItem = mItem;
        this.quantity = mQuantity;
        this.price = mPrice;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() {
        return price;
    }

}
