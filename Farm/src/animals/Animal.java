package animals;

import animals.impl.HungerManager;

public class Animal implements HungerManager {
    protected String name;
    protected int hunger, age;
    protected boolean getsFed;

    public Animal(String name, int hunger, int age) {
        this.name = name;
        this.hunger = hunger;
        this.age = age;
    }

    public Animal() {
    }

    @Override
    public void feed() {
        this.setHunger(Math.max(hunger - 20, 0));
    }

    @Override
    public void hunger() {
        this.hunger += hungerValue;
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

    public boolean getsFed() {
        return getsFed;
    }

    public void setGetsFed(boolean getsFed) {
        this.getsFed = getsFed;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
