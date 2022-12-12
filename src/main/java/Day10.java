import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day10.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){
        String data;
        int num;
        int cycle = 0;
        int register = 1;
        int res = 0;
        StringBuilder b;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(data.charAt(0) == 'a'){
                b = new StringBuilder();
                for(int i =5 ; i< data.length(); i++)
                    b.append(data.charAt(i));
                num = Integer.parseInt(b.toString());
                cycle+=1;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220)
                    res+=register*cycle;
                cycle+=1;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220)
                    res+=register*cycle;

                register+=num; //end of cycle
            }
            else {
                cycle+=1;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220)
                    res+=register*cycle;

            }
        }

        System.out.println(res);
    }

    public static void secondProblem(Scanner scanner){
        String data;
        int num;
        int cycle = 0;
        int register = 1;
        StringBuilder b;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(data.charAt(0) == 'a'){
                b = new StringBuilder();
                for(int i =5 ; i< data.length(); i++)
                    b.append(data.charAt(i));
                num = Integer.parseInt(b.toString());
                for(int i=0; i<2; i++){
                    cycle+=1;
                    if(register+1 == (cycle-1)%40 || register == (cycle-1)%40 || register-1 == (cycle-1)%40){
                        System.out.print("#");
                    }
                    else {
                        System.out.print(".");
                    }
                    if(cycle%40 == 0)
                        System.out.print("\n");
                }

                register+=num; //end of cycle
            }
            else {
                cycle+=1;
                if(register+1 == (cycle-1)%40 || register == (cycle-1)%40 || register-1 == (cycle-1)%40){
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
                if(cycle%40 == 0)
                    System.out.print("\n");

            }
        }

    }
}
