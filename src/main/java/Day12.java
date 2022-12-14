import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static void main(String [] args){
        try {
            File input = new File("C:\\Users\\alefe\\Desktop\\Coding\\Advent-Of-Code-2022\\src\\main\\resources\\Day12.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    static class Node {
        int i=0, j=0;

        public Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }


    public static void firstProblem(Scanner scanner){
        int input_x = 41, input_y = 143;
        int objective_x=0, objective_y=0;
        char [][] matrix = new char[input_x][input_y];
        boolean [][] visited = new boolean[input_x][input_y];
        String data;
        boolean found = false;
        int i=0, j=0, size =0, steps = 0;
        Queue<Node> queue = new LinkedList<>();
        while(scanner.hasNext()){
            data = scanner.nextLine();
            for(j=0;j<input_y;j++){
                matrix[i][j]=data.charAt(j);
                visited[i][j] = false;
                if(matrix[i][j] == 'S') {
                    queue.offer(new Node(i, j));
                    visited[i][j] = true;
                    matrix[i][j]= 'a';
                }
                if(matrix[i][j] == 'E'){
                    matrix[i][j]= 'z';
                    objective_x = i;
                    objective_y = j;
                }
            }
            i++;
        }

        while(!queue.isEmpty() && !found){
            size = queue.size();
            for(int k=0;k<size; k++){
                Node tmp = queue.poll();
                if(tmp.i-1 >= 0 && !visited[tmp.i-1][tmp.j] && matrix[tmp.i-1][tmp.j]-matrix[tmp.i][tmp.j]<=1){
                    if(tmp.i-1 == objective_x && tmp.j == objective_y)
                        found = true;
                    queue.offer(new Node(tmp.i-1, tmp.j));
                    visited[tmp.i-1][tmp.j] = true;
                }
                if(tmp.i+1 <= input_x-1 && !visited[tmp.i+1][tmp.j]&& matrix[tmp.i+1][tmp.j]-matrix[tmp.i][tmp.j]<=1){
                    if(tmp.i+1 == objective_x && tmp.j == objective_y)
                        found = true;
                    queue.offer(new Node(tmp.i+1, tmp.j));
                    visited[tmp.i+1][tmp.j] = true;
                }
                if(tmp.j-1 >=0 && !visited[tmp.i][tmp.j-1]&& matrix[tmp.i][tmp.j-1]-matrix[tmp.i][tmp.j]<=1){
                    if(tmp.i == objective_x && tmp.j-1 == objective_y)
                        found = true;
                    queue.offer(new Node(tmp.i, tmp.j-1));
                    visited[tmp.i][tmp.j-1] = true;
                }
                if(tmp.j+1 <= input_y-1 && !visited[tmp.i][tmp.j+1]&& matrix[tmp.i][tmp.j+1]-matrix[tmp.i][tmp.j]<=1){
                    if(tmp.i == objective_x && tmp.j+1 == objective_y)
                        found = true;
                    queue.offer(new Node(tmp.i, tmp.j+1));
                    visited[tmp.i][tmp.j+1] = true;
                }
            }
            steps++;

        }
        System.out.println(steps);

    }

    public static void secondProblem(Scanner scanner){
        int objective_x = 0, objective_y = 0;
        int input_x = 41, input_y = 143;
        char [][] matrix = new char[input_x][input_y];
        boolean [][] visited = new boolean[input_x][input_y];
        List<Node> startingCells = new ArrayList<>();
        String data;
        boolean found = false;
        int i=0, j=0, size =0, steps = 0, minSteps = -1;
        Queue<Node> queue = new LinkedList<>();
        while(scanner.hasNext()){
            data = scanner.nextLine();
            for(j=0;j<input_y;j++){
                matrix[i][j]=data.charAt(j);
                visited[i][j] = false;

                if(matrix[i][j] == 'a') {
                    startingCells.add(new Node(i, j));
                }
                if(matrix[i][j] == 'S') {
                    startingCells.add(new Node(i,j));
                    matrix[i][j] = 'a';
                }
                if(matrix[i][j] == 'E'){
                    matrix[i][j] = 'z';
                    objective_x = i;
                    objective_y = j;
                }
            }
            i++;
        }

        for(i = 0; i<startingCells.size(); i++) {
            queue = new LinkedList<>();
            steps = 0;
            found = false;
            queue.offer(new Node(startingCells.get(i).i, startingCells.get(i).j));
            for(int k = 0; k<input_x; k++){
                for(int p = 0; p<input_y; p++)
                    visited[k][p] = false;
            }
            visited[startingCells.get(i).i][startingCells.get(i).j] = true;

            while (!queue.isEmpty() && !found) {
                size = queue.size();
                for (int k = 0; k < size; k++) {
                    Node tmp = queue.poll();
                    if (tmp.i - 1 >= 0 && !visited[tmp.i - 1][tmp.j] && matrix[tmp.i - 1][tmp.j] - matrix[tmp.i][tmp.j] <= 1) {
                        if(tmp.i-1 == objective_x && tmp.j == objective_y)
                            found = true;
                        queue.offer(new Node(tmp.i - 1, tmp.j));
                        visited[tmp.i - 1][tmp.j] = true;
                    }
                    if (tmp.i + 1 <= input_x-1 && !visited[tmp.i + 1][tmp.j] && matrix[tmp.i + 1][tmp.j] - matrix[tmp.i][tmp.j] <= 1) {
                        if(tmp.i+1 == objective_x && tmp.j == objective_y)
                            found = true;
                        queue.offer(new Node(tmp.i + 1, tmp.j));
                        visited[tmp.i + 1][tmp.j] = true;
                    }
                    if (tmp.j - 1 >= 0 && !visited[tmp.i][tmp.j - 1] && matrix[tmp.i][tmp.j - 1] - matrix[tmp.i][tmp.j] <= 1) {
                        if(tmp.i == objective_x && tmp.j-1 == objective_y)
                            found = true;
                        queue.offer(new Node(tmp.i, tmp.j - 1));
                        visited[tmp.i][tmp.j - 1] = true;
                    }
                    if (tmp.j + 1 <= input_y-1 && !visited[tmp.i][tmp.j + 1] && matrix[tmp.i][tmp.j + 1] - matrix[tmp.i][tmp.j] <= 1) {
                        if(tmp.i == objective_x && tmp.j+1 == objective_y)
                            found = true;
                        queue.offer(new Node(tmp.i, tmp.j + 1));
                        visited[tmp.i][tmp.j + 1] = true;
                    }
                }
                steps++;

            }
            if(found && (steps < minSteps || minSteps == -1))
                minSteps = steps;
        }
        System.out.println(minSteps);
    }
}
