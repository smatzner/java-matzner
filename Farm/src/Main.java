import animals.Chicken;
import animals.Cow;
import animals.Sheep;
import farm.Farm;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Farm farm;

    public static void main(String[] args) {
        farm = new Farm();
        String animal;
        boolean noDeadAnimals = true;

        Sheep sheep = new Sheep("Ferdi", 50, 5, 2);
        Cow cow = new Cow("Susi", 60, 8, 5);
        Chicken chicken = new Chicken("Klaus", 60, 3, 5);

        farm.setSheep(sheep);
        farm.setCow(cow);
        farm.setChicken(chicken);

        do {
            farm.isSheep = false;
            farm.isCow = false;
            farm.isChicken = false;
            int hunger = 0;

            farm.printHunger();

            System.out.println("Tier eingeben (Schaf, Kuh oder Huhn):");
            animal = scanner.nextLine();

            hunger = farm.getHungerForAnimal(animal);


            if (isHungry(hunger, animal)) {
                if (farm.isSheep) {
                    hunger -= 20;
                    sheep.setHunger(Math.max(hunger, 0));
                } else if (farm.isCow) {
                    hunger -= 20;
                    cow.setHunger(Math.max(hunger, 0));
                } else if (farm.isChicken) {
                    hunger -= 20;
                    chicken.setHunger(Math.max(hunger, 0));
                }
            } else {
                farm.isSheep = false;
                farm.isCow = false;
                farm.isChicken = false;
            }

            farm.hunger();

            if (sheep.getHunger() > 100 || cow.getHunger() > 100 || chicken.getHunger() > 100) {
                System.out.println("Eines der Tiere ist verhungert");
                noDeadAnimals = false;
            }
        } while (noDeadAnimals);
        scanner.close();
    }

    public static boolean isHungry(int hunger, String animal) {
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
}