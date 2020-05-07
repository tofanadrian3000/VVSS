package pizzashop.repository;


import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pizzashop.Utils.restoreFile;

class PaymentRepositoryTest {

    @Test
    void whenRepositoryIsConstructedPaymentsAreReadCorrectly() {
        PaymentRepository repo = new PaymentRepository();

        assertEquals(2, repo.getAll().size());
        assertEquals(6, repo.getAll().get(0).getTableNumber());
    }

    @Test
    void whenPaymentIsAddedItCanBeRead() {
        restoreFile();

        PaymentRepository repo = new PaymentRepository();
        Payment paymentMock = mock(Payment.class);
        when(paymentMock.toString()).thenReturn("3,Cash,15.0");
        repo.add(paymentMock);

        assertEquals(3, repo.getAll().size());
        repo = new PaymentRepository();
        assertEquals(3, repo.getAll().size());

        restoreFile();

    }
}