package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private final String paymentFileName = "data/payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    private void readPayments(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        BufferedReader paymentBufferReader = null;
        File file = new File(classLoader.getResource(paymentFileName).getFile());

        try {
            paymentBufferReader = new BufferedReader(new FileReader(file));
            String currentLine;
            while((currentLine=paymentBufferReader.readLine())!=null){
                Payment payment=getPayment(currentLine);
                if(null != payment) {
                    paymentList.add(payment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(null != paymentBufferReader)
            {
                try{
                    paymentBufferReader.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    private Payment getPayment(String line){
        if (line==null|| line.equals("")) {
            return null;
        }
        Payment newPayment;
        StringTokenizer stringTokenizer=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(stringTokenizer.nextToken());
        String type= stringTokenizer.nextToken();
        double amount = Double.parseDouble(stringTokenizer.nextToken());
        newPayment = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return newPayment;
    }

    public void add(Payment payment){
        paymentList.add(payment);
        writeAll();
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(paymentFileName).getFile());
        BufferedWriter paymentBufferWriter = null;
        try {
            paymentBufferWriter = new BufferedWriter(new FileWriter(file));
            for (Payment currentPayment:paymentList) {
                System.out.println(currentPayment.toString());
                paymentBufferWriter.write(currentPayment.toString());
                paymentBufferWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != paymentBufferWriter)
            {
                try {
                    paymentBufferWriter.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

}
