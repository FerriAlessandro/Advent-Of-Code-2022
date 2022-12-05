import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day5.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void firstProblem(Scanner scanner){

        List<Deque<Character>> stackList = new ArrayList<>();
        int source = 0, dest = 0, numOfBoxes = 0;
        for(int i = 0; i<9; i++)
            stackList.add(new LinkedList<>());
        String data;
        String res;
        int stackNumber = 0;
        while(scanner.hasNext()){
            stackNumber = 0;
            data = scanner.nextLine();
            if(data.length() > 0 && data.charAt(0) == '['){
                for(int i = 1; i<data.length(); i+=4){
                    if(data.charAt(i) != ' '){
                        stackList.get(stackNumber).add(data.charAt(i));
                    }
                    stackNumber++;
                }
            }
            else if (data.length() > 0 && data.charAt(0) == 'm'){
                int i = 0, count = 0;

                String [] values = new String[3]; //index 0 contains the number of boxes to move, index 1 the source, index 2 the destination
                while(i<data.length() && count < 3) {
                    if (data.charAt(i) < '0' || data.charAt(i) > '9')
                        i++;

                    else{
                        StringBuilder builder = new StringBuilder();
                        while(i < data.length() && data.charAt(i) >= '0' && data.charAt(i) <= '9'){
                            builder.append(data.charAt(i));
                            i++;
                        }
                        values[count] = builder.toString();
                        count++;

                    }

                }

                numOfBoxes = Integer.parseInt(values[0]);
                source = Integer.parseInt(values[1]);
                dest = Integer.parseInt(values[2]);

                while(numOfBoxes > 0){
                    stackList.get(dest-1).push(stackList.get(source-1).pop());
                    numOfBoxes--;
                }

            }
        }
        StringBuilder builder = new StringBuilder();
        for(Deque<Character> l : stackList){
            if(l.size() > 0)
                builder.append(l.peek());
        }
        System.out.println(stackList);
        res = builder.toString();
        System.out.println(res);
    }

    public static void secondProblem(Scanner scanner){

        List<Deque<Character>> stackList = new ArrayList<>();
        Deque<Character> tempStorage = new LinkedList<>();
        int source = 0, dest = 0, numOfBoxes = 0;
        for(int i = 0; i<9; i++)
            stackList.add(new LinkedList<>());
        String data;
        String res;
        int stackNumber = 0;
        while(scanner.hasNext()){
            stackNumber = 0;
            data = scanner.nextLine();
            if(data.length() > 0 && data.charAt(0) == '['){
                for(int i = 1; i<data.length(); i+=4){
                    if(data.charAt(i) != ' '){
                        stackList.get(stackNumber).add(data.charAt(i));
                    }
                    stackNumber++;
                }
            }
            else if (data.length() > 0 && data.charAt(0) == 'm'){
                int i = 0, count = 0;

                String [] values = new String[3]; //index 0 contains the number of boxes to move, index 1 the source, index 2 the destination
                while(i<data.length() && count < 3) {
                    if (data.charAt(i) < '0' || data.charAt(i) > '9')
                        i++;

                    else{
                        StringBuilder builder = new StringBuilder();
                        while(i < data.length() && data.charAt(i) >= '0' && data.charAt(i) <= '9'){
                            builder.append(data.charAt(i));
                            i++;
                        }
                        values[count] = builder.toString();
                        count++;

                    }

                }

                numOfBoxes = Integer.parseInt(values[0]);
                source = Integer.parseInt(values[1]);
                dest = Integer.parseInt(values[2]);

                while(numOfBoxes > 0){
                    tempStorage.push(stackList.get(source-1).pop());
                    numOfBoxes--;
                }
                while(tempStorage.size() > 0)
                    stackList.get(dest-1).push(tempStorage.pop());

            }
        }
        StringBuilder builder = new StringBuilder();
        for(Deque<Character> l : stackList){
            if(l.size() > 0)
                builder.append(l.peek());
        }
        System.out.println(stackList);
        res = builder.toString();
        System.out.println(res);
    }

}
