import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int balance = 100;                                                    // variable for initial balance
        int bet;                                                              // variable for bet
        int payout;                                                           // variable for payout
        String[] row;                                                         // string array for symbols
        String playAgain;

        System.out.println("##########################");
        System.out.println("  Welcome to JAVA SLOTS!  ");
        System.out.println(" Symbols: 🍒 🍉 🍋 🔔 ⭐️ ");
        System.out.println("##########################");

        while (balance > 0) {                                                 // checking if balance is positive
            System.out.println("Current balance: $" + balance);               // showing balance
            System.out.print("Place your bet amount: ");                      // entering bet
            bet = scanner.nextInt();
            scanner.nextLine();

            if (bet > balance) {                                              // checking if bet is higher than balance
                System.out.println("INSUFFICIENT FUNDS");
                continue;
            }
            else if (bet <=0) {                                               // checking if entered bet is positive
                System.out.println("Bet must be greater than 0");
                continue;
            }
            else  {                                                           // subtract bet from the balance
                balance -= bet;
            }
            System.out.println("Spinning...");
            row = spinRow();                                                  // calling spinRow method
            printRow(row);                                                    // assigning row
            payout = getPayout(row, bet);                                     // calling printRow method

            if (payout > 0) {                                                 // checking if payout is positive
                System.out.println("You won: $" + payout);
                balance += payout;                                            // adding payout to balance
            }
            else {
                System.out.println("Sorry, you lost this round");
            }

            System.out.println("Do you want play again? (Y/N)");             // users choice
            playAgain = scanner.nextLine().toUpperCase();

            if (!playAgain.equals("Y")){
                break;                                                       // terminates the while loop
            }
        }

        System.out.println("Game over! Your final balance is $" + balance); // showing final balance
        scanner.close();
    }

    static String[] spinRow() {

        String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐️"};
        String[] row = new String[3];

        Random random = new Random();

       for (int i = 0; i < 3; i++) {
           row[i] = symbols[random.nextInt(symbols.length)];
       }
        return row;

    }                                         // method for row of symbols
    static void printRow(String[] row) {
        System.out.println("##############");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("##############");
    }                                // printing row method
    static int getPayout(String[] row, int bet) {                           // payout method

        if(row[0].equals(row[1]) && row[1].equals(row[2])) {                // payout for 3 same symbols
            return switch(row[0]){
                case "🍒" -> bet * 3;                                       // values of each symbol
                case "🍉" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🔔" -> bet * 10;
                case "⭐" -> bet * 20;
                default -> 0;
            };
        }
        else if(row[0].equals(row[1])) {                                    // payout for first 2 same symbols
            return switch(row[0]){
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])) {                                    // payout for last 2 same symbols
            return switch(row[1]){
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }

        return 0;
    }
}