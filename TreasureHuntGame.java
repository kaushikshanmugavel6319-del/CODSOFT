import java.util.*;

public class TreasureHuntGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalCoins = 100;
        boolean playAgain = true;

        System.out.println("================================");
        System.out.println("   TREASURE HUNT ADVENTURE");
        System.out.println("================================");

        while(playAgain){

            int treasure = rand.nextInt(100) + 1;
            int lives = 7;
            boolean found = false;

            System.out.println("\nA treasure is hidden between 1 and 100!");
            System.out.println("You have " + lives + " lives.");
            System.out.println("Coins Available: " + totalCoins);

            while(lives > 0){

                System.out.println("\n1. Search Location");
                System.out.println("2. Buy Hint (20 Coins)");
                System.out.print("Choose: ");

                int choice = sc.nextInt();

                if(choice == 2){

                    if(totalCoins >= 20){

                        totalCoins -= 20;

                        if(treasure <= 25)
                            System.out.println("Hint: Treasure is in Zone A (1-25)");
                        else if(treasure <= 50)
                            System.out.println("Hint: Treasure is in Zone B (26-50)");
                        else if(treasure <= 75)
                            System.out.println("Hint: Treasure is in Zone C (51-75)");
                        else
                            System.out.println("Hint: Treasure is in Zone D (76-100)");

                    } else {
                        System.out.println("Not enough coins!");
                    }

                } else {

                    System.out.print("Enter Location Number: ");
                    int guess = sc.nextInt();

                    if(guess == treasure){

                        int reward = lives * 50;
                        totalCoins += reward;

                        System.out.println("\nTREASURE FOUND!");
                        System.out.println("Reward Coins: " + reward);
                        System.out.println("Total Coins: " + totalCoins);

                        found = true;
                        break;
                    }

                    lives--;

                    int distance = Math.abs(treasure - guess);

                    if(distance <= 5)
                        System.out.println("Very Hot! You're extremely close.");
                    else if(distance <= 15)
                        System.out.println("Warm! You're getting closer.");
                    else if(distance <= 30)
                        System.out.println("Cold! Far away.");
                    else
                        System.out.println("Freezing! Very far.");

                    System.out.println("Lives Remaining: " + lives);
                }
            }

            if(!found){
                System.out.println("\nMission Failed!");
                System.out.println("Treasure Location: " + treasure);
            }

            System.out.print("\nPlay Again? (Y/N): ");
            char ans = sc.next().toUpperCase().charAt(0);

            if(ans != 'Y')
                playAgain = false;
        }

        System.out.println("\nFinal Coins Earned: " + totalCoins);
        System.out.println("Thanks for playing Treasure Hunt Adventure!");
    }
}
