package farm;

import animals.Chicken;
import animals.Cow;
import animals.Sheep;

public class Farm {
    private Chicken chicken;
    private Cow cow;
    private Sheep sheep;
    public static boolean isSheep, isCow, isChicken;

    public Farm() {
    }

    public void printHunger() {
        System.out.println("Hungerwerte:");
        System.out.println("Schaf: " + sheep.getHunger());
        System.out.println("Kuh: " + cow.getHunger());
        System.out.println("Huhn: " + chicken.getHunger());
    }

    public int getHungerForAnimal(String animal) {
        int hunger;
        switch (animal) {
            case "Schaf" -> {
                hunger = sheep.getHunger();
                isSheep = true;
                System.out.println("Du hast das Schaf gewählt!");
            }
            case "Kuh" -> {
                hunger = cow.getHunger();
                isCow = true;
                System.out.println("Du hast eine Kuh gewählt!");
            }
            case "Huhn" -> {
                hunger = chicken.getHunger();
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

    public void hunger() {
        if (!isSheep) sheep.hunger();
        if (!isCow) cow.hunger();
        if (!isChicken) chicken.hunger();
    }

    public Chicken getChicken() {
        return chicken;
    }

    public void setChicken(Chicken chicken) {
        this.chicken = chicken;
    }

    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

    public Sheep getSheep() {
        return sheep;
    }

    public void setSheep(Sheep sheep) {
        this.sheep = sheep;
    }
}
