import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    static class Node {
        int i, j;
        public Node (int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day9.txt");
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
        StringBuilder b;
        Set<String> seen = new HashSet<>();
        seen.add("0,0");
        int i_head=0, j_head=0, i_tail=0, j_tail=0;
        int numOfSteps;
        int visited = 1; //Assuming they overlap in the first position
        char direction;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            direction = data.charAt(0);
            b = new StringBuilder();
            for(int i=2; i<data.length(); i++)
                b.append(data.charAt(i));
            numOfSteps = Integer.parseInt(b.toString());
            while(numOfSteps > 0) {
                if (direction == 'R') {
                    if (j_head > j_tail) {
                        if (i_head == i_tail) {//same row
                            j_tail++;
                        } else {
                            j_tail = j_head;
                            i_tail = i_head;
                        }
                    }
                    j_head++;
                } else if (direction == 'L') {
                    if (j_head < j_tail) {
                        if (i_head == i_tail) {
                            j_tail--;
                        } else {
                            j_tail = j_head;
                            i_tail = i_head;
                        }
                    }
                    j_head--;
                } else if (direction == 'U') {
                    if (i_head < i_tail) {
                        if (j_head == j_tail) {
                            i_tail--;
                        } else {
                            i_tail = i_head;
                            j_tail = j_head;
                        }
                    }
                    i_head--;
                } else {
                    if (i_head > i_tail) {
                        if (j_head == j_tail) {
                            i_tail++;
                        } else {
                            i_tail = i_head;
                            j_tail = j_head;
                        }
                    }
                    i_head++;
                }
                if(!seen.contains((i_tail+","+j_tail))){
                    seen.add(i_tail+","+j_tail);
                    visited++;
                }
                numOfSteps--;
            }

        }
        System.out.println(visited);

    }

    public static void secondProblem(Scanner scanner) {

        String data;
        StringBuilder b;
        Set<String> seen = new HashSet<>();
        seen.add("0,0");
        Node [] list  = new Node [10];
        for(int i =0; i<10; i++)
            list[i] = new Node(0,0);
        int numOfSteps;
        int visited = 1; //Assuming they overlap in the first position
        char direction;
        while (scanner.hasNext()) {
            data = scanner.nextLine();
            direction = data.charAt(0);
            b = new StringBuilder();
            for (int i = 2; i < data.length(); i++)
                b.append(data.charAt(i));
            numOfSteps = Integer.parseInt(b.toString());
            while(numOfSteps > 0){
                if(direction == 'R')
                    list[0].j ++;
                else if(direction == 'L')
                    list[0].j --;
                else if(direction == 'U')
                    list[0].i --;
                else list[0].i ++;

                for(int i = 1; i<10; i++){
                   if(Math.abs(list[i-1].i -list[i].i) > 1 || Math.abs(list[i-1].j - list[i].j) > 1){
                       if(list[i-1].i == list[i].i){
                           if(list[i-1].j > list[i].j)
                               list[i].j ++;
                           else list[i].j --;
                       }
                       else if(list[i-1].j == list[i].j){
                           if(list[i-1].i > list[i].i)
                               list[i].i ++;
                           else list[i].i--;
                       }
                       else{
                           if(list[i-1].i > list[i].i)
                               list[i].i++;
                           else list[i].i--;

                           if(list[i-1].j > list[i].j)
                               list[i].j ++;
                           else list[i].j --;
                       }
                   }
                }
                if(!seen.contains((list[9].i+","+list[9].j))) {
                    seen.add(list[9].i + "," + list[9].j);
                    visited++;
                }
                numOfSteps--;

            }

        }
        System.out.println(visited);
    }
}
