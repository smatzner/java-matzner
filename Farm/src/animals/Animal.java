package animals;

import animals.impl.HungerManager;

public class Animal implements HungerManager {
    protected String name;
    protected int hunger, age;

    public Animal(String name, int hunger, int age) {
        this.name = name;
        this.hunger = hunger;
        this.age = age;
    }

    public Animal() {
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

    @Override
    public void feed() {
        this.hunger -= 20;
    }

    @Override
    public void hunger() {
        this.hunger += hungerValue;
    }
}
