import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day13 {

    static class Node{
        boolean isList;
        int value;
        List<Node> children = new ArrayList<>();
        Node father;

        public Node (boolean isList, int value, Node father){
            this.isList = isList;
            this.value=value;
            this.father = father;
        }
    }

    public static Node parseList (String data){
        Node root = new Node(true, -1, null);
        Node currentNode = root;
        int idx = 1;
        int last_idx = data.length()-1;
        StringBuilder b;
        while(idx != last_idx){
            if(data.charAt(idx) == '['){
                Node child = new Node(true, -1, currentNode);
                currentNode.children.add(child);
                currentNode = child;
            }
            else if(data.charAt(idx) == ']' && currentNode.father != null)
                currentNode = currentNode.father;
            else{
                if(data.charAt(idx) >= '0' && data.charAt(idx) <= '9'){
                    b = new StringBuilder();
                    while(data.charAt(idx) >= '0' && data.charAt(idx) <= '9'){
                        b.append(data.charAt(idx));
                        idx++;
                    }
                    idx--;
                    Node child = new Node(false, Integer.parseInt(b.toString()), currentNode);
                    currentNode.children.add(child);
                }
            }
            idx++;
        }
        return root;
    }

    public static void print(Node n){
        if(!n.isList)
            System.out.print(n.value);
        else{
            System.out.print("[");
            for(int i =0; i<n.children.size(); i++){
                print(n.children.get(i));
                if(i!= n.children.size()-1){
                    System.out.print(",");
                }
            }
            System.out.print("]");
        }
    }

    public static void main(String [] args){
        try {
            File input = new File("C:/Users/alefe/OneDrive/Desktop/Coding/AdventOfCode2022/src/main/resources/Day13.txt");
            Scanner scanner = new Scanner(input);
            firstProblem(scanner);
            scanner = new Scanner(input);
            secondProblem(scanner);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static int compare(Node first, Node second){
        int valid;
        if(first.isList && !second.isList){
            Node tmp = new Node(true, -1, second.father);
            tmp.children.add(second);
            valid = compare(first, tmp);
        }
        else if(!first.isList && second.isList){
            Node tmp = new Node(true, -1, first.father);
            tmp.children.add(first);
            valid = compare(tmp, second);
        }
        else if(first.isList && second.isList){
            int min = Math.min(first.children.size(), second.children.size());
            for(int i=0;i<min; i++){
                valid = compare(first.children.get(i), second.children.get(i));
                if(valid == 1)
                    return 1;
                else if(valid == -1)
                    return -1;
            }
            if(first.children.size() == second.children.size())
                return 0;
            if(min == first.children.size())
                return -1;
            else return 1;

        }
        else {
            if(first.value < second.value)
                return -1;
            else if(first.value == second.value)
                return 0;
            else return 1;
        }
        return valid;

    }

    public static void firstProblem(Scanner scanner){
        Node first, second;
        int count = 1, res =0;

        while(scanner.hasNext()){
            first = parseList(scanner.nextLine());
            second = parseList(scanner.nextLine());
            if(compare(first, second) == -1) {
                res += count;
                /*print(first);
                System.out.print(" vs ");
                print(second);
                System.out.print(" : TRUE");*/
            }
            /*else {
                print(first);
                System.out.print(" vs ");
                print(second);
                System.out.print(" : FALSE");
            }
            System.out.print("\n");*/
            count++;
            if(scanner.hasNext())
                scanner.nextLine();
        }
        System.out.println(res);


    }
    public static void secondProblem(Scanner scanner){
        int numOfPackets = 302, index = 0, res=1;
        Node [] nodes = new Node [numOfPackets];

        Node divider_1 = new Node(true, -1, null);
        divider_1.children.add(new Node(true, -1, divider_1));
        divider_1.children.get(0).children.add(new Node(false, 2, divider_1.children.get(0)));

        Node divider_2 = new Node(true, -1, null);
        divider_2.children.add(new Node(true, -1, divider_2));
        divider_2.children.get(0).children.add(new Node(false, 6, divider_2.children.get(0)));

        String data;
        while(scanner.hasNext()){
            data = scanner.nextLine();
            if(!data.equals("")){
                nodes[index] = parseList(data);
                index++;
            }
        }
        nodes[numOfPackets-2] = divider_1;
        nodes[numOfPackets-1] = divider_2;
        Arrays.sort(nodes, Day13::compare);
        for(int i=0;i<numOfPackets;i++){
            if(nodes[i].equals(divider_1) || nodes[i].equals(divider_2))
                res*=(i+1);
        }

        System.out.println(res);


    }
}
