package pizzashop.gui.DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pizzashop.model.MenuData;

public class MenuDataModel{

    private MenuData menuData;

    public MenuDataModel(String mItem, Integer mQuantity, Double mPrice) {
        this.menuData = new MenuData(mItem, mQuantity, mPrice);
    }

    public String getMenuItem() { return menuData.getMenuItem(); }

    public SimpleStringProperty menuItemProperty() { return new SimpleStringProperty(menuData.getMenuItem()); }

    public Integer getQuantity() { return menuData.getQuantity(); }

    public void setQuantity(Integer quantity) {
        menuData.setQuantity(quantity);
    }

    public Double getPrice() {
        return menuData.getPrice();
    }

}
