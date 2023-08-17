package animals;

public class Cow {
    private String name;
    private int hunger, age, milk;

    public Cow(String name, int hunger, int age, int milk) {
        this.name = name;
        this.hunger = hunger;
        this.age = age;
        this.milk = milk;
    }

    public Cow() {
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

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }
}
