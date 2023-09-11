package animals;

import animals.impl.HungerManager;

public abstract class Animal implements HungerManager {
    protected String name;
    protected int hunger, age;
    protected boolean getsFed;
    protected boolean isAlive;

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

    public abstract String getAnimalType();

    public abstract void printYield();

    public abstract void collectYield();

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

    public void increaseAge(){
        this.age++;
    }

    public boolean getsFed() {
        return getsFed;
    }

    public void setGetsFed(boolean getsFed) {
        this.getsFed = getsFed;
    }

    public boolean isGetsFed() {
        return getsFed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
