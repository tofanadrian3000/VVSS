package pizzashop;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void restoreFile(){
        ClassLoader classLoader = Utils.class.getClassLoader();
        File file = new File(classLoader.getResource("data/payments.txt").getFile());
        BufferedWriter paymentBufferWriter = null;
        try {
            paymentBufferWriter = new BufferedWriter(new FileWriter(file));
            paymentBufferWriter.write("6,Cash,13.97");
            paymentBufferWriter.newLine();
            paymentBufferWriter.write("1,Card,3.45");
            paymentBufferWriter.newLine();

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
