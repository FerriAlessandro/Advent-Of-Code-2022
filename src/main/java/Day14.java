import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day14 {

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day14.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner, input);
            scanner = new Scanner(input);
            secondProblem(scanner, input);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
    public static void findSize(int[] size, Scanner scanner){ //size: index 0 = min_x, index 1 = max_x, index 2 = min_y, index 3 = max_y
        StringBuilder b;
        String data;
        int i = 0, val_x, val_y;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            i=0;
            b = new StringBuilder();
            while(i<data.length() && data.charAt(i)>= '0' && data.charAt(i) <= '9'){

                b.append(data.charAt(i));
                i++;
                if( i< data.length() && data.charAt(i) == ','){
                    val_x = Integer.parseInt(b.toString());
                    if(val_x < size[0] || size[0] == -1)
                        size[0] = val_x;
                    if(val_x > size[1] || size[1] == -1)
                        size[1] = val_x;
                    i++;
                    b= new StringBuilder();
                }
                else if((i<data.length() && data.charAt(i) == ' ') || i==data.length()){
                    val_y = Integer.parseInt(b.toString());
                    if(val_y < size[2] || size[2] == -1)
                        size[2] = val_y;
                    if(val_y > size[3] || size[3] == -1)
                        size[3] = val_y;
                    b= new StringBuilder();

                    while(i<data.length() && (data.charAt(i)<='0' || data.charAt(i) >= '9'))
                        i++;
                }
            }

        }
    }

    public static void drawPattern(char [][] cave, int curr, int next, int fixed_coord, char direction){
        if(direction == 'x'){
            if(curr < next){
                for(int i=curr; i<=next; i++){
                    cave[fixed_coord][i] = '#';
                }
            }
            else{
                for(int i=curr; i>= next; i--){
                    cave[fixed_coord][i] = '#';
                }
            }
        }
        else{
            if(curr < next){
                for(int i=curr; i<=next; i++){
                    cave[i][fixed_coord] = '#';
                }
            }
            else{
                for(int i=curr; i>= next; i--){
                    cave[i][fixed_coord] = '#';
                }
            }
        }
    }

    public static void firstProblem(Scanner scanner, File input) throws FileNotFoundException {

        boolean settled = false, found = false;
        int count = 0;
        int [] size = new int[4]; //size: index 0 = min_x, index 1 = max_x, index 2 = min_y, index 3 = max_y
        Arrays.fill(size, -1);
        findSize(size, scanner);
        scanner = new Scanner (input);
        int size_x = size[1]-size[0]+1;
        int size_y = size[3]+1;
        int offset_x = size[0];
        int offset_y = 0;
        int pouringCell_x = 500-offset_x;
        int pouringCell_y = 0;
        int curr_sand_x;
        int curr_sand_y;
        char[][] cave = new char[size_y][size_x];
        for(int i=0;i<size_y;i++){
            for(int j=0;j<size_x;j++){
                cave[i][j]= '.';
            }
        }
        String data;
        StringBuilder b = new StringBuilder();
        int curr_x, curr_y, next_x, next_y;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            int i=0;
            curr_x=-1;
            curr_y=-1;
            next_x=-1;
            next_y=-1;
            b=new StringBuilder();
            while(i<data.length() && data.charAt(i)>= '0' && data.charAt(i) <= '9'){
                b.append(data.charAt(i));
                i++;
                if( i< data.length() && data.charAt(i) == ','){
                   if(curr_x == -1){
                       curr_x = Integer.parseInt(b.toString())-offset_x;
                   }
                   else {
                       next_x = Integer.parseInt(b.toString())-offset_x;
                   }
                   i++;
                   b= new StringBuilder();
                }
                else if((i<data.length() && data.charAt(i) == ' ') || i==data.length()){
                   if(curr_y == -1){
                       curr_y = Integer.parseInt(b.toString())-offset_y;
                   }
                   else{
                       next_y = Integer.parseInt(b.toString())-offset_y;
                   }
                   b=new StringBuilder();

                   if(next_y != -1){
                       if(next_x - curr_x != 0){
                           drawPattern(cave, curr_x, next_x, curr_y,'x');
                       }
                       else if(next_y - curr_y != 0){
                           drawPattern(cave, curr_y, next_y, curr_x, 'y');
                       }
                       curr_x = next_x;
                       curr_y = next_y;
                   }
                   while(i<data.length() && (data.charAt(i)<='0' || data.charAt(i) >= '9'))
                       i++;
                }


            }

        }

        while(!found){
            settled = false;
            curr_sand_x = pouringCell_x;
            curr_sand_y = pouringCell_y;
            while(!settled) {
                if (curr_sand_y == size_y-1){
                    settled=true;
                    found=true;
                }
                else if(cave[curr_sand_y+1][curr_sand_x] != '.'){
                    if(curr_sand_x == 0){
                        settled=true;
                        found=true;
                    }
                    else{
                        if(cave[curr_sand_y+1][curr_sand_x-1] == '.'){
                            curr_sand_y = curr_sand_y+1;
                            curr_sand_x = curr_sand_x-1;
                        }
                        else{
                            if(curr_sand_x == size_x-1){
                                settled=true;
                                found=true;
                            }
                            else{
                                if(cave[curr_sand_y+1][curr_sand_x+1] == '.'){
                                    curr_sand_y = curr_sand_y +1;
                                    curr_sand_x = curr_sand_x +1;
                                }
                                else{
                                    cave[curr_sand_y][curr_sand_x] = 'o';
                                    count++;
                                    settled = true;
                                }
                            }
                        }
                    }
                }

                else{
                    curr_sand_y = curr_sand_y+1;
                }

            }
        }
        System.out.println(count);

    }

    public static void secondProblem(Scanner scanner, File input) throws FileNotFoundException {

        boolean settled = false, found = false;
        int count = 0;
        int [] size = new int[4]; //size: index 0 = min_x, index 1 = max_x, index 2 = min_y, index 3 = max_y
        Arrays.fill(size, -1);
        findSize(size, scanner);
        scanner = new Scanner (input);
        int size_x = size[1]-size[0]+1+1000;
        int size_y = size[3]+1+2;
        int offset_x = size[0]-500;
        int offset_y = 0;
        int pouringCell_x = 500-offset_x;
        int pouringCell_y = 0;
        int curr_sand_x;
        int curr_sand_y;
        char[][] cave = new char[size_y][size_x];
        for(int i=0;i<size_y;i++){
            for(int j=0;j<size_x;j++){
                cave[i][j]= '.';
            }
        }
        for(int i=0;i<size_x;i++)
            cave[size_y-1][i]='#';
        String data;
        StringBuilder b = new StringBuilder();
        int curr_x, curr_y, next_x, next_y;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            int i=0;
            curr_x=-1;
            curr_y=-1;
            next_x=-1;
            next_y=-1;
            b=new StringBuilder();
            while(i<data.length() && data.charAt(i)>= '0' && data.charAt(i) <= '9'){
                b.append(data.charAt(i));
                i++;
                if( i< data.length() && data.charAt(i) == ','){
                    if(curr_x == -1){
                        curr_x = Integer.parseInt(b.toString())-offset_x;
                    }
                    else {
                        next_x = Integer.parseInt(b.toString())-offset_x;
                    }
                    i++;
                    b= new StringBuilder();
                }
                else if((i<data.length() && data.charAt(i) == ' ') || i==data.length()){
                    if(curr_y == -1){
                        curr_y = Integer.parseInt(b.toString())-offset_y;
                    }
                    else{
                        next_y = Integer.parseInt(b.toString())-offset_y;
                    }
                    b=new StringBuilder();

                    if(next_y != -1){
                        if(next_x - curr_x != 0){
                            drawPattern(cave, curr_x, next_x, curr_y,'x');
                        }
                        else if(next_y - curr_y != 0){
                            drawPattern(cave, curr_y, next_y, curr_x, 'y');
                        }
                        curr_x = next_x;
                        curr_y = next_y;
                    }
                    while(i<data.length() && (data.charAt(i)<='0' || data.charAt(i) >= '9'))
                        i++;
                }


            }

        }


        while(!found){
            settled = false;
            curr_sand_x = pouringCell_x;
            curr_sand_y = pouringCell_y;
            while(!settled) {
                if (curr_sand_y == size_y-1){
                    settled=true;
                    found=true;
                }
                else if(cave[curr_sand_y+1][curr_sand_x] != '.'){
                    if(curr_sand_x == 0){
                        settled=true;
                        found=true;
                    }
                    else{
                        if(cave[curr_sand_y+1][curr_sand_x-1] == '.'){
                            curr_sand_y = curr_sand_y+1;
                            curr_sand_x = curr_sand_x-1;
                        }
                        else{
                            if(curr_sand_x == size_x-1){
                                settled=true;
                                found=true;
                            }
                            else{
                                if(cave[curr_sand_y+1][curr_sand_x+1] == '.'){
                                    curr_sand_y = curr_sand_y +1;
                                    curr_sand_x = curr_sand_x +1;
                                }
                                else{
                                    if(curr_sand_x == pouringCell_x && curr_sand_y == pouringCell_y)
                                        found = true;
                                    cave[curr_sand_y][curr_sand_x] = 'o';
                                    count++;
                                    settled = true;
                                }
                            }
                        }
                    }
                }

                else{
                    curr_sand_y = curr_sand_y+1;
                }

            }
        }
        for(int i=0;i<size_y;i++){
            for(int j=0;j<size_x;j++){
                System.out.print(cave[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.println(count);
    }

}
