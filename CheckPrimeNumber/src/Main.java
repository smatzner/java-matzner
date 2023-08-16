import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Zahl eingeben:");
            int num = scanner.nextInt();

            checkPrimeNumber(num);
        }
    }

    public static void checkPrimeNumber(int num) {
        boolean isPrime;

        if ((num % 2 != 0) && (num % 3 != 0) && (num % 5 != 0) && (num % 7 != 0)){
            isPrime = true;
        } else isPrime = (num == 2) || (num == 3) || (num == 5) || (num == 7);

        if(isPrime){
            System.out.println(num + " ist eine Primzahl.");
        } else {
            System.out.println(num + " ist keine Primzahl.");
        }
    }
}