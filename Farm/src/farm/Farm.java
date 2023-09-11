package farm;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Farm {
    private List<Animal> animals = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private int hunger, roundCounter = 1;
    private boolean noDeadAnimals = true;

    public Farm() {
    }

    public void run(){
        String animal;
        boolean noDeadAnimals = true;
        do {
            hunger = 0;

            printRoundNumber();

            printHunger();

            System.out.println("Tier oder Tiergruppe eingeben: (Name des Tieres / Schafe, Kühe, Hühner / Alle)");
            animal = sc.nextLine();

            hunger = getHungerForAnimal(animal);

            feed(hunger);

            hunger();

            increaseAge();

            setNoDeadAnimals();

        } while (noDeadAnimals);
    }

    public void printRoundNumber(){
        System.out.println(roundCounter + ". Runde");
    }

    public void printHunger() {
        System.out.println("Hungerwerte:");
        animals.forEach(animal -> System.out.println(animal.getName() + ": " + animal.getHunger()));
    }

    public int getHungerForAnimal(String animal) {
        int hunger = -1;

        for (Animal selectedAnimal : animals) {
            if (selectedAnimal.getName().equals(animal)) {
                hunger = selectedAnimal.getHunger();
                selectedAnimal.setGetsFed(true);
                System.out.println("Du hast " + selectedAnimal.getName() + " gewählt!");
                break;
            }
        }

        hunger = selectAnimalGroup(animal,hunger);

        if (hunger == -1) {
            System.out.println("Kein passendes Tier gefunden!");
        }

        return hunger;
    }

    private boolean isHungry(int hunger){
        if (hunger >= 75) {
            return true;
        } else if (hunger >= 50) {
            System.out.println("Soll das Tier gefüttert werden? (J/N)");
            String feedAnimalInput = sc.nextLine();
            return feedAnimalInput.equalsIgnoreCase("j");
        }
        System.out.println("Das Tier kann nicht gefüttert werden.");
        return false;
    }

    private void feed(int hunger){
        if(isHungry(hunger)){
            System.out.println("Fütterung ...");
            animals.forEach(animal -> {
                if(animal.getsFed()){
                    animal.feed();
                }
            });
        }
    }

    private int selectAnimalGroup(String animalGroup, int hunger) {
        switch (animalGroup.toLowerCase()) {
            case "schafe" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Sheep";
            }
            case "kühe" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Cow";
            }
            case "hühner" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Chicken";
            }
            case "alle" -> {
                System.out.println("Alle Tiere weden gefüttert");
                animalGroup = "all";
            }
        }

        for (Animal animal : animals){
            if(animalGroup.equals("all")) {
                hunger = 100;
                animal.setGetsFed(true);
                continue;
            }
            if(animal.getClass().getSimpleName().equals(animalGroup)){
                hunger = 100;
                animal.setGetsFed(true);
            }
        }

        return hunger;
    }

    public void hunger() {
        animals.forEach(animal -> {
            if (!animal.getsFed()) {
                animal.hunger();
            }
            animal.setGetsFed(false);
        });
    }

    protected void increaseAge(){
        if(roundCounter % 3 == 0){
            animals.forEach(animal -> {
                animal.increaseAge();
            });
        }

        roundCounter++;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public boolean setNoDeadAnimals() {
        for (Animal animal : animals){
            if(animal.getHunger() > 100){
                System.out.println("Eines der Tiere ist verhungert!");
                noDeadAnimals = false;
            }
        }
        return noDeadAnimals;
    }
}
