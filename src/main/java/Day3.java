import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day3.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){
        int sum = 0;
        boolean found;


        while(scanner.hasNext()){
            Set<Character> items = new HashSet<>();
            String data = scanner.nextLine();
            found = false;
            for(int i=0;i<data.length() && !found; i++){
                if(i < data.length()/2)
                    items.add(data.charAt(i));
                else if(items.contains(data.charAt(i)) && i >= data.length()/2) {
                    if(data.charAt(i) >= 'A' && data.charAt(i) <= 'Z')
                        sum+= data.charAt(i)-38; //ASCII magic
                    else sum+= data.charAt(i)-96;//ASCII magic
                    found = true;
                }
            }
        }
        System.out.println(sum);

    }

    public static void secondProblem(Scanner scanner){
        int sum = 0;

        while(scanner.hasNext()){
            Map<Character, Integer> items = new HashMap<>();
            String data;
            boolean found = false;

            for(int i=0;i<3 && !found;i++){

                data = scanner.nextLine();
                Set<Character> itemsSet = new HashSet<>(); //this one allows us to add 1 single item of each type for each elf (if Elf 1 has 3 items "P" we add it only once to the hashmap)

                for(int j=0;j<data.length(); j++){
                    if(!itemsSet.contains(data.charAt(j))){
                        itemsSet.add(data.charAt(j));

                        if(!items.containsKey(data.charAt(j)))
                            items.put(data.charAt(j), 1);

                        else
                            items.put(data.charAt(j), items.get(data.charAt(j))+1);

                        if(items.get(data.charAt(j)) == 3){
                            if(data.charAt(j) >= 'A' && data.charAt(j) <= 'Z')
                                sum+= data.charAt(j)-38; //ASCII magic
                            else sum+= data.charAt(j)-96;//ASCII magic
                            found = true;
                        }
                    }
                }

            }

        }
        System.out.println(sum);


    }
}
