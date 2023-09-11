import animals.Animal;
import animals.Chicken;
import animals.Cow;
import animals.Sheep;
import farm.Farm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Farm farm;

    public static void main(String[] args) {
        farm = new Farm();
        String animalName;
        boolean noDeadAnimals = true;

        Sheep sheep = new Sheep("Ferdi", 50, 5, 2);
        Cow cow = new Cow("Susi", 60, 8, 5);
        Chicken chicken = new Chicken("Klaus", 60, 3, 5);

        List<Animal> animals = new ArrayList<>(Arrays.asList(sheep, cow, chicken));

        farm.setAnimals(animals);

        do {
            int hunger = 0;

            farm.printHunger();

            System.out.println("Tier oder Tiergruppe eingeben: (Namen des Tiers / Schafe, Kühe, Hühner / Alle)");
            animalName = scanner.nextLine();

            hunger = farm.getHungerForAnimal(animalName);

            if (isHungry(hunger)) {
                System.out.println("Fütterung ...");
                animals.forEach(animal -> {
                    if (animal.getsFed()) {
                        animal.feed();
                    }
                });
            }

            farm.hunger();

            if (sheep.getHunger() > 100 || cow.getHunger() > 100 || chicken.getHunger() > 100) {
                System.out.println("Eines der Tiere ist verhungert");
                noDeadAnimals = false;
            }
        } while (noDeadAnimals);
        scanner.close();
    }

    public static boolean isHungry(int hunger) {
        if (hunger >= 75) {
            return true;
        } else if (hunger >= 50) {
            System.out.println("Soll das Tier gefüttert werden? (J/N)");
            String feedAnimalInput = scanner.nextLine();
            return feedAnimalInput.equalsIgnoreCase("j");
        }
        System.out.println("Das Tier kann nicht gefüttert werden.");
        return false;
    }
}