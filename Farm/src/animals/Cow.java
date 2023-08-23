package animals;

public class Cow extends Animal {
    private int milk;

    public Cow(String name, int hunger, int age, int milk) {
        super(name, hunger, age);
        this.milk = milk;
    }

    public Cow() {
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    @Override
    public void feed(){
        setHunger(Math.max(hunger - 10,0));
        this.milk++;
    }
}
