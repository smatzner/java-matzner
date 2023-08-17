package animals;

public class Sheep {
    private String name;
    private int hunger, age, wool;

    public Sheep(String name, int hunger, int age, int wool) {
        this.name = name;
        this.hunger = hunger;
        this.age = age;
        this.wool = wool;
    }

    public Sheep() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWool() {
        return wool;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }
}
