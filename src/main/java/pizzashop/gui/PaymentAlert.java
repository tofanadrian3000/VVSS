package pizzashop.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PaymentOperation;
import pizzashop.service.PizzaService;

import java.util.Optional;

public class PaymentAlert implements PaymentOperation {


    public PaymentAlert(){ }

    @Override
    public void cardPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying by card...");
        System.out.println("Please insert your card!");
        System.out.println("--------------------------");
    }

    @Override
    public void cashPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying cash...");
        System.out.println("Please show the cash...!");
        System.out.println("--------------------------");
    }

    @Override
    public void cancelPayment() {
        System.out.println("--------------------------");
        System.out.println("Payment choice needed...");
        System.out.println("--------------------------");
    }

    public Payment showPaymentAlert(int tableNumber, double totalAmount ) {
        if(tableNumber < 1 || tableNumber > 8) {
            cancelPayment();
            return null;
        }
        if(totalAmount<=0) {
            cancelPayment();
            return null;
        }
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");

        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();

        if(result.isPresent()) {
            if (result.get() == cardPayment) {
                cardPayment();
                return new Payment(tableNumber, PaymentType.Card, totalAmount);
            } else if (result.get() == cashPayment) {
                cashPayment();
                return new Payment(tableNumber, PaymentType.Cash, totalAmount);
            } else {
                cancelPayment();
            }
        }
        cancelPayment();
        return null;
    }
}
