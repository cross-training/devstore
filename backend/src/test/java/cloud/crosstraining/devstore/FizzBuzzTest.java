package cloud.crosstraining.devstore;
import org.junit.jupiter.api.Test;
import cloud.crosstraining.devstore.application.FizzBuzz;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void testSaludar() {
        String result = FizzBuzz.execute(10);
        String expected = "0 FizzBuzz\n" + 
                        "1 \n" + 
                        "2 \n" + 
                        "3 Fizz\n" + 
                        "4 \n" + 
                        "5 Buzz\n" + 
                        "6 Fizz\n" + 
                        "7 \n" + 
                        "8 \n" + 
                        "9 Fizz";
        assertEquals(expected, result, "FizzBuzz.execute(10) ok");
    }
}
