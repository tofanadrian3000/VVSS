package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.util.Calendar;


public class KitchenGUIController {
    @FXML
    private ListView<String> kitchenOrdersList;
    @FXML
    public Button cookButton;
    @FXML
    public Button readyButton;

    public static  ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    private String extractedTableNumberString;
    private int extractedTableNumberInteger;

    //thread for adding data to kitchenOrderList
    public  Thread addOrders = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                Platform.runLater(() -> kitchenOrdersList.setItems(order));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    });

    public void initialize() {
        //starting thread for adding data to kitchenOrderList
        addOrders.setDaemon(true);
        addOrders.start();

        //Controller for Cook Button
        cookButton.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(null != selectedOrder) {
                kitchenOrdersList.getItems().remove((String)selectedOrder);
                kitchenOrdersList.getItems().add(selectedOrder.toString()
                        .concat(" Cooking started at: ").toUpperCase()
                        .concat(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE)));
            }
        });
        //Controller for Ready Button
        readyButton.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(null != selectedOrder) {
                kitchenOrdersList.getItems().remove((String)selectedOrder);
                extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
                extractedTableNumberInteger = Integer.parseInt(extractedTableNumberString);
                System.out.println("--------------------------");
                System.out.println("Table " + extractedTableNumberInteger + " ready at: " + now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
                System.out.println("--------------------------");
            }
        });
    }
}