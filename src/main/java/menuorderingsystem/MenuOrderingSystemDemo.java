package menuorderingsystem;

import java.util.Scanner;

public class MenuOrderingSystemDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandParser parser = new CommandParser();

        while (true) {
            System.out.println("Please input an order: (input \"quit\" to stop)");
            String input = scanner.nextLine();

            if (input.equals("exit") || input.equals("quit")) break;
            else {
                String output = parser.parseOrder(input);
                System.out.println(output);
            }
        }
    }
}
