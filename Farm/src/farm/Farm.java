package farm;

import animals.Animal;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Farm {
    private List<Animal> animals = new ArrayList<>();

    public Farm() {
    }

    public void printHunger() {
        System.out.println("Hungerwerte:");
        animals.forEach(animal -> System.out.println(animal.getName() + " (" + animal + "): " + animal.getHunger()));
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

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
