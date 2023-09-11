package animals;

public class Chicken extends Animal {
    private int eggs;

    public Chicken(String name, int hunger, int age, int eggs) {
        super(name, hunger, age);
        this.eggs = eggs;
    }

    public Chicken() {
    }

    @Override
    public void feed() {
        super.feed();
        eggs++;
    }

    @Override
    public String getAnimalType() {
        return "Huhn";
    }

    @Override
    public void printYield() {
        System.out.println(getName() + " (" + getAnimalType() + "): " + getEggs() + " Stück Eier.");
    }

    @Override
    public void collectYield() {
        System.out.println(getEggs() + "Eier eingesammelt.");
        setEggs(0);
    }

    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }

}