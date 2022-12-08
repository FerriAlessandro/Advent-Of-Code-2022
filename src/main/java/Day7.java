import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Day7 {

    public static long totalSum = 0;
    public static long min;
    static class Node {
        String name;
        long size;
        Node father;
        boolean isDirectory;
        List<Node> children;

        public Node(String name, boolean isDirectory){
            this.name = name;
            this.isDirectory = isDirectory;
        }
    }

    public static void main(String [] args){
        try {
            File input = new File("C:\\Users\\alefe\\Desktop\\Coding\\Advent-Of-Code-2022\\src\\main\\resources\\Day7.txt");
            Scanner scanner = new Scanner(input);
            Node root = parseInput(scanner);
            firstProblem(root);
            secondProblem(root);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void printList (Node root){

        System.out.println("Current directory: "+root.name);
        if(root.children != null) {
            for (Node n : root.children) {
                System.out.println(n.name);
            }
            for (Node n : root.children) {
                printList(n);
            }
        }
    }



    public static Node parseInput(Scanner scanner){
        String data;
        String name;
        StringBuilder nameBuilder;
        StringBuilder numBuilder;
        Node root = new Node("/", true);
        root.father = null;
        Node currentNode = root;
        boolean found = false;
        int i;
        scanner.nextLine();
        /*Excluding the first directory ("/"), every time we find the command "cd" we already encountered the target directory,
        hence we already added it to the children of the current directory, so the only thing we need to do when we find a "cd" command
        is to switch current directory (node). When we find "dir" or a pure file we add them as children of the current directory
         */
        while(scanner.hasNext()){
            data = scanner.nextLine();
            nameBuilder= new StringBuilder();
            if(data.charAt(0) == '$'){
                if(data.charAt(2) == 'c') {
                    if (data.charAt(5) != '.') {
                        for (i = 5; i < data.length(); i++) {
                            nameBuilder.append(data.charAt(i));
                        }
                        name = nameBuilder.toString();
                        for (i = 0; i < currentNode.children.size() && !found && currentNode.isDirectory; i++) {
                            if (Objects.equals(currentNode.children.get(i).name, name)) {
                                found = true;
                            }
                        }
                        if (found) {
                            currentNode = currentNode.children.get(i - 1);
                            found = false;
                        }
                    }
                    else {
                        currentNode = currentNode.father;
                    }
                }
            }
            else if ( data.charAt(0) == 'd'){   //nested directory
                for(i=4; i<data.length(); i++){
                    nameBuilder.append(data.charAt(i));
                }
                if(currentNode.children == null){
                    currentNode.children = new LinkedList<>();
                }
                Node child = new Node(nameBuilder.toString(), true);
                child.father = currentNode;
                currentNode.children.add(child);

            }
            else if(data.charAt(0) >='0' && data.charAt(0) <= '9'){ //file
                numBuilder = new StringBuilder();
                nameBuilder = new StringBuilder();
                i=0;
                while(data.charAt(i)>= '0' && data.charAt(i) <= '9') {
                    numBuilder.append(data.charAt(i));
                    i++;
                }
                i++;

                while(i<data.length()){
                    nameBuilder.append(data.charAt(i));
                    i++;
                }
                Node child = new Node(nameBuilder.toString(), false);
                child.size = Integer.parseInt(numBuilder.toString());
                child.father = currentNode;
                if(currentNode.children == null)
                    currentNode.children = new LinkedList<>();
                currentNode.children.add(child);

            }
        }

        return root;

    }

    public static long findSum (Node currentNode){
        long sum = 0;
        if(!currentNode.isDirectory)
            return currentNode.size;
        else{
            if(currentNode.children != null){
                for(Node n : currentNode.children){
                    sum += findSum(n);
                }
            }
        }
        if(sum <= 100000)
            totalSum+=sum;
        currentNode.size = sum;
        return sum;
    }
    public static void firstProblem(Node root){

        findSum(root);
        System.out.println(totalSum);

    }

    public static void traverse (Node root, long target){
        if(!root.isDirectory || root.size < target)
            return;

        if(root.size <= min) {
            min = root.size;
            if(root.children != null){
                for(Node n : root.children)
                    traverse(n, target);
            }
        }

    }

    public static void secondProblem(Node root){
        long sum = findSum(root);
        long freeSpace = 70000000-sum;
        long neededSpace = 30000000 - freeSpace;
        min = root.size;
        traverse(root, neededSpace);
        System.out.println(min);

    }
}
