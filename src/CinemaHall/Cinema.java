
package CinemaHall;

import java.util.Scanner;

public class Cinema {
    private static char[][] cinemaHall;
    private static final char SEAT = 'S';
    private static final char BUSY = 'B';
    private static final Scanner scanner = new Scanner(System.in);
    private static final int frontHalf = 10;
    private static final int backHalf = 8;

    private static int purchasedTickets;

    private static int currentIncome;

    private static int totalIncome;

    public static void main(String[] args) {
        createCinema();

        checkUser();


    }

    public static void createCinema() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            if (rows % 2 != 0) {
                totalIncome = ((rows / 2) * seats) * 10 + ((rows / 2 + 1) * seats) * 8;
            } else {
                totalIncome = ((rows / 2) * seats) * 10 + ((rows / 2) * seats) * 8;
            }
        }

        cinemaHall = new char[rows][seats];
        if (rows <= 9 && seats <= 9) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    cinemaHall[i][j] = SEAT;
                }
            }
        } else {
            System.out.println("Error!!!");
        }
    }

    public static void printStatistics() {
        float percentage = purchasedTickets * 100 / (float) (cinemaHall.length * cinemaHall[0].length);
        String statistics = String.format("Number of purchased tickets: %d%nPercentage: %.2f%c%nCurrent income: $%d%nTotal income: $%d%n", purchasedTickets, percentage, '%', currentIncome, totalIncome);
        System.out.println(statistics);
    }

    public static void printCinemaHall(char[][] cinemaHall) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]);
            }
            System.out.println();
        }
    }

    public static void takeTicket(char[][] cinemaHall) {
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (rowNumber > cinemaHall.length || seatNumber > cinemaHall[0].length) {
            System.out.println("Wrong input!");
            takeTicket(cinemaHall);
        } else if (cinemaHall[rowNumber - 1][seatNumber - 1] == BUSY) {
            System.out.println("That ticket has already been purchased!");
            takeTicket(cinemaHall);
        } else {
            cinemaHall[rowNumber - 1][seatNumber - 1] = BUSY;
            int totalPlaces = cinemaHall.length * cinemaHall[0].length;
            int seatPrice;
            if (totalPlaces <= 60) {
                seatPrice = frontHalf;
                currentIncome += 10;

            } else {
                int halfOfRows = cinemaHall.length / 2;
                if (rowNumber <= halfOfRows) {
                    seatPrice = frontHalf;
                    currentIncome += 10;

                } else {
                    seatPrice = backHalf;
                    currentIncome += 8;

                }
            }
            System.out.println("Ticket price: $" + seatPrice);
            purchasedTickets++;


        }

    }

    public static void checkUser() {
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                                """);
        int userChoice = scanner.nextInt();


        switch (userChoice) {
            case 1:
                printCinemaHall(cinemaHall);
                checkUser();
                break;
            case 2:
                takeTicket(cinemaHall);
                checkUser();
                break;
            case 3:
                printStatistics();
                checkUser();
            case 0:
                break;
            default:
                System.out.println("Please choose correct option");
                checkUser();


        }

    }


}
