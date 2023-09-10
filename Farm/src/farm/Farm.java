package farm;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    List<Animal> animals = new ArrayList<>();

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

        if(hunger == -1){
            System.out.println("Kein passendes Tier gefunden!");
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
