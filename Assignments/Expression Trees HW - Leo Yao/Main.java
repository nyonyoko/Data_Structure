import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        
        while (true) {            
            System.out.println("Type your expression: ");
            input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }
            Converter convertor = new Converter(input);
            String postfix = convertor.toPostFix();
    
            ExpressionTree eTree = new ExpressionTree();
            Node node = eTree.buildTree(postfix);
    
            System.out.print("Prefix:");
            eTree.prefix(node);
            System.out.println();
            
            System.out.print("Infix:");
            eTree.infix(node);
            System.out.println();
    
            System.out.print("Postfix:");
            eTree.postfix(node);
            System.out.println();
            System.out.println();
        }
        sc.close();
    }
}
