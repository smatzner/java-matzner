import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        rollDice();
    }

    public static void rollDice() {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Anzahl an Würfen eingeben: ");
        int diceThrows = scanner.nextInt();

        for (int i = 1; i <= diceThrows; i++) {
            System.out.print(rand.nextInt(1, 7) + " ");
        }
    }
}