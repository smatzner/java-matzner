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
            case "Schwein" -> animals.add(new Pig(animalName,50,animalAge,0));
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
            animal.printYield();
        }
    }

    private void collectYield() {
        System.out.println("Von welchem Tier wollen Sie die Ressource einsammeln?");
        String animalInput = sc.nextLine();
        Animal animal = animals.stream().filter(selectedAnimal -> selectedAnimal.getName().equalsIgnoreCase(animalInput)).findFirst().orElse(null);

        if (animal != null) {
            animal.collectYield();
            if(!animal.isAlive()){
                animals.remove(animal);
            }
        } else {
            System.out.println("Tier nicht gefunden");
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
                animalGroup = "Schaf";
            }
            case "kühe" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Kuh";
            }
            case "hühner" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Huhn";
            }
            case "schweine" -> {
                System.out.println("Alle " + animalGroup + " werden gefüttert");
                animalGroup = "Schwein";
            }
            case "alle" -> {
                System.out.println("Alle Tiere weden gefüttert");
            }
        }

        for (Animal animal : animals) {
            if (animalGroup.equals("alle")) {
                hunger = 100;
                animal.setGetsFed(true);
                continue;
            }
            if (animal.getAnimalType().equals(animalGroup)) {
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
            animals.forEach(Animal::increaseAge);
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
