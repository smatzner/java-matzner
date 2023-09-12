import animals.*;
import farm.Farm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Farm farm = new Farm();

        Sheep sheep = new Sheep("Ferdi", 50, 5, 2);
        Cow cow = new Cow("Susi", 60, 8, 5);
        Chicken chicken = new Chicken("Klaus", 60, 3, 5);

        Pig pig = new Pig("Basti",70,3,0);
        List<Animal> animals = new ArrayList<>(Arrays.asList(sheep, cow, chicken, pig));

        farm.setAnimals(animals);

        farm.run();
    }
}