package cloud.crosstraining.devstore.application;
import java.util.List;

public class FizzBuzz {
    public static String execute(Integer count) {
        List<String> result = new java.util.ArrayList<>();
        String text = "";
        for(int i=0;i<count;i++){
            text= i+" ";
            text+=i%3==0?"Fizz":"";
            text+=i%5==0?"Buzz":"";
            result.add(text);
        }
        return String.join("\n", result);       
    }
}
