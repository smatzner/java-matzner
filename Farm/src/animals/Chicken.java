package animals;

public class Chicken {
    private String name;
    private int hunger, age, eggs;

    public Chicken(String name, int hunger, int age, int eggs) {
        this.name = name;
        this.hunger = hunger;
        this.age = age;
        this.eggs = eggs;
    }

    public Chicken() {
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

    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }
}
