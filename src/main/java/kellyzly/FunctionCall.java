package kellyzly;

import java.text.DecimalFormat;
import java.util.function.Function;

public class FunctionCall {
  // Predicate
    //Consumer
    // Function
    //Supplier
    //  UnaryOperator
    //
//https://mp.weixin.qq.com/s?src=11&timestamp=1551405176&ver=1457&signature=SUTwSg662Xd*3DOaro8xiB96r1qJnBM2YlMDm6jNR46N2Nx6dmlmpKQGlDwhmZ9LoZDBXP1FQvaJRb2gDUfdPTRURDM9OpOOpwXvtAK*9BG1KLqQjpAvq5Y3pcwr7PGi&new=1

    // method  reference 希望能少定义一些接口
    //Function<Integer,String> input : Integer  output: String

    public static void printMoney(Function<Integer,String> moneyFormat, Integer a){
        System.out.println(moneyFormat.apply(a));

    }

    public static void main(String[] args) {
     Function<Integer, String> moneyFormat = i ->new DecimalFormat("#,###").format(i);
     printMoney(moneyFormat, 9999);


    }
}
