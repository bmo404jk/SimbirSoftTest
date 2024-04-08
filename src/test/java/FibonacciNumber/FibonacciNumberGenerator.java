package FibonacciNumber;
import java.util.Date;
public class FibonacciNumberGenerator {
    public int GetFibonacciNumber()
    {
        Date date = new Date();
        int currentDate = date.getDate() + 1;
        int n0 = 0;
        int n1 = 1;
        int n2 = 0;
        for(int i = 3; i <= currentDate; i++){
            n2=n0+n1;
            n0=n1;
            n1=n2;
        }
        return n2;
    }
}
