package animals;

import java.util.Map;

public class Sheep extends Animal {
    private int wool;

    public Sheep(String name, int hunger, int age, int wool) {
        super(name, hunger, age);
        this.wool = wool;
    }

    public Sheep() {
    }

    @Override
    public void feed() {
        super.feed();
        wool++;
    }

    @Override
    public Map<String, String> getAnimalType() {
        return AnimalType.SHEEP.getAnimalTypeLabels();
    }

    @Override
    public String getResourceType() {
        return ResourceType.WOOL.getResourceType();
    }

    @Override
    public int getYield() {
        return wool;
    }

    @Override
    public void printYield() {
        System.out.println(getName() + " (" + getAnimalType() + "): " + getWool() + " kg Wolle.");
    }

    @Override
    public void collectYield() {
        System.out.println(getWool() + " kg Wolle eingesammelt.");
        setWool(0);
    }

    public int getWool() {
        return wool;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }
}
