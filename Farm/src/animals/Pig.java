package animals;

import java.util.Scanner;

public class Pig extends Animal{
    private int bacon;
    private Scanner sc = new Scanner(System.in);

    public Pig(String name, int hunger, int age, int bacon) {
        super(name, hunger, age);
        this.bacon = bacon;
    }

    public Pig() {
    }

    @Override
    public void feed() {
        super.feed();
        bacon++;
    }

    @Override
    public String getAnimalType() {
        return "Schwein";
    }

    @Override
    public void printYield() {
        System.out.println(getName() + " (" + getAnimalType() + "): " + getBacon() + " kg Speck.");
    }

    @Override
    public void collectYield() {
        System.out.println(getName() + " zur Schlachtung freigeben? (J/N)");
        String slaughter = sc.nextLine();
        if(slaughter.equalsIgnoreCase("J")){
            System.out.println(getName() + " wird geschlachtet.");
            System.out.println(getBacon() + "kg Speck eingesammelt.");
            setAlive(false);
        } else {
            System.out.println(getName() + " wird nicht geschlachtet.");
        }
    }

    public int getBacon() {
        return bacon;
    }

    public void setBacon(int bacon) {
        this.bacon = bacon;
    }
}
