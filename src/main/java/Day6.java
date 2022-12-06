import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day6 {
    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day6.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){

        Set<Character> chars = new HashSet<>();
        int start = 0, end = 0;
        String data = scanner.nextLine();
        boolean found = false;
        while(end < data.length() && !found){
            if(!chars.contains(data.charAt(end))){
                chars.add(data.charAt(end));
                if(end-start == 3)
                    found = true;
            }
            else{
                while(data.charAt(start) != data.charAt(end)){
                    chars.remove(data.charAt(start));
                    start++;
                }
                start++;
            }
            end++;
        }
            System.out.println(end);

    }

    public static void secondProblem(Scanner scanner){

        Set<Character> chars = new HashSet<>();
        int start = 0, end = 0;
        String data = scanner.nextLine();
        boolean found = false;
        while(end < data.length() && !found){
            if(!chars.contains(data.charAt(end))){
                chars.add(data.charAt(end));
                if(end-start == 13)
                    found = true;
            }
            else{
                while(data.charAt(start) != data.charAt(end)){
                    chars.remove(data.charAt(start));
                    start++;
                }
                start++;
            }
            end++;
        }
        System.out.println(end);
    }
}

