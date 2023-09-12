package farm;

import animals.*;

import java.util.*;

public class Farm {
    private static final List<String> gameModes = new ArrayList<>(Arrays.asList("Tiere füttern", "neues Tier erstellen", "Resourcen einsammlen"));
    private List<Animal> animals = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private static int roundCounter = 1;
    private static boolean noDeadAnimals = true;
    private static int hunger;
    private static boolean isAnimalGroup = false;

    public Farm() {
    }

    public void run() {
        System.out.println("----------------------------");
        System.out.println("|  Welcome to Animal Farm  |");
        do {
            printRoundNumber();
            printAnimals();
            selectGameMode();
            increaseRoundCounter();
            increaseHunger();
            setNoDeadAnimals();
        } while (noDeadAnimals);
    }

    private void printRoundNumber() {
        resetAnimalGroup();
        System.out.println("----------------------------");
        System.out.println(roundCounter + ". Runde");
        System.out.println("----------------------------");
    }

    private void printAnimals() {
        System.out.println(animals.size() + " Tiere leben auf der Farm");
        System.out.println("----------------------------");
        animals.forEach(animal -> {
            System.out.print(animal.getName());
            System.out.print(" (" + animal.getAnimalType().get("sing") + ") // ");
            System.out.print("Alter (" + animal.getAge() + ") // ");
            System.out.print("Hunger (" + animal.getHunger() + ") // ");
            System.out.println(animal.getResourceType() + " (" + animal.getYield() + ")");
        });
    }

    private void selectGameMode() {
        while (true) {
            System.out.println("----------------------------");
            System.out.println("Spielmodus wählen");
            System.out.println("----------------------------");
            int gameModeIndex = 0;
            for (String gameMode : gameModes) {
                System.out.println("(" + gameModeIndex + ") = " + gameMode);
                gameModeIndex++;
            }
            System.out.println("----------------------------");
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

    private void feedAnimals() {
        String userInput;
        System.out.println("Tier oder Tiergruppe eingeben: (Name des Tieres / Schafe, Kühe, Hühner / Alle)");
        System.out.println("----------------------------");
        userInput = sc.nextLine();

        hunger = getHungerForAnimal(userInput);

        feed(hunger);
    }

    private int getHungerForAnimal(String userInput) {
        int hunger = -1;

        if (isAnimalGroup(userInput)) {
            hunger = 100;
            for (Animal animal : animals) {
                if ((userInput.equalsIgnoreCase("alle") || animal.getAnimalType().get("plur").equalsIgnoreCase(userInput)) && animal.getHunger() < hunger){
                    hunger = animal.getHunger();
                }
            }
            isAnimalGroup = true;
            return hunger;
        }

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(userInput)) {
                hunger = animal.getHunger();
                animal.setGetsFed(true);
                System.out.println("Du hast " + animal.getName() + " gewählt!");
                break;
            }
        }

        if (hunger == -1) {
            System.out.println("Kein passendes Tier gefunden!");
        }

        return hunger;
    }

    private boolean isHungry(int hunger) {
        String feedAnimalInput;

        if (isAnimalGroup) {
            if (hunger <= 75) {
                System.out.println("Eines oder mehrere Tiere sind noch nicht besonders hungrig. Sollen trotzdem alle gewählten Tiere gefüttert werden? (J/N)");
                feedAnimalInput = sc.nextLine();
                if (feedAnimalInput.equalsIgnoreCase("n")) {
                    animals.forEach(animal -> animal.setGetsFed(false));
                    return false;
                }
                return true;
            }
        }
        if (hunger >= 75) {
            return true;
        } else if (hunger >= 50) {
            System.out.println("Soll das Tier gefüttert werden? (J/N)");
            feedAnimalInput = sc.nextLine();
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

    private boolean isAnimalGroup(String animalGroup) {
        if (animalGroup.equalsIgnoreCase("alle")) {
            animals.forEach(animal -> animal.setGetsFed(true));
            return true;
        }

        boolean animalGroupExisting = false;
        for (Animal animal : animals) {
            if (animal.getAnimalType().get("plur").equalsIgnoreCase(animalGroup)) {
                animal.setGetsFed(true);
                animalGroupExisting = true;
            }
        }

        return animalGroupExisting;
    }

    private void increaseHunger() {
        animals.forEach(animal -> {
            if (!animal.getsFed()) {
                animal.increaseHunger();
            }
            animal.setGetsFed(false);
        });
    }

    private void createNewAnimal() {

        System.out.println("Tierart eingeben: (Schaf / Kuh / Huhn)");
        String animalType = "";
        boolean incorrectAnimalType = true;
        while (incorrectAnimalType) {
            animalType = sc.nextLine();
            for (Animal animal : animals) {
                if (animal.getAnimalType().get("sing").equalsIgnoreCase(animalType)) {
                    incorrectAnimalType = false;
                    break;
                }
            }
            if (incorrectAnimalType) {
                System.out.println("Tierart nicht vorhanden. Bitte erneut eingeben!");
            }
        }

        String animalName;

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
        int animalAge = Integer.parseInt(sc.nextLine());

        switch (animalType.toLowerCase()) {
            case "schaf" -> animals.add(new Sheep(animalName, 50, animalAge, 0));
            case "kuh" -> animals.add(new Cow(animalName, 50, animalAge, 0));
            case "huhn" -> animals.add(new Chicken(animalName, 50, animalAge, 0));
            case "schwein" -> animals.add(new Pig(animalName, 50, animalAge, 0));
        }
    }

    private void collectResources() {
        printYield();
        collectYield();
    }

    private void printYield() {
        for (Animal animal : animals) {
            animal.printYield();
        }
    }

    private void collectYield() {
        System.out.println("Von welchem Tier wollen Sie die Ressource einsammeln?");
        String animalInput = sc.nextLine();
        Animal animal = animals.stream().filter(selectedAnimal -> selectedAnimal.getName().equalsIgnoreCase(animalInput)).findFirst().orElse(null);

        if (animal != null) {
            animal.collectYield();
            if (!animal.isAlive()) {
                animals.remove(animal);
            }
        } else {
            System.out.println("Tier nicht gefunden");
        }
    }

    private void increaseRoundCounter() {
        if (roundCounter % 3 == 0) {
            animals.forEach(Animal::increaseAge);
        }

        roundCounter++;
    }

    private void resetAnimalGroup() {
        isAnimalGroup = false;
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
