package pizzashop.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @Test
    void ifPaymentListIsNullThenTotalIs0() {
        PaymentRepository paymentRepoMock = mock(PaymentRepository.class);
        when(paymentRepoMock.getAll()).thenReturn(null);
        PizzaService service = new PizzaService(null, paymentRepoMock);

        assertEquals(0, service.getTotalAmount(PaymentType.Cash));
    }
    @Test
    void ifPaymentListIsEmptyThenTotalIs0() {
        PaymentRepository paymentRepoMock = mock(PaymentRepository.class);
        when(paymentRepoMock.getAll()).thenReturn(new ArrayList<>());
        PizzaService service = new PizzaService(null, paymentRepoMock);

        assertEquals(0, service.getTotalAmount(PaymentType.Cash));
    }
    @Test
    void ifPaymentListHasNoElementsThenTotalIs0() {
        PaymentRepository paymentRepoMock = mock(PaymentRepository.class);
        ArrayList<Payment> arrayMock = spy(new ArrayList());
        when(arrayMock.size()).thenReturn(1);
        when(paymentRepoMock.getAll()).thenReturn(arrayMock);
        PizzaService service = new PizzaService(null, paymentRepoMock);

        assertEquals(0, service.getTotalAmount(PaymentType.Cash));

    }
    @Test
    void ifTotalIsGreaterThan0ThenTotalIsCalculatedCorrectly() {
        PaymentRepository paymentRepoMock = mock(PaymentRepository.class);
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, 24));
        payments.add(new Payment(2, PaymentType.Card, 56));
        when(paymentRepoMock.getAll()).thenReturn(payments);
        PizzaService service = new PizzaService(null, paymentRepoMock);

        assertEquals(80, service.getTotalAmount(PaymentType.Card));
    }

    @Test
    void ifTotalIsNegativeThen0IsReturned() {
        PaymentRepository paymentRepoMock = mock(PaymentRepository.class);
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, -24));
        when(paymentRepoMock.getAll()).thenReturn(payments);
        PizzaService service = new PizzaService(null, paymentRepoMock);

        assertEquals(0, service.getTotalAmount(PaymentType.Card));
    }
}