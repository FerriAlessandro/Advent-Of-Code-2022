import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {

    public static void main (String [] args) {

        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day1.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    public static void firstProblem(Scanner scanner){
        String data;
        int max = 0;
        int sum = 0;
        while(scanner.hasNext()) {
            data = scanner.nextLine();
            if (!data.equals("")) {
                sum += Integer.parseInt(data);
            }
            else {
                if (sum > max)
                    max = sum;
                sum = 0;
            }
        }
        System.out.println(max);

    }

    public static void secondProblem(Scanner scanner){
        List<Integer> calories = new ArrayList<>();
        int sum = 0;
        String data;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(!data.equals("")){
                sum+= Integer.parseInt(data);
            }
            else{
                calories.add(sum);
                sum=0;
            }
        }
        calories.sort(Comparator.reverseOrder());
        System.out.println(calories.get(0) + calories.get(1) + calories.get(2));


    }
}
