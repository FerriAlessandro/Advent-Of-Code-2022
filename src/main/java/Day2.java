import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day2.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){
        int score = 0;
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            char enemyMove = data.charAt(0);
            char myMove = data.charAt(2);

            score+=myMove-87; //Convert the decimal value of a char to 1, 2 or 3
            if(myMove - 23 == enemyMove) //Offset the value of the X, Y and Z to that of A, B and C
                score+=3;

           /*More readable statement
           else if((myMove == 'X' && enemyMove == 'C') || (myMove == 'Y' && enemyMove == 'A') || (myMove == 'Z' && enemyMove == 'B'))
                score+=6;*/

            else if((enemyMove-64)%3+1 == myMove-87 ) //If you pick the 'letter' that immediately follows the one chosen by your enemy, you win (module for a circular shifting)
                score+=6;

        }
        System.out.println(score);
    }

    public static void secondProblem(Scanner scanner){
        int score = 0;
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            char enemyMove = data.charAt(0);
            char myObjective = data.charAt(2);
            if(myObjective == 'Y') {
                score = score + 3 + enemyMove-64;
            }
            else if(myObjective == 'Z') {
                score = score+ 6 + (enemyMove-64)%3 + 1;
            }
            else score = score + (enemyMove-64 - 2 + 3)%3 +1;

        }
        System.out.println(score);
    }
}
