package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void constructorMatchesGetters(){
        Payment payment = new Payment(2, PaymentType.Card, 20);

        assertEquals(2, payment.getTableNumber());
        assertEquals(PaymentType.Card, payment.getType());
        assertEquals(20, payment.getAmount());
    }

    @Test
    void stringIsConstructedCorrectly() {
        Payment payment = new Payment(2, PaymentType.Cash, 20);

        assertEquals("2,Cash,20.0", payment.toString());
    }
}