import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Integer> topThree = new ArrayList<>();
        int sum = 0;
        int minIndex;
        String data;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(!data.equals("")){
                sum+= Integer.parseInt(data);
            }
            else{
                if(topThree.size() < 3) {
                    topThree.add(sum);
                }
                else{
                    minIndex = 0;
                    for(int i=1;i<3;i++){
                        if(topThree.get(i) < topThree.get(minIndex))
                            minIndex = i;
                    }
                    if(sum > topThree.get(minIndex))
                        topThree.set(minIndex, sum);
                }

                sum=0;
            }
        }
        sum = 0;
        for(int i=0;i<3;i++)
            sum+=topThree.get(i);
        System.out.println(sum);

    }
}
