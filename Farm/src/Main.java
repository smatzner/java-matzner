import java.util.Scanner;

public class Main {

    public static int hungerSheep = 50, hungerCow = 60, hungerChicken = 60;
    public static boolean feedAnimal = false, isSheep = false, isCow = false, isChicken = false;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String animal;
        boolean noDeadAnimals = true;

        do {
            feedAnimal = false;
            isSheep = false;
            isCow = false;
            isChicken = false;
            int hunger = 0;

            printHunger();

            System.out.println("Tier eingeben (Schaf, Kuh oder Huhn):");
            animal = scanner.nextLine();

            hunger = getHungerForAnimal(animal);


            if (isHungry(hunger, animal)) {
                if (isSheep) {
                    hunger -= 20;
                    hungerSheep = Math.max(hunger, 0);
                }
                if (isCow) {
                    hunger -= 20;
                    hungerCow = Math.max(hunger, 0);
                }
                if (isChicken) {
                    hunger -= 20;
                    hungerChicken = Math.max(hunger, 0);
                }
            } else {
                isSheep = false;
                isCow = false;
                isChicken = false;
            }

            hunger();

            if (hungerSheep > 100 || hungerCow > 100 || hungerChicken > 100) {
                System.out.println("Eines der Tiere ist verhungert");
                noDeadAnimals = false;
            }
        } while (noDeadAnimals);
        scanner.close();
    }

    private static void hunger() {
        if (!isSheep) hungerSheep += 10;
        if (!isCow) hungerCow += 10;
        if (!isChicken) hungerChicken += 10;
    }

    private static boolean isHungry(int hunger, String animal) {
        if (hunger >= 75) {
            System.out.println("Das Tier wird gefüttert");
            return true;
        } else if (hunger >= 50) {
            System.out.println("Soll das Tier gefüttert werden? (J/N)");
            String feedAnimalInput = scanner.nextLine();
            return feedAnimalInput.equalsIgnoreCase("j");
        }
        System.out.println("Das Tier kann nicht gefüttert werden.");
        return false;
    }

    private static int getHungerForAnimal(String animal) {
        int hunger;
        switch (animal) {
            case "Schaf" -> {
                hunger = hungerSheep;
                isSheep = true;
                System.out.println("Du hast das Schaf gewählt!");
            }
            case "Kuh" -> {
                hunger = hungerCow;
                isCow = true;
                System.out.println("Du hast eine Kuh gewählt!");
            }
            case "Huhn" -> {
                hunger = hungerChicken;
                isChicken = true;
                System.out.println("Du hast ein Huhn gewählt!");
            }
            default -> {
                System.out.println("Kein passendes Tier gefunden!");
                hunger = -1;
            }
        }
        return hunger;
    }

    private static void printHunger() {
        System.out.println("Hungerwerte:");
        System.out.println("Schaf: " + hungerSheep);
        System.out.println("Kuh: " + hungerCow);
        System.out.println("Huhn: " + hungerChicken);
    }
}