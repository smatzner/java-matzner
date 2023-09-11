package farm;

import animals.Animal;
import animals.Chicken;
import animals.Cow;
import animals.Sheep;

import java.util.*;

public class Farm {
    private static final List<String> gameModes = new ArrayList<>(Arrays.asList("Tiere füttern", "neues Tier erstellen", "Resourcen einsammlen"));
    private List<Animal> animals = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private static int roundCounter = 1;
    private static boolean noDeadAnimals = true;
    private static int hunger;

    public Farm() {
    }

    public void run() {
        do {
            printRoundNumber();
            printHunger();
            selectGameMode();
            increaseRoundCounter();
            hunger();
            setNoDeadAnimals();
        } while (noDeadAnimals);
    }

    private void printRoundNumber() {
        System.out.println(roundCounter + ". Runde");
    }

    private void selectGameMode() {
        while (true) {
            System.out.println("Modus wählen");
            int gameModeIndex = 0;
            for (String gameMode : gameModes) {
                System.out.println("(" + gameModeIndex + ") = " + gameMode);
                gameModeIndex++;
            }
            String mode = sc.nextLine();
            switch (mode) {
                case "0" -> {
                    System.out.println(gameModes.get(0) + " gewählt");
                    feedAnimals();
                    return;
                }
                case "1" -> {
                    System.out.println(gameModes.get(1) + " gewählt");
                    createNewAnimal();
                    return;
                }
                case "2" -> {
                    System.out.println(gameModes.get(2) + " gewählt");
                    collectResources();
                    return;
                }
                default -> {
                    System.out.println("Inkorrekter Modus.");
                }
            }
        }
    }

    private void createNewAnimal() {
        System.out.println("Tierart eingeben: (Schaf / Kuh / Huhn");
        String animalType = sc.nextLine();
        String animalName;
        int animalAge;

        while (true) {
            System.out.println("Namen des Tieres eingeben:");
            String name = sc.nextLine();
            if (animals.stream().anyMatch(animal -> animal.getName().equals(name))) {
                System.out.println(name + " bereits vorhanden. Bitte einen anderen Namen wählen");
                continue;
            }
            animalName = name;
            break;
        }

        System.out.println("Alter des Tieres eingeben:");
        animalAge = Integer.parseInt(sc.nextLine());

        switch (animalType) {
            case "Schaf" -> animals.add(new Sheep(animalName, 50, animalAge, 0));
            case "Kuh" -> animals.add(new Cow(animalName, 50, animalAge, 0));
            case "Huhn" -> animals.add(new Chicken(animalName, 50, animalAge, 0));
        }
    }

    private void feedAnimals() {
        String animal;

        System.out.println("Tier oder Tiergruppe eingeben: (Name des Tieres / Schafe, Kühe, Hühner / Alle)");
        animal = sc.nextLine();

        hunger = getHungerForAnimal(animal);

        feed(hunger);
    }

    private void collectResources() {
        printYield();
        collectYield();
    }

    private void printYield() {
        for (Animal animal : animals) {
            switch (animal.getClass().getSimpleName()) {
                case "Sheep" -> {
                    int wool = ((Sheep) animal).getWool();
                    System.out.println(animal.getName() + "(Schaf): " + wool + "kg Wolle");
                    return;
                }
                case "Cow" -> {
                    int milk = ((Cow) animal).getMilk();
                    System.out.println(animal.getName() + "(Kuh): " + milk + "l Milch");
                    return;
                }
                case "Chicken" -> {
                    int eggs = ((Chicken) animal).getEggs();
                    System.out.println(animal.getName() + "(Huhn): " + eggs + "Stück Eier");
                    return;
                }

            }
        }
    }

    private void collectYield(){
        System.out.println("Von welchem Tier wollen Sie die Ressource einsammeln?");
        String animalInput = sc.nextLine();
        Animal animal = animals.stream().filter(animal1 -> animal1.getName().equals(animalInput)).findFirst().orElse(null);
        switch (animal.getClass().getSimpleName()){
            case "Sheep" -> {
                System.out.println(((Sheep)animal).getWool() + "kg Wolle eingesammelt");
                ((Sheep)animal).setWool(0);
            }
            case "Cow" -> {
                System.out.println(((Cow)animal).getMilk() + "l Milch eingesammelt");
                ((Cow)animal).setMilk(0);
            }
            case "Chicken" -> {
                System.out.println(((Chicken)animal).getEggs() + " Eier eingesammelt");
                ((Chicken)animal).setEggs(0);
            }
        }
    }

    private void printHunger() {
        System.out.println("Hungerwerte:");
        animals.forEach(animal -> System.out.println(animal.getName() + ": " + animal.getHunger()));
    }

    private int getHungerForAnimal(String animal) {
        int hunger = -1;

        for (Animal selectedAnimal : animals) {
            if (selectedAnimal.getName().equalsIgnoreCase(animal)) {
                hunger = selectedAnimal.getHunger();
                selectedAnimal.setGetsFed(true);
                System.out.println("Du hast " + selectedAnimal.getName() + " gewählt!");
                break;
            }
        }

        hunger = selectAnimalGroup(animal, hunger);

        if (hunger == -1) {
            System.out.println("Kein passendes Tier gefunden!");
        }

        return hunger;
    }

    private boolean isHungry(int hunger) {
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

    private void feed(int hunger) {
        if (isHungry(hunger)) {
            System.out.println("Fütterung ...");
            animals.forEach(animal -> {
                if (animal.getsFed()) {
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

        for (Animal animal : animals) {
            if (animalGroup.equals("all")) {
                hunger = 100;
                animal.setGetsFed(true);
                continue;
            }
            if (animal.getClass().getSimpleName().equals(animalGroup)) {
                hunger = 100;
                animal.setGetsFed(true);
            }
        }

        return hunger;
    }

    private void hunger() {
        animals.forEach(animal -> {
            if (!animal.getsFed()) {
                animal.hunger();
            }
            animal.setGetsFed(false);
        });
    }

    private void increaseRoundCounter() {
        if (roundCounter % 3 == 0) {
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

    private void setNoDeadAnimals() {
        for (Animal animal : animals) {
            if (animal.getHunger() >= 100) {
                System.out.println("Eines der Tiere ist verhungert. Game Over!");
                noDeadAnimals = false;
                break;
            }
        }
    }
}
