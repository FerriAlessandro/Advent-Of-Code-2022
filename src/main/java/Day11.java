import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {

    static class Monkey{
        Queue<Long> items = new LinkedList<>();
        char operation;
        int operationFactor;
        int divisor;
        int monkeyTrue;
        int monkeyFalse;
        int inspectedItems = 0;

        public void printMonkey(){
            for(Long i : items)
                System.out.println(i);
            System.out.println("operation: "+ operation);
            System.out.println("operation factor: "+operationFactor);
            System.out.println("divisor: "+ divisor);
            System.out.println("true: "+ monkeyTrue);
            System.out.println("false: "+ monkeyFalse);

        }

    }

    public static void parseMonkey(Scanner scanner, List<Monkey> monkeys){
        String data = scanner.nextLine();
        StringBuilder b;
        Monkey m = new Monkey();
        for(int i=18; i<data.length();){
            b = new StringBuilder();
            while(i<data.length() && data.charAt(i) >= '0' && data.charAt(i)<='9'){
                b.append(data.charAt(i));
                i++;
            }
            m.items.add(Long.parseLong(b.toString()));
            while(i<data.length() && (data.charAt(i) < '0' || data.charAt(i) > '9'))
                i++;
        }
        data = scanner.nextLine();
        m.operation = data.charAt(23);
        if(data.charAt(25) == 'o')
            m.operationFactor = -1;
        else {
            b = new StringBuilder();
            int i = 25;
            while (i < data.length() && data.charAt(i) >= '0' && data.charAt(i) <= '9') {
                b.append(data.charAt(i));
                i++;
            }
            m.operationFactor = Integer.parseInt(b.toString());
        }
        data = scanner.nextLine();
        int i = 21;
        b = new StringBuilder();
        while (i < data.length() && data.charAt(i) >= '0' && data.charAt(i) <= '9') {
            b.append(data.charAt(i));
            i++;
        }
        m.divisor = Integer.parseInt(b.toString());
        data = scanner.nextLine();
        m.monkeyTrue = data.charAt(29)-'0';
        data = scanner.nextLine();
        m.monkeyFalse = data.charAt(30) - '0';

        monkeys.add(m);
    }

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day11.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){

        List<Monkey> monkeys = new ArrayList<>();
        String data;
        int res, max1=0, max2=0;
        long item;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(data.length() > 0 && data.charAt(0) == 'M'){
                parseMonkey(scanner, monkeys);
            }
        }
        for(int i=0; i<20; i++){
            for(Monkey m : monkeys){
                while(m.items.size() > 0){
                    item = m.items.poll();
                  if(m.operation == '+')
                      item += m.operationFactor;
                  else{
                      if(m.operationFactor != -1)
                          item*=m.operationFactor;
                      else item*=item;
                  }
                  item= item/3;
                  if(item%m.divisor == 0)
                      monkeys.get(m.monkeyTrue).items.add(item);
                  else monkeys.get(m.monkeyFalse).items.add(item);
                  m.inspectedItems++;
                }
            }
        }
        for(Monkey m : monkeys){
            if(m.inspectedItems > max1){
                max2 = max1;
                max1 = m.inspectedItems;
            }
            else if(m.inspectedItems > max2)
                max2 = m.inspectedItems;
        }
        res = max2*max1;
        System.out.println(res);

    }

    public static void secondProblem(Scanner scanner){

        List<Monkey> monkeys = new ArrayList<>();
        String data;
        long res, max1=0, max2=0;
        long item;
        long div=1;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(data.length() > 0 && data.charAt(0) == 'M'){
                parseMonkey(scanner, monkeys);
            }
        }
        for(Monkey m : monkeys)
            div*=m.divisor;
        for(int i=0; i<10000; i++){
            for(Monkey m : monkeys){
                while(m.items.size() > 0){
                    item = m.items.poll();
                    if(m.operation == '+')
                        item += m.operationFactor;
                    else{
                        if(m.operationFactor != -1)
                            item*=m.operationFactor;
                        else item*=item;
                    }

                    item= item%div;
                    if(item%m.divisor == 0)
                        monkeys.get(m.monkeyTrue).items.add(item);
                    else monkeys.get(m.monkeyFalse).items.add(item);
                    m.inspectedItems++;
                }
            }
        }
        for(Monkey m : monkeys){
            if(m.inspectedItems > max1){
                max2 = max1;
                max1 = m.inspectedItems;
            }
            else if(m.inspectedItems > max2)
                max2 = m.inspectedItems;
        }

        res = max2*max1;
        System.out.println(max1 + " " + max2);
        System.out.println(res);

    }
}
