package pizzashop.integration;

import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pizzashop.Utils.restoreFile;

public class ServiceRepositoryTest {
    @Test
    public void whenPaymentIsAddedItCanBeRetrieved(){
        restoreFile();

        PizzaService service = new PizzaService(null, new PaymentRepository());
        service.addPayment(2, PaymentType.Card, 15.13);

        assertEquals(3, service.getPayments().size());
        assertEquals(15.13, service.getPayments().get(2).getAmount());

        service = new PizzaService(null, new PaymentRepository());

        assertEquals(3, service.getPayments().size());
        assertEquals(15.13, service.getPayments().get(2).getAmount());

        restoreFile();
    }

    @Test
    public void cashTotalAmountIsCalculatedCorrectly() {
        PizzaService service = new PizzaService(null, new PaymentRepository());

        assertEquals(13.97, service.getTotalAmount(PaymentType.Cash));
    }

    @Test
    public void cardTotalAmountIsCalculatedCorrectly() {
        PizzaService service = new PizzaService(null, new PaymentRepository());

        assertEquals(3.45, service.getTotalAmount(PaymentType.Card));
    }
}
