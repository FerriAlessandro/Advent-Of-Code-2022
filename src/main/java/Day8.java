import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {

    public static int size = 99;
    public static int [][] data = new int [size][size];
    public static boolean [][] isVisible = new boolean [size][size];

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day8.txt");
            Scanner scanner = new Scanner(input);
            String d;
            int i=0, j;
            while(scanner.hasNext()){
                d = scanner.next();
               for(j=0;j<size;j++){
                   data[i][j] = d.charAt(j) - '0';
               }
               i++;
            }
            firstProblem();
            secondProblem();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }




    public static void firstProblem(){ // k = 0(top), 1(bottom), 2(left), 3(right)
        int i=0, j=0, k=0, totalCount = 0;
        int [] storage = new int[size];
        int max = 0;
        //top to bottom + left to right
        for(i=0;i<size; i++){
            for(j=0;j<size;j++){
                if(data[i][j] > storage[j] || i == 0){ //This one checks the visibility from the top
                    storage[j] = data[i][j];
                    totalCount++;
                    isVisible[i][j] = true;

                }
                if(data[i][j] > max || j == 0){  //This one checks the visibility from the left
                    max = data[i][j];
                    if(!isVisible[i][j]) {
                        isVisible[i][j] = true;
                        totalCount++;
                    }

                }
            }
            max = 0;
        }

        for(i =0; i<size; i++)
            storage[i] = 0;

        //bottom to top + right to left
        for(i = size-1; i>=0; i--){
            for(j=size-1; j>=0; j--){
                if(data[i][j] > storage[j] || i == size-1){
                    storage[j] = data[i][j];
                    if(!isVisible[i][j]) {
                        isVisible[i][j] = true;
                        totalCount++;
                    }

                }
                if(data[i][j] > max || j == size-1){
                    max = data[i][j];
                    if(!isVisible[i][j]) {
                        isVisible[i][j] = true;
                        totalCount++;
                    }
                }
            }
            max = 0;
        }
        System.out.println(totalCount);
    }


    public static void secondProblem(){

        int maxScenic = 0, tmp;
        int [] count = new int [4];
        int i, j, k, index;
        for(i=1; i<size-1; i++){
            for(j=1;j<size-1;j++){
                index = 0;
                k=i-1;
                while(k>=0 && data[i][j] > data[k][j]){
                    count[index]++;
                    k--;
                }
                if(k!=-1)
                    count[index]++;

                index=1;
                k=i+1;
                while(k<=size-1 && data[i][j] > data[k][j]){
                    count[index]++;
                    k++;
                }
                if(k!=size)
                    count[index]++;

                index = 2;
                k=j-1;
                while(k>=0 && data[i][j] > data[i][k]){
                    count[index]++;
                    k--;
                }
                if(k!=-1)
                    count[index]++;

                index = 3;
                k=j+1;
                while(k<=size-1 && data[i][j] > data[i][k]){
                    count[index]++;
                    k++;
                }
                if(k!=size)
                    count[index]++;

                tmp=1;
                for(k=0;k<4;k++)
                    tmp*=count[k];
                if(tmp>maxScenic)
                    maxScenic=tmp;

                for(k=0;k<4;k++)
                    count[k]=0;

            }
        }
        System.out.println(maxScenic);
    }

}
