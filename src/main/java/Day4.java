import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {

    public static void main(String [] args){

        try{
            File input = new File("C:\\Users\\alefe\\OneDrive\\Desktop\\Coding\\AdventOfCode2022\\src\\main\\resources\\Day4.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public static void firstProblem (Scanner scanner){

        int count = 0;
        String [] intervals;
        String [] firstInterval;
        String [] secondInterval;
        int startFirst, endFirst, startSecond, endSecond;
        while(scanner.hasNext()){
            intervals = scanner.nextLine().split(",");
            firstInterval = intervals[0].split("-");
            secondInterval = intervals[1].split("-");
            startFirst = Integer.parseInt(firstInterval[0]);
            endFirst = Integer.parseInt(firstInterval[1]);
            startSecond = Integer.parseInt(secondInterval[0]);
            endSecond = Integer.parseInt(secondInterval[1]);

            if((startFirst <= startSecond && endFirst >= endSecond) || (startFirst >= startSecond && endFirst <= endSecond ))
                count ++;
        }
        System.out.println(count);
    }

    public static void secondProblem (Scanner scanner){

        int count = 0;
        String [] intervals;
        String [] firstInterval;
        String [] secondInterval;
        int startFirst, endFirst, startSecond, endSecond;
        while(scanner.hasNext()){
            intervals = scanner.nextLine().split(",");
            firstInterval = intervals[0].split("-");
            secondInterval = intervals[1].split("-");
            startFirst = Integer.parseInt(firstInterval[0]);
            endFirst = Integer.parseInt(firstInterval[1]);
            startSecond = Integer.parseInt(secondInterval[0]);
            endSecond = Integer.parseInt(secondInterval[1]);

            if((startFirst >= startSecond && startFirst <= endSecond) || (startSecond >= startFirst && startSecond <= endFirst ))
                count ++;
        }
        System.out.println(count);
    }
}
