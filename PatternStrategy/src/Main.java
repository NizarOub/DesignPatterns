import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Context context = new Context();


        // NullPointerException bcs strategy in null
        /* c.effectuerStrategy(); */

        // the Correct way :
        //c.setStrategy(new StrategyImp1());
        //context.effectuerStrategy();

        Scanner scanner = new Scanner(System.in);
        Map<String ,Strategy> strategyMap = new HashMap<>();
        Strategy strategy;
        while (true) {
            System.out.println("Quelle stratégie? : ");
            String str = scanner.nextLine();
            strategy = strategyMap.get(str);
            if (strategy == null){
                System.out.println("Création d'un nouvel objet de StrategyImp" + str);
                strategy = (Strategy) Class.forName("StrategyImp" + str)
                        .getConstructor().newInstance();
                strategyMap.put(str,strategy);
            }
            context.setStrategy(strategy);
            context.effectuerStrategy();
        }
// test
    }
}