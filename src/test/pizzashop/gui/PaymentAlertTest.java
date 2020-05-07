package pizzashop.gui;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
class PaymentAlertTest {
    private PaymentAlert alert;
    private Payment payment;

    @Start
    private void start(Stage stage) {

        stage.setScene(new Scene(new StackPane(), 100, 100));
        stage.show();
        alert = new PaymentAlert();

    }

    @Disabled
    void notExecuted() {
        System.out.println("Disabled demonstration");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Card", "Cash"})
    @Timeout(5)
    void validPaymentType(String str, FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(5, 25.5);

        });
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("Pay " + (str.equals("Card") ? "by " : "") + str);

        assertEquals(5, payment.getTableNumber());
        assertEquals(25.5, payment.getAmount());
        assertEquals(payment.getType(), PaymentType.valueOf(str));

    }

    @Test
    @DisplayName("Invalid Payment Type, Display demo")
    void invalidPaymentType(FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(6, 200);
        });
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("Cancel");

        assertNull(payment);
    }

    @Test
    void negativeTotalAmount(FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(6, -20);
        });

        assertNull(payment);
    }

    @Test
    void tableNumberTooSmall(FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(0, 84.43);
        });

        assertNull(payment);
    }

    @Test
    void tableNumberTooBig(FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(12, 67.13);
        });

        assertNull(payment);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7})
    void tableNumberValidLimits(int tableNumber, FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(tableNumber, 0.0001);

        });
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("Pay by Card");

        assertEquals(tableNumber, payment.getTableNumber());
        assertEquals(0.0001, payment.getAmount());
        assertEquals(PaymentType.Card, payment.getType());

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 9})
    void tableNumberInvalidLimits(int tableNumber, FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(tableNumber, 0.0001);

        });


        assertNull(payment);

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0001, 1})
    void totalAmountValidLimits(double totalAmount, FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(1, totalAmount);

        });
        WaitForAsyncUtils.waitForFxEvents();
        robot.clickOn("Pay Cash");

        assertEquals(1, payment.getTableNumber());
        assertEquals(totalAmount, payment.getAmount());
        assertEquals(PaymentType.Cash, payment.getType());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.0001, 0, -1})
    void totalAmountInvalidLimits(double totalAmount, FxRobot robot) {
        Platform.runLater(() -> {
            payment = alert.showPaymentAlert(1, totalAmount);

        });


        assertNull(payment);
    }
}