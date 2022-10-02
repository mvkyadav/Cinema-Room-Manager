package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();
        char[][] room = new char[rows][cols];
        cinemaRoom(room);
        boolean flag = true;
        while(flag) {
            menu();
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    printCinemaRoom(rows, cols, room);
                    break;
                case 2:
                    while(true) {
                        System.out.println("Enter a row number:");
                        int r = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int c = scanner.nextInt();
                        String res = purchase(r, c, rows, cols, room);
                        if(res.equals("Wrong Input!")) {
                            System.out.println(res);
                        }
                        else if(res.equals("That ticket has already been purchased!")){
                            System.out.println(res);
                        }
                        else {
                            System.out.println(res);
                            break;
                        }
                    }
                    break;
                case 3:
                    stats(room);
                    break;
                case 0:
                    flag = false;

            }
        }
//        System.out.println("Total income:");
//        System.out.println("$" + profit(rows, cols));
    }

    public static void printCinemaRoom(int rows, int cols, char[][] room) {
        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if(i == 0 && j == 0){
                    System.out.print("  ");
                }
                else if(i == 0) {
                    System.out.print(j + " ");
                }
                else if(j == 0)
                    System.out.print(i + " ");
                else
                System.out.print(room[i-1][j-1] + " ");
            }
            System.out.println();
        }
    }
    public static int profit(int rows, int cols) {
        int totalScreens = rows * cols;
        int profit = 0;
        if(totalScreens < 60) {
            profit = 10 * totalScreens;

        }
        else {
            int first_half = (rows / 2) * cols ;
            int second_half = (rows * cols) - first_half;
            profit += first_half * 10 + second_half * 8;
        }
        return profit;
    }

    public static void cinemaRoom(char[][] room) {
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                room[i][j] = 'S';
            }
        }
    }

    public static int ticketPrice(int r, int c, int row, int col) {
        if(row * col <= 60)
            return 10;
        else
            return r < row/2 ? 10 : 8;
    }

    public static void menu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void stats(char[][] room) {
        int noOfPurchasedTickets = 0;
        double totalTickets = room.length * room[0].length;
        int income = 0;
        double percentage;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j] == 'B') {
                    noOfPurchasedTickets++;
                    income += ticketPrice(i, j, room.length, room[0].length);
                }
            }
        }
        System.out.println("Number of purchased tickets: " + noOfPurchasedTickets);
        try {
            percentage = (noOfPurchasedTickets / totalTickets) * 100;
            System.out.format("Percentage: %.2f%c%n", percentage, '%');

        }
        catch (ArithmeticException arithmeticException) {
            System.out.format("Percentage: %.2f%c%n", 0.00, '%');
        }

        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + profit(room.length, room[0].length));
    }

    public static String purchase(int r, int c, int rows, int cols, char[][] room) {
        if(r < 1 || r > rows || c < 1 || c > cols)
            return "Wrong Input!";
        else if(room[r-1][c-1] == 'B')
            return "That ticket has already been purchased!";
        room[r-1][c-1] = 'B';
        return "Ticket price: $" + ticketPrice(r-1, c-1, rows, cols);
    }
}