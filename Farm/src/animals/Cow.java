package animals;

import java.util.Map;

public class Cow extends Animal {
    private int milk;

    public Cow(String name, int hunger, int age, int milk) {
        super(name, hunger, age);
        this.milk = milk;
    }

    public Cow() {
    }

    @Override
    public void feed(){
        setHunger(Math.max(hunger - 10,0));
        this.milk++;
    }

    @Override
    public Map<String, String> getAnimalType() {
        return AnimalType.COW.getAnimalTypeLabels();
    }

    @Override
    public String getResourceType() {
        return ResourceType.MILK.getResourceType();
    }

    @Override
    public int getYield() {
        return milk;
    }

    @Override
    public void printYield() {
        System.out.println(getName() + " (" + getAnimalType().get("sing") + "): " + getMilk() + " Liter Milch.");
    }

    @Override
    public void collectYield() {
        System.out.println(getMilk() + "l Milch eingesammelt.");
        setMilk(0);
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }
}
